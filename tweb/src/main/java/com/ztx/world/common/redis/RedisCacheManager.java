package com.ztx.world.common.redis;

import java.io.Serializable;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheManager implements CacheManager {

	@Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;
 
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<K,V>(redisTemplate);
    }
    
}
