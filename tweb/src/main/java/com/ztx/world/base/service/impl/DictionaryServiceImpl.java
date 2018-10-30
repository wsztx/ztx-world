package com.ztx.world.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.entity.Dictionary;
import com.ztx.world.base.entity.DictionaryExample;
import com.ztx.world.base.mapper.DictionaryMapper;
import com.ztx.world.base.service.DictionaryService;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.redis.RedisOperator;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getDictionaryByType(String dictionaryType) {
		String cacheType = "dictionary." + dictionaryType;
		List<Dictionary> dictionaryList = null;
		if(redisOperator.hasKey(cacheType)){
			dictionaryList = (List<Dictionary>)redisOperator.get(cacheType);
		}else{
			DictionaryExample example = new DictionaryExample();
			example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
					.andDictionaryTypeEqualTo(dictionaryType);
			dictionaryList = dictionaryMapper.selectByExample(example);
			redisOperator.set(cacheType, dictionaryList);
		}
		return dictionaryList;
	}

}
