package com.ztx.world.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.Permission;
import com.ztx.world.base.entity.Role;
import com.ztx.world.base.entity.RoleExample;
import com.ztx.world.base.entity.RolePermission;
import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserRole;
import com.ztx.world.base.mapper.PermissionMapper;
import com.ztx.world.base.mapper.RoleMapper;
import com.ztx.world.base.mapper.RolePermissionMapper;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.mapper.UserRoleMapper;
import com.ztx.world.base.mapper.ext.PermissionExtMapper;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.common.utils.MD5Util;
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
	private static final String DB_DATA_XML = "init/data/";
	
	@Autowired
	private ConfigMapper configMapper;
	
	@Autowired
	private ConfigExtMapper configExtMapper;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Autowired
	private DictionaryExtMapper dictionaryExtMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private PermissionExtMapper permissionExtMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private RedisOperator redisOperator;
	
    @Autowired
    private DataSourceTransactionManager transactionManager;

	@Override
	public void cacheInit() {
    	// 配置数据初始化
		configCacheInit();
    	// 字典数据初始化
		dictionaryCacheInit();
	}

	@Override
	public void dbInit() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// 事物隔离级别,开启新事务
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		// 获得事务状态
		TransactionStatus status = transactionManager.getTransaction(def);
		if(permissionInit() && roleInit() && userInit() && configInit() && dictionaryInit()){
			transactionManager.commit(status);
			log.info("数据初始化成功.");
		}else{
			transactionManager.rollback(status);
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
	
	private boolean permissionInit(){
        try {
			SAXReader reader = new SAXReader();
			String Path = DataInitServiceImpl.class.getClassLoader().getResource(DB_DATA_XML + "permission.xml").toURI().getPath();
			File file = new File(Path);
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			List<Element> list = root.elements();
			if(!CollectionUtils.isEmpty(list)){
				for(Element element : list){
					Permission data = new Permission();
					data.setCreateTime(new Date());
					data.setUpdateTime(new Date());
					data.setStatus(BaseConstants.UNDELETE_STATUS);
					data.setPermissionCode(element.attributeValue("permissionCode"));
					data.setParentCode(element.attributeValue("parentCode"));
					data.setPermissionName(element.attributeValue("permissionName"));
					data.setPermissionValue(element.attributeValue("permissionValue"));
					permissionMapper.insertSelective(data);
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error("权限初始化失败.", e);
			return false;
		}
	}
	
	private boolean roleInit(){
        try {
			SAXReader reader = new SAXReader();
			String Path = DataInitServiceImpl.class.getClassLoader().getResource(DB_DATA_XML + "role.xml").toURI().getPath();
			File file = new File(Path);
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			List<Element> list = root.elements();
			if(!CollectionUtils.isEmpty(list)){
				for(Element element : list){
					Role data = new Role();
					data.setCreateTime(new Date());
					data.setUpdateTime(new Date());
					data.setStatus(BaseConstants.UNDELETE_STATUS);
					data.setRoleCode(element.attributeValue("roleCode"));
					data.setRoleName(element.attributeValue("roleName"));
					data.setDescription(element.attributeValue("description"));
					roleMapper.insertSelective(data);
					
					// 如果是超级管理员，加上所有权限
					if("SuperAdmin".equals(data.getRoleCode())){
						List<Long> ids = permissionExtMapper.findAllIds();
						if(!CollectionUtils.isEmpty(ids)){
							for(Long id : ids){
								RolePermission rp = new RolePermission();
								rp.setPermissionId(id);
								rp.setRoleId(data.getId());
								rolePermissionMapper.insertSelective(rp);
							}
						}
					}
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error("角色初始化失败.", e);
			return false;
		}
	}
	
	private boolean userInit(){
        try {
			SAXReader reader = new SAXReader();
			String Path = DataInitServiceImpl.class.getClassLoader().getResource(DB_DATA_XML + "user.xml").toURI().getPath();
			File file = new File(Path);
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			List<Element> list = root.elements();
			if(!CollectionUtils.isEmpty(list)){
				for(Element element : list){
					User data = new User();
					data.setCreateTime(new Date());
					data.setUpdateTime(new Date());
					data.setStatus(BaseConstants.UNDELETE_STATUS);
					data.setUserCode(element.attributeValue("userCode"));
					data.setUserName(element.attributeValue("userName"));
					data.setPassword(MD5Util.md5(element.attributeValue("password")));
					data.setUserStatus(Integer.valueOf(element.attributeValue("userStatus")));
					data.setSessionVersion(Long.valueOf(element.attributeValue("sessionVersion")));
					data.setLastLoginTime(new Date());
					data.setDescription(element.attributeValue("description"));
					userMapper.insertSelective(data);
					
					// 如果是超级管理员,给他分配超管角色
					if("SuperAdmin".equals(data.getUserCode())){
						RoleExample roleExample = new RoleExample();
						roleExample.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andRoleCodeEqualTo("SuperAdmin");
						List<Role> roles = roleMapper.selectByExample(roleExample);
						if(!CollectionUtils.isEmpty(roles)){
							UserRole ur = new UserRole();
							ur.setRoleId(roles.get(0).getId());
							ur.setUserId(data.getId());
							userRoleMapper.insertSelective(ur);
						}
					}
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error("用户初始化失败.", e);
			return false;
		}
	}
	
	private boolean configInit(){
        try {
			SAXReader reader = new SAXReader();
			String Path = DataInitServiceImpl.class.getClassLoader().getResource(DB_DATA_XML + "config.xml").toURI().getPath();
			File file = new File(Path);
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			List<Element> list = root.elements();
			if(!CollectionUtils.isEmpty(list)){
				for(Element element : list){
					Config data = new Config();
					data.setCreateTime(new Date());
					data.setUpdateTime(new Date());
					data.setStatus(BaseConstants.UNDELETE_STATUS);
					data.setConfigType(element.attributeValue("configType"));
					data.setConfigKey(element.attributeValue("configKey"));
					data.setConfigName(element.attributeValue("configName"));
					data.setConfigValue(element.attributeValue("configValue"));
					data.setUseStatus(Integer.valueOf(element.attributeValue("useStatus")));
					data.setDescription(element.attributeValue("description"));
					configMapper.insertSelective(data);
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error("配置初始化失败.", e);
			return false;
		}
	}
	
	private boolean dictionaryInit(){
        try {
			SAXReader reader = new SAXReader();
			String Path = DataInitServiceImpl.class.getClassLoader().getResource(DB_DATA_XML + "dictionary.xml").toURI().getPath();
			File file = new File(Path);
			Document document = reader.read(file);
			Element root = document.getRootElement();
			
			List<Element> list = root.elements();
			if(!CollectionUtils.isEmpty(list)){
				for(Element element : list){
					Dictionary data = new Dictionary();
					data.setCreateTime(new Date());
					data.setUpdateTime(new Date());
					data.setStatus(BaseConstants.UNDELETE_STATUS);
					data.setDictionaryType(element.attributeValue("dictionaryType"));
					data.setDictionaryKey(element.attributeValue("dictionaryKey"));
					data.setDictionaryName(element.attributeValue("dictionaryName"));
					data.setDictionaryValue(element.attributeValue("dictionaryValue"));
					data.setDescription(element.attributeValue("description"));
					dictionaryMapper.insertSelective(data);
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error("字典初始化失败.", e);
			return false;
		}
	}
}
