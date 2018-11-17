package com.ztx.world.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.service.CacheService;
import com.ztx.world.common.redis.RedisOperator;

@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private RedisOperator redisOperator;

	@Override
	public void clearCache() {
		redisOperator.clear();
	}
	
}
