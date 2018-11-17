package com.ztx.world.common.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.entity.ConfigExample;
import com.ztx.world.base.entity.Dictionary;
import com.ztx.world.base.entity.DictionaryExample;
import com.ztx.world.base.mapper.ConfigMapper;
import com.ztx.world.base.mapper.DictionaryMapper;
import com.ztx.world.base.mapper.ext.ConfigExtMapper;
import com.ztx.world.base.mapper.ext.DictionaryExtMapper;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.redis.RedisOperator;

/**
 * 数据初始化
 * @author ztx
 *
 */
@Component
public class DataInitListener implements ApplicationListener<ContextRefreshedEvent> {
	
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
	public void onApplicationEvent(ContextRefreshedEvent event) {
        // 防止重复执行。
        if(event.getApplicationContext().getParent() == null){
        	// 配置数据初始化
        	configDataInit();
        	// 字典数据初始化
        	dictionaryDataInit();
        }
	}

	private void configDataInit(){
		List<String> typeList = configExtMapper.findConfigTypes();
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
	
	private void dictionaryDataInit(){
		List<String> typeList = dictionaryExtMapper.findDictionaryTypes();
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
}
