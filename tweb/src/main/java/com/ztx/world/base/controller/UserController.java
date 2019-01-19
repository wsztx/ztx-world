package com.ztx.world.base.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.exception.BasicException;

@Controller
@RequestMapping(value = "/base/user")
public class UserController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/tologin", method = RequestMethod.GET)
    public String toLogin(){
    	Subject currentUser = SecurityUtils.getSubject();
    	if (currentUser.isAuthenticated()){
    		return "redirect:/index";
    	}else{
            return "login";
    	}
    }
    
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(UserVo user){
    	userService.login(user);
        return success();
    }
    
    @ResponseBody
    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public BaseResponse logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return success();
    }
    
	@RequiresPermissions(value = {"base", "base:user", "base:user:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "base/user/list";
    }
	
	@RequiresPermissions(value = {"base", "base:user", "base:user:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "base/user/add";
    }
	
	@RequiresPermissions(value = {"base", "base:user", "base:user:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "base/user/add";
    }
	
	@RequiresPermissions(value = {"base", "base:user", "base:user:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "base/user/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(UserVo user) throws Exception{
		Long id = userService.saveUser(user);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(UserVo user) throws Exception{
		Long id = userService.updateUser(user);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(UserVo user) 
    		throws Exception{
    	PageInfo<UserVo> page = userService.page(user);
    	return success(page);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	userService.deleteUser(ids);
    	return success(ids);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:saveuserrole"}, logical = Logical.AND)
    @RequestMapping(value="/saveuserrole", method = RequestMethod.POST)
    public BaseResponse saveUserRole(UserVo user) throws Exception{
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		Long id = user.getId();
		List<Long> roleIds = user.getRoleIds();
		userService.saveUserRole(id, roleIds);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:updatepassword"}, logical = Logical.AND)
    @RequestMapping(value="/updatepassword", method = RequestMethod.POST)
    public BaseResponse updatePassword(UserVo user) throws Exception{
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		Long id = user.getId();
		userService.updatePassword(id, user.getOldPassword(), user.getNewPassword());
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:resetpassword"}, logical = Logical.AND)
    @RequestMapping(value="/resetpassword", method = RequestMethod.POST)
    public BaseResponse resetPassword(UserVo user) throws Exception{
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		Long id = user.getId();
		userService.resetPassword(id);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base", "base:user", "base:user:downline"}, logical = Logical.AND)
    @RequestMapping(value="/downline", method = RequestMethod.POST)
    public BaseResponse downline(@RequestParam List<String> userCodes) throws Exception{
		userService.updateSessionVersion(ConfigConstants.LOGIN_VERSION_PRE, userCodes);
    	return success(userCodes);
    }
}
