package com.ztx.world.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object obj, Exception exception) {
		String requestType = request.getHeader("X-Requested-With");
    	response.setStatus(413);
    	response.addHeader("Error-Json", "{\"code\":413,\"msg\":\"nopermission\"}");  
    	response.setContentType("text/html;charset=utf-8");  
    	if("XMLHttpRequest".equals(requestType)){  
    		return new ModelAndView();  
    	}
    	return new ModelAndView("redirect:/error/error.jsp");
	}

}
