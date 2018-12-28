package com.ztx.world.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ztx.world.base.mapper.ext.UserExtMapper;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.redis.RedisCacheManager;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.common.utils.ResponseUtil;

public class SessionControlFilter extends AccessControlFilter {
	
	// 退出后到的地址
	private String loginUrl;
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private UserExtMapper userExtMapper;
	
	@Autowired
	private RedisCacheManager redisCacheManager;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		CustomSession mySession = (CustomSession)subject.getPrincipal();
		if(mySession != null){
			String userCode = mySession.getUserCode();
			if(!StringUtils.isEmpty(userCode)){
				if(redisOperator.hasKey(ConfigConstants.LOGIN_VERSION_PRE + userCode)){
					long version = (long)redisOperator.get(ConfigConstants.LOGIN_VERSION_PRE + userCode);
					// 如果用户登录版本过旧,则强制登出用户
					if(mySession.getSessionVersion() < version){
						try {
							subject.logout();
						} catch (Exception e) {}
						HttpServletRequest httpRequest = (HttpServletRequest)request;
						HttpServletResponse httpResponse = (HttpServletResponse)response;
						String requestType = httpRequest.getHeader("X-Requested-With");
						if ("XMLHttpRequest".equals(requestType)) {
				        	BaseResponse responseData = new BaseResponse(false, ResultEnum.DATA_UPDATE_ERROR);
				        	ResponseUtil.writeJson(httpResponse, responseData);
							return false;
						} else {
							WebUtils.issueRedirect(request, response, loginUrl);
							return false;
						}
					}
				}
				if(redisOperator.hasKey(ConfigConstants.AUTH_VERSION_PRE + userCode)){
					long version = (long)redisOperator.get(ConfigConstants.AUTH_VERSION_PRE + userCode);
					if(mySession.getSessionVersion() < version){
						SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)subject.getSession().getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
						redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm.authorizationCache").remove(principalCollection);
					}
				}
				if(redisOperator.hasKey(ConfigConstants.USER_VERSION_PRE + userCode)){
					long version = (long)redisOperator.get(ConfigConstants.USER_VERSION_PRE + userCode);
					if(mySession.getSessionVersion() < version){
						CustomSession newSession = userExtMapper.getSessionByUserId(mySession.getUserId());
						if(newSession != null){
							// 切换用户信息
					    	PrincipalCollection principalCollection = subject.getPrincipals(); 
					    	String realmName = principalCollection.getRealmNames().iterator().next();
					    	PrincipalCollection newPrincipalCollection = 
					    			new SimplePrincipalCollection(newSession, realmName);
					    	subject.runAs(newPrincipalCollection);
						}else{
							// 如果sessions是空的,说明用户被删了
							try {
								subject.logout();
							} catch (Exception e) {}
							HttpServletRequest httpRequest = (HttpServletRequest)request;
							HttpServletResponse httpResponse = (HttpServletResponse)response;
							String requestType = httpRequest.getHeader("X-Requested-With");
							if ("XMLHttpRequest".equals(requestType)) {
					        	BaseResponse responseData = new BaseResponse(false, ResultEnum.DATA_UPDATE_ERROR);
					        	ResponseUtil.writeJson(httpResponse, responseData);
								return false;
							} else {
								WebUtils.issueRedirect(request, response, loginUrl);
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
