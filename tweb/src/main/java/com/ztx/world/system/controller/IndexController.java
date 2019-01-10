package com.ztx.world.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztx.world.common.config.BaseController;

@Controller
public class IndexController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index() throws Exception{
    	log.info("Welcome To Home Page.");
        return "index";
    }
	
}
