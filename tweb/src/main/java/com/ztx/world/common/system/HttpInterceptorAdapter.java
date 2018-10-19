package com.ztx.world.common.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ztx.world.base.entity.CustomSession;
import com.ztx.world.common.redis.RedisClient;

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
		CustomSession customSession = new CustomSession();
		modelAndView.addObject("custom.session", customSession);
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
