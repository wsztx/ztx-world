package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztx.world.common.config.BaseController;

@Controller
@RequestMapping(value = "/base/config")
public class ConfigController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ConfigController.class);

	@RequiresPermissions(value = {"base:config:tolist"})
    @RequestMapping(value="/tolist", method=RequestMethod.GET)
    public String test1(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	model.addAttribute("listData", "listData123");
    	model.addAttribute("data", "data123");
    	
    	return "base/config/list";
    }
}
