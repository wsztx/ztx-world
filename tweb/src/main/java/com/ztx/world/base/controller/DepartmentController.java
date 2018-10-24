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
@RequestMapping(value = "/base/dept")
public class DepartmentController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@RequiresPermissions(value = {"base:dept:tolist"})
    @RequestMapping(value="/tolist", method=RequestMethod.GET)
    public String toList(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dept/list";
    }
	
	@RequiresPermissions(value = {"base:dept:toadd"})
    @RequestMapping(value="/toadd", method=RequestMethod.GET)
    public String toAdd(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dept/add";
    }
	
	@RequiresPermissions(value = {"base:dept:toedit"})
    @RequestMapping(value="/toedit", method=RequestMethod.GET)
    public String toEdit(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dept/edit";
    }
	
	@RequiresPermissions(value = {"base:dept:toview"})
    @RequestMapping(value="/toview", method=RequestMethod.GET)
    public String toView(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dept/view";
    }
}
