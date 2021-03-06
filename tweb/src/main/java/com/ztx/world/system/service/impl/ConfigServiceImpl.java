package com.ztx.world.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.system.entity.Config;
import com.ztx.world.system.entity.ConfigExample;
import com.ztx.world.system.mapper.ConfigMapper;
import com.ztx.world.system.service.ConfigService;
import com.ztx.world.system.vo.ConfigVo;

@Service
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private ConfigMapper configMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<Config> getConfigByType(String configType) {
		String cacheType = ConfigConstants.CONFIG_PRE + configType;
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
	public String getConfigValue(String configType, String configKey) {
		String value = "";
		List<Config> configList = this.getConfigByType(configType);
		if(!CollectionUtils.isEmpty(configList)){
			for(Config c : configList){
				if(c.getConfigKey().equals(configKey)){
					value = c.getConfigValue();
					break;
				}
			}
		}
		return value;
	}

	@Override
	public Long saveConfig(ConfigVo config) {
		if(config == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigType())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置类型不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigName())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置名称不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigKey())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置键不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigValue())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置值不能为空.");
		}
		ConfigExample example = new ConfigExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andConfigTypeEqualTo(config.getConfigType())
			.andConfigKeyEqualTo(config.getConfigKey());
		int count = configMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置类型与键的组合已存在.");
		}
		config.setStatus(BaseConstants.UNDELETE_STATUS);
		config.setCreateTime(new Date());
		config.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		config.setCreateUserId(customSession.getUserId());
		configMapper.insertSelective(config);
		if(config.getId() == null){
			throw new BasicException(ResultEnum.BASE_DATA_ERROR.getCode(), "新增配置失败.");
		}
		return config.getId();
	}

	@Override
	public Long updateConfig(ConfigVo config) {
		if(config == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		if(config.getId() == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置数据不存在.");
		}
		if(StringUtils.isEmpty(config.getConfigType())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置类型不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigName())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置名称不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigKey())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置键不能为空.");
		}
		if(StringUtils.isEmpty(config.getConfigValue())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置值不能为空.");
		}
		ConfigExample example = new ConfigExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andConfigTypeEqualTo(config.getConfigType())
			.andConfigKeyEqualTo(config.getConfigKey())
			.andIdNotEqualTo(config.getId());
		int count = configMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "配置类型与键的组合已存在.");
		}
		config.setUpdateTime(new Date());
		configMapper.updateByPrimaryKeySelective(config);
		
		// 删除缓存信息
		String cacheType = ConfigConstants.CONFIG_PRE + config.getConfigType();
		if(redisOperator.hasKey(cacheType)){
			redisOperator.del(cacheType);
		}
		return config.getId();
	}

	@Override
	public void deleteConfig(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
			for(Long id : ids){
				Config config = configMapper.selectByPrimaryKey(id);
				config.setId(id);
				config.setStatus(-id);
				configMapper.updateByPrimaryKeySelective(config);
				
				// 删除缓存信息
				String cacheType = ConfigConstants.CONFIG_PRE + config.getConfigType();
				if(redisOperator.hasKey(cacheType)){
					redisOperator.del(cacheType);
				}
			}
		}
	}
	
}
