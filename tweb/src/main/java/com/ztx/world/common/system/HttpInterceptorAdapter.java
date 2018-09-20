package com.ztx.world.common.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ztx.world.common.constants.Constants;

public class HttpInterceptorAdapter extends HandlerInterceptorAdapter {

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
		modelAndView.addObject(Constants.LOGIN_SESSION, 
				request.getSession().getAttribute(Constants.LOGIN_SESSION));
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
