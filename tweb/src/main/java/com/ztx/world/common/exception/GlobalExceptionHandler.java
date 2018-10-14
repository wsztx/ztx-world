package com.ztx.world.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ztx.world.common.config.ResponseData;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    /**
     * 默认处理异常页面
     */
    public static final String DEFAUL_ERROR_VIEW = "forward:/web/error/error.jsp";

    /**
     * 默认处理异常方法
     * @param request
     * @param response
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @Order(value = 99)
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, 
    		Exception exception) throws  Exception{
    	log.error("Exception occurred.", exception);
    	String requestType = request.getHeader("X-Requested-With");
    	// 如果是ajax请求
    	if("XMLHttpRequest".equals(requestType)){
        	ResponseData responseData = new ResponseData();
        	responseData.setCode(ResultCode.SYS_OPERATION_FAILED);
        	responseData.setMessage(exception.getMessage());
        	ResponseUtil.writeJson(response, responseData);
    		return null;
    	}else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("code", ResultCode.SYS_OPERATION_FAILED);                   
            modelAndView.addObject("message", exception.getMessage());   
            modelAndView.setViewName(DEFAUL_ERROR_VIEW);
            return modelAndView;
    	}
    }

    /**
     * 匹配抛出自定义的基础异常类型
     * @param request
     * @param response
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BasicException.class)
    @Order(value = 1)
    @ResponseBody
    public ResponseData basicExceptionHandler(HttpServletRequest request, HttpServletResponse response, 
    		BasicException exception) throws Exception{
    	log.error("BasicException occurred.", exception);
    	ResponseData responseData = new ResponseData();
    	responseData.setCode(exception.getErrorCode());
    	responseData.setMessage(exception.getMessage());
        return responseData;
    }

}
