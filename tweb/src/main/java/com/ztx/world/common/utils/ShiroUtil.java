package com.ztx.world.common.utils;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.redis.RedisCacheManager;
import com.ztx.world.common.shiro.RedisSessionDAO;

@Component
public class ShiroUtil {
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Autowired
	private RedisSessionDAO sessionDAO;
    
    /**
     * 清除所有权限缓存
     */
    public void clearAllCache(){
    	redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm.authorizationCache").clear();
    }
    
    /**
     * 清除指定用户的缓存
     * @param userCode
     */
    public void clearCache(String userCode){
    	if(!StringUtils.isEmpty(userCode)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		CustomSession mySession = (CustomSession)((SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)).getPrimaryPrincipal();
            		if(userCode.equals(mySession.getUserCode())){
            			String sessionId = session.getId().toString();
            			redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm.authorizationCache").remove(sessionId);
            		}
            	}
        	}
    	}
    }
}
