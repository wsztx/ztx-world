package com.ztx.world.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztx.world.base.service.LogService;
import com.ztx.world.common.config.BaseController;

@Controller
@RequestMapping(value = "/base/log")
public class LogController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(LogController.class);
	
    @Autowired
    private LogService logService;
    
}
