package com.ztx.world.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztx.world.common.config.BaseController;

@Controller
@RequestMapping(value = "/base/org")
public class OrganizationController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(OrganizationController.class);
}
