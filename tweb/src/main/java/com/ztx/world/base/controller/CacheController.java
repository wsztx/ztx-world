package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.service.CacheService;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;

@Controller
@RequestMapping(value = "/base/cache")
public class CacheController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private CacheService cacheService;
	
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:cache", "base:cache:clear"}, logical = Logical.AND)
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public BaseResponse clear(HttpServletRequest request, HttpServletResponse response){
    	cacheService.clearCache();
        return success();
    }
}
