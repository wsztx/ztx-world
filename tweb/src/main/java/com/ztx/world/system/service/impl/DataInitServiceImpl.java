package com.ztx.world.system.service.impl;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.system.entity.Config;
import com.ztx.world.system.entity.ConfigExample;
import com.ztx.world.system.entity.Dictionary;
import com.ztx.world.system.entity.DictionaryExample;
import com.ztx.world.system.mapper.ConfigMapper;
import com.ztx.world.system.mapper.DictionaryMapper;
import com.ztx.world.system.mapper.ext.ConfigExtMapper;
import com.ztx.world.system.mapper.ext.DictionaryExtMapper;
import com.ztx.world.system.service.DataInitService;

@Service
public class DataInitServiceImpl implements DataInitService {
	
	private static Logger log = LoggerFactory.getLogger(DataInitServiceImpl.class);
	
	/**
	 * 初始化数据xml路径
	 */
	private static final String DB_DATA_XML = "META-INF/init/data/";
	
	@Autowired
	private ConfigMapper configMapper;
	
	@Autowired
	private ConfigExtMapper configExtMapper;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Autowired
	private DictionaryExtMapper dictionaryExtMapper;
	
	@Autowired
	private RedisOperator redisOperator;

	@Override
	public void cacheInit() {
    	// 配置数据初始化
		configCacheInit();
    	// 字典数据初始化
		dictionaryCacheInit();
	}

	@Override
	public void dbInit() {
		if(roleInit()){
			if(userInit()){
				if(permissionInit()){
					if(configInit()){
						if(dictionaryInit()){
							log.info("数据初始化成功.");
						}
					}
				}
			}
		}
	}

	
	
	private void configCacheInit(){
		List<String> typeList = configExtMapper.findConfigType();
		if(!CollectionUtils.isEmpty(typeList)){
			for(String type : typeList){
				ConfigExample example = new ConfigExample();
				example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andConfigTypeEqualTo(type);
				List<Config> list = configMapper.selectByExample(example);
				if(!CollectionUtils.isEmpty(list)){
					String cacheKey = ConfigConstants.CONFIG_PRE + type;
					redisOperator.set(cacheKey, list);
				}
			}
		}
	}
	
	private void dictionaryCacheInit(){
		List<String> typeList = dictionaryExtMapper.findDictionaryType();
		if(!CollectionUtils.isEmpty(typeList)){
			for(String type : typeList){
				DictionaryExample example = new DictionaryExample();
				example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andDictionaryTypeEqualTo(type);
				List<Dictionary> list = dictionaryMapper.selectByExample(example);
				if(!CollectionUtils.isEmpty(list)){
					String cacheKey = ConfigConstants.DICTIONARY_PRE + type;
					redisOperator.set(cacheKey, list);
				}
			}
		}
	}
	
	private boolean roleInit(){
        try {
			SAXReader reader = new SAXReader();
			File file = new File(DB_DATA_XML + "role.xml");
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			return true;
		} catch (Exception e) {
			log.error("角色初始化失败.", e);
			return false;
		}
	}
	
	private boolean userInit(){
        try {
			SAXReader reader = new SAXReader();
			File file = new File(DB_DATA_XML + "user.xml");
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			return true;
		} catch (Exception e) {
			log.error("用户初始化失败.", e);
			return false;
		}
	}
	
	private boolean permissionInit(){
        try {
			SAXReader reader = new SAXReader();
			File file = new File(DB_DATA_XML + "permission.xml");
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			return true;
		} catch (Exception e) {
			log.error("权限初始化失败.", e);
			return false;
		}
	}
	
	private boolean configInit(){
        try {
			SAXReader reader = new SAXReader();
			File file = new File(DB_DATA_XML + "config.xml");
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			return true;
		} catch (Exception e) {
			log.error("配置初始化失败.", e);
			return false;
		}
	}
	
	private boolean dictionaryInit(){
        try {
			SAXReader reader = new SAXReader();
			File file = new File(DB_DATA_XML + "dictionary.xml");
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			return true;
		} catch (Exception e) {
			log.error("字典初始化失败.", e);
			return false;
		}
	}
}
