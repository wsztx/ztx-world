package com.ztx.world.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.common.config.BaseAction;
import com.ztx.world.common.config.ResponseData;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;

@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseAction {
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
    
    @RequestMapping(value="/test1", method=RequestMethod.GET)
    public String test1(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	model.addAttribute("listData", "listData123");
    	model.addAttribute("data", "data123");
    	
    	// 发生异常，由GlobalExceptionHandler统一处理
    	int avg = 100/0;
    	System.out.println(avg);
    	
    	return "index";
    }
    
    @ResponseBody
    @RequestMapping(value="/test2", method=RequestMethod.GET)
    public ResponseData test2(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	String test = "";
    	List<String> list = new ArrayList<String>();
    	// 字符串工具类
    	if(StringUtils.isEmpty(test)){
    		log.debug("test is empty.");
    	}
    	// 集合工具类
    	if(CollectionUtils.isEmpty(list)){
    		log.debug("list is empty.");
    		// 手动抛出的异常
    		throw new BasicException(ResultCode.LOGIN_FAIL);
    	}
    	return success("传到后台的数据");
    }
}
