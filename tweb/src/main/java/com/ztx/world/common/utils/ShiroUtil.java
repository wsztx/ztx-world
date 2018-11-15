package com.ztx.world.common.utils;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
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
     * 清除所有用户权限缓存
     */
    public void clearAllAuthCache(){
    	Collection<Session> sessions = sessionDAO.getActiveSessions();
    	if(!CollectionUtils.isEmpty(sessions)){
        	for(Session session : sessions){
        		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        		redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm.authorizationCache").remove(principalCollection);
        	}
    	}
    }
    
    /**
     * 清除指定用户的权限缓存
     * @param userCode
     */
    public void clearAuthCache(String userCode){
    	if(!StringUtils.isEmpty(userCode)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            		CustomSession mySession = (CustomSession)principalCollection.getPrimaryPrincipal();
            		if(userCode.equals(mySession.getUserCode())){
            			redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm.authorizationCache").remove(principalCollection);
            		}
            	}
        	}
    	}
    }
    
    /**
     * 踢出所有用户
     */
    public void clearAllUser(){
    	Collection<Session> sessions = sessionDAO.getActiveSessions();
    	if(!CollectionUtils.isEmpty(sessions)){
        	for(Session session : sessions){
        		sessionDAO.delete(session);
        	}
    	}
    }
    
    /**
     * 踢出指定用户
     * @param userCode
     */
    public void clearUser(String userCode){
    	if(!StringUtils.isEmpty(userCode)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            		CustomSession mySession = (CustomSession)principalCollection.getPrimaryPrincipal();
            		if(userCode.equals(mySession.getUserCode())){
            			sessionDAO.delete(session);
            		}
            	}
        	}
    	}
    }
}
