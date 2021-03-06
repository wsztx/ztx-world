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

import com.ztx.world.base.service.OrganizationService;
import com.ztx.world.base.vo.OrganizationVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;

@Controller
@RequestMapping(value = "/base/organization")
public class OrganizationController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequiresPermissions(value = {"base", "base:organization", "base:organization:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "base/organization/list";
    }
	
	@RequiresPermissions(value = {"base", "base:organization", "base:organization:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "base/organization/add";
    }
	
	@RequiresPermissions(value = {"base", "base:organization", "base:organization:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "base/organization/add";
    }
	
	@RequiresPermissions(value = {"base", "base:organization", "base:organization:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "base/organization/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:organization", "base:organization:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(OrganizationVo organization) throws Exception{
    	Long id = organizationService.saveOrganization(organization);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:organization", "base:organization:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(OrganizationVo organization) throws Exception{
    	Long id = organizationService.updateOrganization(organization);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:organization", "base:organization:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page() 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:organization", "base:organization:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	organizationService.deleteOrganization(ids);
    	return success(ids);
    }
}
