package com.ztx.world.system.controller;

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

import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.system.service.ConfigService;
import com.ztx.world.system.vo.ConfigVo;

@Controller
@RequestMapping(value = "/system/config")
public class ConfigController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private ConfigService configService;

	@RequiresPermissions(value = {"system", "system:config", "system:config:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "system/config/list";
    }
	
	@RequiresPermissions(value = {"system", "system:config", "system:config:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "system/config/add";
    }
	
	@RequiresPermissions(value = {"system", "system:config", "system:config:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "system/config/add";
    }
	
	@RequiresPermissions(value = {"system", "system:config", "system:config:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "system/config/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:config", "system:config:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(ConfigVo config) throws Exception{
    	Long id = configService.saveConfig(config);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:config", "system:config:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(ConfigVo config) throws Exception{
    	Long id = configService.updateConfig(config);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:config", "system:config:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(ConfigVo config) throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:config", "system:config:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	configService.deleteConfig(ids);
    	return success();
    }
}
