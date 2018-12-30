package com.ztx.world.base.controller;

import java.util.List;

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

import com.ztx.world.base.service.LogService;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;

@Controller
@RequestMapping(value = "/base/log")
public class LogController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(LogController.class);
	
    @Autowired
    private LogService logService;
    
	@RequiresPermissions(value = {"base:log:tolist"})
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "base/log/list";
    }
	
	@RequiresPermissions(value = {"base:log:toview"})
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "base/log/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base:log:page"})
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page() 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:log:delete"})
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	logService.deleteLog(ids);
    	return success(ids);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:log:clear"})
    @RequestMapping(value="/clear", method = RequestMethod.POST)
    public BaseResponse clear() throws Exception{
    	logService.clearLog();
    	return success();
    }
}
