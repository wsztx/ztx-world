package com.ztx.world.common.shiro;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.service.ConfigService;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.ConfigTableConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResponseUtil;
import com.ztx.world.common.utils.ResultCodeUtil;

public class KickoutControlFilter extends AccessControlFilter {
	// 踢出后到的地址
	private String kickoutUrl;
	// 踢出之前登录的/之后登录的用户 ,默认踢出之前登录的用户
	private boolean kickoutAfter = false;

	private SessionManager sessionManager;
	
	private Cache<String, Deque<Serializable>> cache;
	
	@Autowired
	private ConfigService configService;

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cache = cacheManager.getCache("shiro-kickout-session");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, 
			Object mappedValue) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 最大同时在线数
		Integer maxSession = this.getMaxSession();
		
		Subject subject = getSubject(request, response);
		// 如果没有登录,直接进行之后的流程
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			return true;
		}

		Session session = subject.getSession();
		CustomSession customSession = (CustomSession) subject.getPrincipal();
		String usercode = customSession.getUserCode();
		Serializable sessionId = session.getId();

		// 同步控制
		Deque<Serializable> deque = cache.get(usercode);
		if (CollectionUtils.isEmpty(deque)) {
			deque = new LinkedList<Serializable>();
		}

		// 如果队列里没有此sessionId,且用户没有被踢出,放入队列
		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
			cache.put(usercode, deque);
		}

		// 如果队列里的sessionId数超出最大会话数,开始踢人
		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			// 如果踢出后者
			if (kickoutAfter) {
				kickoutSessionId = deque.removeFirst();
			// 否则踢出前者
			} else { 
				kickoutSessionId = deque.removeLast();
			}
			try {
				Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
				if (kickoutSession != null) {
					// 设置会话的kickout属性表示踢出了
					kickoutSession.setAttribute("kickout", true);
				}
			// ignore exception
			} catch (Exception e) {}
		}

		// 如果被踢出了,直接退出,重定向到踢出后的地址
		if (session.getAttribute("kickout") != null) {
			// 会话被踢出了
			try {
				subject.logout();
			} catch (Exception e) {}
			saveRequest(request);

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
				WebUtils.issueRedirect(request, response, kickoutUrl);
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 获取用户同时在线最大用户数
	 * @return
	 */
	private Integer getMaxSession(){
		Integer maxSession = 1;
		List<Config> configList = configService.getConfigByType(ConfigTableConstants.TYPE_USER_LOGIN);
		if(!CollectionUtils.isEmpty(configList)){
			for(Config config : configList){
				if(ConfigTableConstants.KEY_ONLINE_MAX.equals(config.getConfigKey())) {
					maxSession = Integer.valueOf(config.getConfigValue());
				}
			}
		}
		
		return maxSession;
	}

}
