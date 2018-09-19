package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.common.message.ResponseMessage;

@Controller
public class HomeController {
	
	private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String getHomePage(HttpServletRequest request){
        System.out.println("Home Page");
        return "index";
    }
    
    @RequestMapping(value="/error", method=RequestMethod.POST)
    public String errorPage(HttpServletRequest request) throws Exception{
        System.out.println("Error Page");
        throw new Exception("出错啦啦啦");
    }
    
    @ResponseBody
    @RequestMapping(value="/test", method=RequestMethod.POST)
    public ResponseMessage<Object> test(HttpServletRequest request) throws Exception{
    	ResponseMessage<Object> responseData = new ResponseMessage<Object>();
    	
    	return responseData;
    }
}
