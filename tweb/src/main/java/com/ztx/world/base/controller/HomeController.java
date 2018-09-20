package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.message.ResponseMessage;

@Controller
public class HomeController {
	
	private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String getHomePage(HttpServletRequest request){
        System.out.println("Home Page");
        return "index";
    }
    
    @RequestMapping(value="/test1", method=RequestMethod.GET)
    public String test1(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	model.addAttribute("listData", "");
    	model.addAttribute("data", "");
    	
    	// 发生异常，由GlobalHandlerExceptionResolver统一处理
    	int avg = 100/0;
    	System.out.println(avg);
    	
    	return "index";
    }
    
    @ResponseBody
    @RequestMapping(value="/test2", method=RequestMethod.POST)
    public ResponseMessage<Object> test2(HttpServletRequest request, HttpServletResponse response){
    	ResponseMessage<Object> responseData;
		try {
			responseData = new ResponseMessage<Object>();
			
			
		} catch (Exception e) {
			responseData = new ResponseMessage<Object>(ResultCode.SYS_OPERATION_FAILED);
			log.error("异常,模板方法.", e);
		}
    	
    	return responseData;
    }
}
