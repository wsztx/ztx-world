package com.ztx.world.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.message.ResponseMessage;

@Controller
@RequestMapping(value = "/base/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/toLogin", method=RequestMethod.GET)
    public String toLogin(HttpServletRequest request){
        System.out.println("Login Page");
        return "login";
    }
    
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMessage<User> login(HttpServletRequest request, HttpServletResponse response, 
    		UserVo vo){
    	ResponseMessage<User> responseData;
		try {
	        // 获取当前的Subject
	        Subject currentUser = SecurityUtils.getSubject();
	        // 测试当前的用户是否已经被认证，即是否已经登陆
	        // 调用Subject的isAuthenticated
	        if (!currentUser.isAuthenticated()) {
	            // 把用户名和密码封装为UsernamePasswordToken 对象
	            UsernamePasswordToken token = new UsernamePasswordToken(
	            		vo.getUser().getLoginName(), vo.getUser().getPassword());
	            token.setRememberMe(true);
                // 执行登陆
                currentUser.login(token);
	        }
	        
	        responseData = new ResponseMessage<User>(ResultCode.LOGIN_SUCCESS);
		} catch (Exception e) {
			responseData = new ResponseMessage<User>(ResultCode.SYS_OPERATION_FAILED);
			log.error("登录异常.", e);
		}
    	
    	return responseData;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, User user){
    	userService.updateUser(user);
        return "base/user/edit";
    }
}
