package com.ztx.world.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ztx.world.common.redis.RedisCacheManager;

@Component
public class ShiroUtil {
	
	@Autowired
	private RedisCacheManager redisCacheManager;
    
    /**
     * 清楚所有权限缓存
     */
    public void clearAllCache(){
    	redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm").clear();
    }
    
    public void clearCache(String sessionId){
    	redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm").remove(sessionId);
    }
}
