package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.service.UserService;

@Controller
@RequestMapping(value = "/base/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, User user){
    	userService.updateUser(user);
        return "base/user/edit";
    }
}
