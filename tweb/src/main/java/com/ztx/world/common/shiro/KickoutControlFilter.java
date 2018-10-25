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
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.entity.ConfigExample;
import com.ztx.world.base.mapper.ConfigMapper;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.constants.ConfigTableConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.system.SpringApplicationContextUtil;
import com.ztx.world.common.utils.ResponseUtil;
import com.ztx.world.common.utils.ResultCodeUtil;

public class KickoutControlFilter extends AccessControlFilter {
	// 踢出后到的地址
	private String kickoutUrl;
	// 踢出之前登录的/之后登录的用户 ,默认踢出之前登录的用户
	private boolean kickoutAfter = false;

	private SessionManager sessionManager;
	
	private Cache<String, Deque<Serializable>> cache;

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
		this.cache = cacheManager.getCache(ConfigConstants.SHIRO_KICKOUT_SESSION);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, 
			Object mappedValue) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 最大同时在线数
		Integer maxSession = 1;
		ConfigMapper configMapper = SpringApplicationContextUtil.getBean("configMapper", ConfigMapper.class);
		ConfigExample example = new ConfigExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
				.andConfigTypeEqualTo(ConfigTableConstants.ConfigUserLogin.TYPE_USER_LOGIN)
				.andConfigKeyEqualTo(ConfigTableConstants.ConfigUserLogin.KEY_ONLINE_MAX);
		List<Config> configList = configMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(configList)){
			maxSession = Integer.valueOf(configList.get(0).getConfigValue());
		}
		
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}

		Session session = subject.getSession();
		CustomSession customSession = (CustomSession) subject.getPrincipal();
		String usercode = customSession.getUser().getUserCode();
		Serializable sessionId = session.getId();

		// 同步控制
		Deque<Serializable> deque = cache.get(usercode);
		if (deque == null) {
			deque = new LinkedList<Serializable>();
			cache.put(usercode, deque);
		}

		// 如果队列里没有此sessionId,且用户没有被踢出,放入队列
		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
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

			HttpServletRequest httpRequest = WebUtils.toHttp(request);
			HttpServletResponse httpResponse = WebUtils.toHttp(response);
			String requestType = httpRequest.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(requestType)) {
	        	BaseResponse responseData = new BaseResponse();
	        	responseData.setSuccess(false);
	        	responseData.setCode(ResultCode.SHIRO_KICKOUT_ERROR);
	        	responseData.setMessage(ResultCodeUtil.get(ResultCode.SYS_OPERATION_SUCCESS));
	        	ResponseUtil.writeJson(httpResponse, responseData);
				return false;
			} else {
				WebUtils.issueRedirect(request, response, kickoutUrl);
				return false;
			}
		}

		return true;
	}

}
