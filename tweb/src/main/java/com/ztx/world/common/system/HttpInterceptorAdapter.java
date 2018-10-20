package com.ztx.world.common.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.redis.RedisClient;
import com.ztx.world.common.shiro.ShiroToken;
import com.ztx.world.common.utils.MD5Util;

public class HttpInterceptorAdapter extends HandlerInterceptorAdapter {
	
	@Autowired
	private RedisClient redisClient;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView == null){
			modelAndView = new ModelAndView();
		}
	    String path = request.getContextPath();
	    String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
	    		+ request.getServerPort() + path;
		modelAndView.addObject("base", basePath);
		CustomSession customSession = new CustomSession();
		Subject currentUser = SecurityUtils.getSubject();
		// 如果已经登录
        if (currentUser.isAuthenticated()) {
        	customSession = (CustomSession)currentUser.getPrincipal();
        }
		modelAndView.addObject("custom.session", customSession);
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
