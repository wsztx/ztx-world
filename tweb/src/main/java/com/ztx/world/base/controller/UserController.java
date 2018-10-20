package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.service.UserService;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.shiro.ShiroToken;
import com.ztx.world.common.utils.MD5Util;

@Controller
@RequestMapping(value = "/base/user")
public class UserController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "tologin", method=RequestMethod.GET)
    public String toLogin(HttpServletRequest request){
        System.out.println("Login Page");
        return "login";
    }
    
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(HttpServletRequest request, HttpServletResponse response, 
    		CustomSession user){
    	if(user == null || StringUtils.isEmpty(user.getUserCode()) || StringUtils.isEmpty(user.getPassword())){
    		throw new BasicException(ResultCode.BASE_ARG_ERROR);
    	}
        Subject currentUser = SecurityUtils.getSubject();
        // 当前的用户是否已经被认证,即是否已经登陆
        if (!currentUser.isAuthenticated()) {
        	ShiroToken token = new ShiroToken(user.getUserCode(), MD5Util.md5(user.getPassword()));
            //token.setRememberMe(true);
            currentUser.login(token);
        }
        return success();
    }
    
    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return success("登出成功");
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, User user){
    	userService.updateUser(user);
        return "base/user/edit";
    }
}
