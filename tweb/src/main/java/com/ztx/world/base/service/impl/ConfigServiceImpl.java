package com.ztx.world.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.entity.ConfigExample;
import com.ztx.world.base.mapper.ConfigMapper;
import com.ztx.world.base.service.ConfigService;
import com.ztx.world.common.constants.BaseConstants;
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

}
