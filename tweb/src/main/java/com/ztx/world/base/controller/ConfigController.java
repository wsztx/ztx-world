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

import com.ztx.world.base.service.ConfigService;
import com.ztx.world.base.vo.ConfigVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;

@Controller
@RequestMapping(value = "/base/config")
public class ConfigController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private ConfigService configService;

	@RequiresPermissions(value = {"base", "base:config", "base:config:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "base/config/list";
    }
	
	@RequiresPermissions(value = {"base", "base:config", "base:config:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "base/config/add";
    }
	
	@RequiresPermissions(value = {"base", "base:config", "base:config:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "base/config/edit";
    }
	
	@RequiresPermissions(value = {"base", "base:config", "base:config:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "base/config/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:config", "base:config:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(ConfigVo config) throws Exception{
    	Long id = configService.saveConfig(config);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:config", "base:config:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(ConfigVo config) throws Exception{
    	Long id = configService.updateConfig(config);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:config", "base:config:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(ConfigVo config) throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:config", "base:config:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	configService.deleteConfig(ids);
    	return success();
    }
}
