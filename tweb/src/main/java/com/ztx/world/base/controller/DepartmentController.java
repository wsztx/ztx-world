package com.ztx.world.base.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequiresPermissions(value = {"base", "base:department", "base:department:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "base/department/list";
    }
	
	@RequiresPermissions(value = {"base", "base:department", "base:department:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "base/department/add";
    }
	
	@RequiresPermissions(value = {"base", "base:department", "base:department:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "base/department/edit";
    }
	
	@RequiresPermissions(value = {"base", "base:department", "base:department:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "base/department/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:department", "base:department:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(DepartmentVo department) throws Exception{
    	Long id = departmentService.saveDepartment(department);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:department", "base:department:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(DepartmentVo department) throws Exception{
    	Long id = departmentService.updateDepartment(department);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:department", "base:department:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page() 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:department", "base:department:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	departmentService.deleteDepartment(ids);
    	return success(ids);
    }
}
