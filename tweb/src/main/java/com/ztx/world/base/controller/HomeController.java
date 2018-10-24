package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;

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

	@RequiresPermissions(value = {"index"})
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception{
    	log.info("Home Page.");
        return "index";
    }
}
