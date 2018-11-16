package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.entity.ConfigExample;
import com.ztx.world.base.mapper.ConfigMapper;
import com.ztx.world.base.service.ConfigService;
import com.ztx.world.base.vo.ConfigVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.redis.RedisOperator;

@Service
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private ConfigMapper configMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<Config> getConfigByType(String configType) {
		String cacheType = "config." + configType;
		List<Config> configList = null;
		if(redisOperator.hasKey(cacheType)){
			configList = (List<Config>)redisOperator.get(cacheType);
		}else{
			ConfigExample example = new ConfigExample();
			example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
					.andUseStatusEqualTo(BaseConstants.USE_STATUS)
					.andConfigTypeEqualTo(configType);
			configList = configMapper.selectByExample(example);
			redisOperator.set(cacheType, configList);
		}
		return configList;
	}

	@Override
	public Long saveConfig(ConfigVo config) {
		if(config == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigType())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置类型不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置名称不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigKey())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置键不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigValue())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置值不能为空!");
		}
		config.setStatus(BaseConstants.UNDELETE_STATUS);
		config.setCreateTime(new Date());
		config.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		config.setCreateUserId(customSession.getUserId());
		configMapper.insertSelective(config);
		if(config.getId() == null){
			throw new BasicException(ResultCode.BASE_DATA_ERROR, "新增配置失败!");
		}
		return config.getId();
	}

	@Override
	public Long updateConfig(ConfigVo config) {
		if(config == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空!");
		}
		if(config.getId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置数据不存在!");
		}
		if(StringUtils.isEmpty(config.getConfigType())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置类型不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置名称不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigKey())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置键不能为空!");
		}
		if(StringUtils.isEmpty(config.getConfigValue())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "配置值不能为空!");
		}
		config.setUpdateTime(new Date());
		configMapper.updateByPrimaryKeySelective(config);
		
		// 删除缓存信息
		String cacheType = "config." + config.getConfigType();
		if(redisOperator.hasKey(cacheType)){
			redisOperator.del(cacheType);
		}
		return config.getId();
	}

	@Override
	public void deleteConfig(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			for(Long id : ids){
				Config config = configMapper.selectByPrimaryKey(id);
				config.setId(id);
				config.setStatus(BaseConstants.DELETE_STATUS);
				configMapper.updateByPrimaryKeySelective(config);
				
				// 删除缓存信息
				String cacheType = "config." + config.getConfigType();
				if(redisOperator.hasKey(cacheType)){
					redisOperator.del(cacheType);
				}
			}
		}
	}
	
}
