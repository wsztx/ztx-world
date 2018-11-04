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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;

@Controller
@RequestMapping(value = "/base/role")
public class RoleController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(RoleController.class);

	@RequiresPermissions(value = {"base:role:tolist"})
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/role/list";
    }
	
	@RequiresPermissions(value = {"base:role:toadd"})
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/role/add";
    }
	
	@RequiresPermissions(value = {"base:role:toedit"})
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/role/edit";
    }
	
	@RequiresPermissions(value = {"base:role:toview"})
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/role/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base:role:save"})
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:role:update"})
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:role:page"})
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:role:delete"})
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public BaseResponse delete(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
}
