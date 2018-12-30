package com.ztx.world.base.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.utils.ShiroUtil;

@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private ShiroUtil shiroUtil;
    
    @RequestMapping(value="/test1", method=RequestMethod.GET)
    public String test1(Model model) throws Exception{
    	model.addAttribute("listData", "listData123");
    	model.addAttribute("data", "data123");
    	
    	// 除0
    	int avg = 100/0;
    	System.out.println(avg);
    	
    	return "index";
    }
    
    @ResponseBody
    @RequestMapping(value="/test2", method=RequestMethod.GET)
    public BaseResponse test2() throws Exception{
    	String test = "";
    	List<String> list = new ArrayList<String>();
    	// 工具类判空
    	if(StringUtils.isEmpty(test)){
    		log.debug("test is empty.");
    	}
    	if(CollectionUtils.isEmpty(list)){
    		log.debug("list is empty.");
    		throw new BasicException(ResultEnum.BASE_ARG_ERROR);
    	}
    	return success("操作成功");
    }
    
    @ResponseBody
    @RequestMapping(value="/test3", method=RequestMethod.GET)
    public BaseResponse test3() throws Exception{
    	User user = new User();
    	user.setId(123L);
    	user.setUserName("张三");
    	return success(user);
    }
    
    @ResponseBody
    @RequestMapping(value="/test4", method=RequestMethod.GET)
    public BaseResponse test4() throws Exception{
    	shiroUtil.updateCurrentSession();
    	return success();
    }
}
