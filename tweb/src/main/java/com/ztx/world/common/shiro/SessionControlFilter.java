package com.ztx.world.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ztx.world.base.mapper.ext.UserExtMapper;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.common.utils.ResponseUtil;
import com.ztx.world.common.utils.ResultCodeUtil;

public class SessionControlFilter extends AccessControlFilter {
	
	// 退出后到的地址
	private String loginUrl;
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private UserExtMapper userExtMapper;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		CustomSession mySession = (CustomSession)subject.getPrincipal();
		String userCode = mySession.getUserCode();
		
		if(!StringUtils.isEmpty(userCode)){
			if(redisOperator.hasKey(ConfigConstants.VERSION_PRE + userCode)){
				String version = (String)redisOperator.get(ConfigConstants.VERSION_PRE + userCode);
				if(!StringUtils.isEmpty(version) && !version.equals(mySession.getSessionVersion())){
					CustomSession newSession = userExtMapper.getSessionByUserId(mySession.getUserId());
					if(newSession != null){
						// 修改用户信息
						mySession.setUserName(newSession.getUserName());
					}else{
						// 如果sessions是空的,说明用户被删了或者踢了
						try {
							subject.logout();
						} catch (Exception e) {}
						HttpServletRequest httpRequest = (HttpServletRequest)request;
						HttpServletResponse httpResponse = (HttpServletResponse)response;
						String requestType = httpRequest.getHeader("X-Requested-With");
						if ("XMLHttpRequest".equals(requestType)) {
				        	BaseResponse responseData = new BaseResponse();
				        	responseData.setSuccess(false);
				        	responseData.setCode(ResultCode.SHIRO_KICKOUT_ERROR);
				        	responseData.setMessage(ResultCodeUtil.get(ResultCode.SHIRO_KICKOUT_ERROR));
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

		return true;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}