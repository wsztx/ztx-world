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

import com.ztx.world.base.service.DictionaryService;
import com.ztx.world.base.vo.DictionaryVo;
import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;

@Controller
@RequestMapping(value = "/base/dictionary")
public class DictionaryController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(DictionaryController.class);
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequiresPermissions(value = {"base:dictionary:tolist"})
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dictionary/list";
    }
	
	@RequiresPermissions(value = {"base:dictionary:toadd"})
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dictionary/add";
    }
	
	@RequiresPermissions(value = {"base:dictionary:toedit"})
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dictionary/edit";
    }
	
	@RequiresPermissions(value = {"base:dictionary:toview"})
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(HttpServletRequest request, HttpServletResponse response, 
    		Model model) throws Exception{
    	
    	return "base/dictionary/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"base:dictionary:save"})
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(HttpServletRequest request, HttpServletResponse response, 
    		DictionaryVo dictionary) throws Exception{
    	Long id = dictionaryService.saveDictionary(dictionary);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:dictionary:update"})
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(HttpServletRequest request, HttpServletResponse response, 
    		DictionaryVo dictionary) throws Exception{
    	Long id = dictionaryService.updateDictionary(dictionary);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:dictionary:page"})
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"base:dictionary:delete"})
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(HttpServletRequest request, HttpServletResponse response, 
    		List<Long> ids) throws Exception{
    	dictionaryService.deleteDictionary(ids);
    	return success();
    }
}
