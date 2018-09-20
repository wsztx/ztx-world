package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.constants.Constants;

@Controller
@RequestMapping(value = "/base/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, 
    		User user){
    	UserVo vo = new UserVo();
    	user = new User();
    	user.setId(1);
    	user.setOrgId(1);
    	user.setStatus(Constants.UNDELETE_STATUS);
    	user.setUserName("张三");
    	vo.setUser(user);
    	request.getSession().setAttribute(Constants.LOGIN_SESSION, vo);
        return "base/user/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, User user){
    	userService.updateUser(user);
        return "base/user/edit";
    }
}
