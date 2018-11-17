package com.ztx.world.common.utils;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.base.mapper.ext.UserExtMapper;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.redis.RedisCacheManager;
import com.ztx.world.common.shiro.RedisSessionDAO;

@Component
public class ShiroUtil {
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Autowired
	private RedisSessionDAO sessionDAO;
	
	@Autowired
	private UserExtMapper userExtMapper;
	
	/**
	 * 获取Subject
	 * @return
	 */
	public Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public CustomSession getCurrentSession(){
		return (CustomSession)getSubject().getPrincipal();
	}
    
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
     * 清除指定用户集合的权限缓存
     * @param userCode
     */
    public void clearAuthCache(List<String> userCodes){
    	if(CollectionUtils.isEmpty(userCodes)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            		CustomSession mySession = (CustomSession)principalCollection.getPrimaryPrincipal();
            		if(userCodes.contains(mySession.getUserCode())){
            			redisCacheManager.getCache("com.ztx.world.common.shiro.ShiroRealm.authorizationCache").remove(principalCollection);
            		}
            	}
        	}
    	}
    }
    
    /**
     * 删除所有session
     */
    public void deleteAllSession(){
    	Collection<Session> sessions = sessionDAO.getActiveSessions();
    	if(!CollectionUtils.isEmpty(sessions)){
        	for(Session session : sessions){
    			Subject subject = new Subject.Builder().sessionId(session.getId()).buildSubject();
    			subject.logout();
        	}
    	}
    }
    
    /**
     * 删除指定session
     * @param userCode
     */
    public void deleteSession(String userCode){
    	if(!StringUtils.isEmpty(userCode)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            		CustomSession mySession = (CustomSession)principalCollection.getPrimaryPrincipal();
            		if(userCode.equals(mySession.getUserCode())){
            			// 此方法只会退出用户,不会删除权限
//            			session.setTimeout(0);
//            			sessionDAO.update(session);
            			Subject subject = new Subject.Builder().sessionId(session.getId()).buildSubject();
            			subject.logout();
            		}
            	}
        	}
    	}
    }
    
    /**
     * 删除指定集合session
     * @param userCode
     */
    public void deleteSession(List<String> userCodes){
    	if(CollectionUtils.isEmpty(userCodes)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            		CustomSession mySession = (CustomSession)principalCollection.getPrimaryPrincipal();
            		if(userCodes.contains(mySession.getUserCode())){
            			Subject subject = new Subject.Builder().sessionId(session.getId()).buildSubject();
            			subject.logout();
            		}
            	}
        	}
    	}
    }
    
    /**
     * 更新指定用户session
     * @param userCode
     */
    public void updateSession(String userCode){
    	if(!StringUtils.isEmpty(userCode)){
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	if(!CollectionUtils.isEmpty(sessions)){
            	for(Session session : sessions){
            		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            		CustomSession customSession = (CustomSession)principalCollection.getPrimaryPrincipal();
                	CustomSession newSession = userExtMapper.getSessionByUserId(customSession.getUserId());
                	if(newSession != null){
                    	Subject subject = new Subject.Builder().sessionId(session.getId()).buildSubject();
                    	SimplePrincipalCollection principals = new SimplePrincipalCollection(newSession, subject.getPrincipals().getRealmNames().iterator().next());
                    	subject.runAs(principals);
                	}
            	}
        	}
    	}
    }
    
    /**
     * 更新当前用户session
     * @param userCode
     */
	public void updateCurrentSession(){
    	Subject subject = getSubject();
    	CustomSession customSession = (CustomSession)subject.getPrincipal();
    	CustomSession newSession = userExtMapper.getSessionByUserId(customSession.getUserId());
    	if(newSession != null){
        	SimplePrincipalCollection principals = new SimplePrincipalCollection(newSession, subject.getPrincipals().getRealmNames().iterator().next());
        	getSubject().runAs(principals);
    	}
    }
}
