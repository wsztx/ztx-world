package com.ztx.world.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;

@Controller
@RequestMapping(value = "/base/user")
public class UserController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/tologin", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request){
    	Subject currentUser = SecurityUtils.getSubject();
    	if (currentUser.isAuthenticated()){
    		return "redirect:/index";
    	}else{
        	log.info("Login Page.");
            return "login";
    	}
    }
    
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(HttpServletRequest request, HttpServletResponse response, 
    		UserVo user){
    	userService.login(user);
        return success();
    }
    
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return success();
    }
    
	@RequiresPermissions(value = {"base:user:tolist"})
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/user/list";
    }
	
	@RequiresPermissions(value = {"base:user:toadd"})
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/user/add";
    }
	
	@RequiresPermissions(value = {"base:user:toedit"})
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/user/edit";
    }
	
	@RequiresPermissions(value = {"base:user:toview"})
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/user/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base:user:save"})
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(HttpServletRequest request, HttpServletResponse response, 
    		UserVo user) throws Exception{
		Long id = userService.saveUser(user);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:user:update"})
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(HttpServletRequest request, HttpServletResponse response, 
    		UserVo user) throws Exception{
		Long id = userService.updateUser(user);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:user:page"})
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:user:delete"})
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(HttpServletRequest request, HttpServletResponse response, 
    		List<Long> ids) throws Exception{
    	userService.deleteUser(ids);
    	return success(ids);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:user:saveuserrole"})
    @RequestMapping(value="/saveuserrole", method = RequestMethod.POST)
    public BaseResponse distributeRole(HttpServletRequest request, HttpServletResponse response, 
    		UserVo user) throws Exception{
		if(user == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		Long id = user.getId();
		List<Long> roleIds = user.getRoleIds();
		userService.saveUserRole(id, roleIds);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:user:updatepassword"})
    @RequestMapping(value="/updatepassword", method = RequestMethod.POST)
    public BaseResponse updatePassword(HttpServletRequest request, HttpServletResponse response, 
    		UserVo user) throws Exception{
		if(user == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空!");
		}
		Long id = user.getId();
		userService.updatePassword(id, user.getOldPassword(), user.getNewPassword());
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:user:resetpassword"})
    @RequestMapping(value="/resetpassword", method = RequestMethod.POST)
    public BaseResponse resetPassword(HttpServletRequest request, HttpServletResponse response, 
    		UserVo user) throws Exception{
		if(user == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空!");
		}
		Long id = user.getId();
		userService.resetPassword(id);
    	return success(id);
    }
}
