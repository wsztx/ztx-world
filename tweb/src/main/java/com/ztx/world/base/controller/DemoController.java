package com.ztx.world.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.entity.User;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.utils.ShiroUtil;

@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private ShiroUtil shiroUtil;
    
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
    public BaseResponse test2(HttpServletRequest request, HttpServletResponse response) 
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
    		throw new BasicException(ResultCode.BASE_ARG_ERROR);
    	}
    	return success("传到后台的数据");
    }
    
    @ResponseBody
    @RequestMapping(value="/test3", method=RequestMethod.GET)
    public BaseResponse test3(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	User user = new User();
    	user.setId(123L);
    	user.setUserName("测试");
    	return success(user);
    }
    
    @ResponseBody
    @RequestMapping(value="/test4", method=RequestMethod.GET)
    public BaseResponse test4(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	shiroUtil.updateCurrentSession();
    	return success();
    }
}
