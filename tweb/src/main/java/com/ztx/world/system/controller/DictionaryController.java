package com.ztx.world.system.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztx.world.common.config.BaseController;
import com.ztx.world.common.config.BaseResponse;
import com.ztx.world.system.service.DictionaryService;
import com.ztx.world.system.vo.DictionaryVo;

@Controller
@RequestMapping(value = "/base/dictionary")
public class DictionaryController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(DictionaryController.class);
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:tolist"}, logical = Logical.AND)
    @RequestMapping(value="/tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception{
    	
    	return "system/dictionary/list";
    }
	
	@RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:toadd"}, logical = Logical.AND)
    @RequestMapping(value="/toadd", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception{
    	
    	return "system/dictionary/add";
    }
	
	@RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:toedit"}, logical = Logical.AND)
    @RequestMapping(value="/toedit", method = RequestMethod.GET)
    public String toEdit(Model model) throws Exception{
    	
    	return "system/dictionary/add";
    }
	
	@RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:toview"}, logical = Logical.AND)
    @RequestMapping(value="/toview", method = RequestMethod.GET)
    public String toView(Model model) throws Exception{
    	
    	return "system/dictionary/view";
    }
	
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:save"}, logical = Logical.AND)
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseResponse save(DictionaryVo dictionary) throws Exception{
    	Long id = dictionaryService.saveDictionary(dictionary);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:update"}, logical = Logical.AND)
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseResponse update(DictionaryVo dictionary) throws Exception{
    	Long id = dictionaryService.updateDictionary(dictionary);
    	return success(id);
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:page"}, logical = Logical.AND)
    @RequestMapping(value="/page", method = RequestMethod.GET)
    public BaseResponse page() 
    		throws Exception{
    	
    	return success();
    }
    
    @ResponseBody
    @RequiresPermissions(value = {"system", "system:dictionary", "system:dictionary:delete"}, logical = Logical.AND)
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam List<Long> ids) throws Exception{
    	dictionaryService.deleteDictionary(ids);
    	return success();
    }
}
