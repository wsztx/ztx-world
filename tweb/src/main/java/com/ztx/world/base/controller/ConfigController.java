package com.ztx.world.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.base.service.ConfigService;
import com.ztx.world.base.vo.ConfigVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;

@Controller
@RequestMapping(value = "/base/config")
public class ConfigController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private ConfigService configService;

	@RequiresPermissions(value = {"base:config:tolist"})
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/config/list";
    }
	
	@RequiresPermissions(value = {"base:config:toadd"})
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/config/add";
    }
	
	@RequiresPermissions(value = {"base:config:toedit"})
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/config/edit";
    }
	
	@RequiresPermissions(value = {"base:config:toview"})
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/config/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base:config:save"})
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(HttpServletRequest request, HttpServletResponse response,
    		ConfigVo config) throws Exception{
		if(config == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigType())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置类型不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigKey())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置键不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigValue())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置值不能为空!");
		}
    	Long id = configService.saveConfig(config);
		if(id == null){
			throw new BasicException(ResultCode.SYS_OPERATION_FAILED, "新增配置失败!");
		}
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:config:update"})
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(HttpServletRequest request, HttpServletResponse response, 
    		ConfigVo config) throws Exception{
		if(config == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空!");
		}
		if(config.getId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置数据不存在!");
		}
		if(StringUtils.isEmpty(config.getConfigType())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置类型不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigKey())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置键不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigValue())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置值不能为空!");
		}
    	Long id = configService.updateConfig(config);
		if(id == null){
			throw new BasicException(ResultCode.SYS_OPERATION_FAILED, "修改配置失败!");
		}
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:config:page"})
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:config:delete"})
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public BaseResponse delete(HttpServletRequest request, HttpServletResponse response, 
    		List<Long> ids) throws Exception{
    	configService.deleteConfig(ids);
    	return success();
    }
}
