package com.ztx.world.common.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpInterceptorAdapter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
	    String path = request.getContextPath();
	    String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
	    		+ request.getServerPort() + path;
		modelAndView.addObject("base", basePath);
		modelAndView.addObject("loginUser", request.getSession().getAttribute("loginUser"));
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
