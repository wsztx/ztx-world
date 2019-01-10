package com.ztx.world.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztx.world.common.config.BaseController;

@Controller
@RequestMapping(value = "/error")
public class ErrorController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(ErrorController.class);
	
    @RequestMapping(value="/error", method=RequestMethod.GET)
    public String error() throws Exception{
        return "error/error";
    }
}
