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

import com.ztx.world.base.service.RoleService;
import com.ztx.world.base.vo.RoleVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.exception.BasicException;

@Controller
@RequestMapping(value = "/base/role")
public class RoleController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;

	@RequiresPermissions(value = {"base", "base:role", "base:role:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "base/role/list";
    }
	
	@RequiresPermissions(value = {"base", "base:role", "base:role:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "base/role/add";
    }
	
	@RequiresPermissions(value = {"base", "base:role", "base:role:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "base/role/add";
    }
	
	@RequiresPermissions(value = {"base", "base:role", "base:role:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "base/role/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:role", "base:role:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(RoleVo role) throws Exception{
		Long id = roleService.saveRole(role);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:role", "base:role:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(RoleVo role) throws Exception{
		Long id = roleService.updateRole(role);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:role", "base:role:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page() throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:role", "base:role:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	roleService.deleteRole(ids);
    	return success(ids);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:role", "base:role:saverolepermission"}, logical = Logical.AND)
    @RequestMapping(value="/saverolepermission", method = RequestMethod.POST)
    public BaseResponse saveRolePermission(RoleVo role) throws Exception{
		if(role == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		Long id = role.getId();
		List<Long> permissionIds = role.getPermissionIds();
		roleService.saveRolePermission(id, permissionIds);
    	return success(id);
    }
}
