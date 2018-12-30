package com.ztx.world.base.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztx.world.common.config.BaseController;

@Controller
public class HomeController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequiresPermissions(value = {"index"}, logical = Logical.AND)
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index() throws Exception{
    	log.info("Home Page.");
        return "index";
    }
}
