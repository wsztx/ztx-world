package com.ztx.world.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.ztx.world.common.constants.ResultCode;

@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private static Logger log = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object obj, Exception exception) {
		String requestType = request.getHeader("X-Requested-With");
		ModelAndView modelAndView = new ModelAndView();
    	if("XMLHttpRequest".equals(requestType)){  
	        // 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常
	        FastJsonJsonView view = new FastJsonJsonView();
	        Map<String, Object> modelMap = new HashMap<String, Object>();
	        modelMap.put("code", ResultCode.SYS_OPERATION_FAILED);
	        modelMap.put("message", exception.getMessage());
	        view.setAttributesMap(modelMap);
	        modelAndView.setView(view);
    	}else{
    		Map<String, Object> modelMap = new HashMap<String, Object>();
    		modelMap.put("code", ResultCode.SYS_OPERATION_FAILED);
    		modelMap.put("message", exception.getMessage());
    		modelAndView.addAllObjects(modelMap);
    		modelAndView.setViewName("redirect:/web/error/error.jsp");
    	}
    	
    	log.debug("occurred exception.", exception);
    	return modelAndView;
	}

}
