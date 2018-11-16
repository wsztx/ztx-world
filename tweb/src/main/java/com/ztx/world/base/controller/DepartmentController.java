package com.ztx.world.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.service.DepartmentService;
import com.ztx.world.base.vo.DepartmentVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;

@Controller
@RequestMapping(value = "/base/department")
public class DepartmentController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequiresPermissions(value = {"base:department:tolist"})
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/department/list";
    }
	
	@RequiresPermissions(value = {"base:department:toadd"})
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/department/add";
    }
	
	@RequiresPermissions(value = {"base:department:toedit"})
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/department/edit";
    }
	
	@RequiresPermissions(value = {"base:department:toview"})
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/department/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base:department:save"})
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(HttpServletRequest request, HttpServletResponse response, 
    		DepartmentVo department) throws Exception{
    	Long id = departmentService.saveDepartment(department);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:department:update"})
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(HttpServletRequest request, HttpServletResponse response, 
    		DepartmentVo department) throws Exception{
    	Long id = departmentService.updateDepartment(department);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:department:page"})
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:department:delete"})
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public BaseResponse delete(HttpServletRequest request, HttpServletResponse response, 
    		List<Long> ids) throws Exception{
    	departmentService.deleteDepartment(ids);
    	return success(ids);
    }
}
