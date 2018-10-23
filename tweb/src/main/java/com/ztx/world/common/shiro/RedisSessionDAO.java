package com.ztx.world.common.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ztx.world.common.redis.RedisManager;

@Component
public class RedisSessionDAO extends AbstractSessionDAO {
	
	private static Logger log = LoggerFactory.getLogger(RedisSessionDAO.class);

    @Autowired
    private RedisManager redisManager;
    
    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
        	log.debug("Session is null.");
            return;
        }
        Serializable sessionId = session.getId(); 
        log.debug("Delete session,id = {}.", sessionId.toString());
        redisManager.hdelete(sessionId.toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        List<Session> list = redisManager.hmget();
        return list;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session == null || session.getId() == null){
        	log.debug("Session is null.");
            return;
        }
        Serializable sessionId = session.getId();
        log.debug("Update session,id = {}.", sessionId.toString());
        redisManager.hadd(sessionId.toString(), session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        log.debug("Create session,id = {}.", sessionId.toString());
        assignSessionId(session, sessionId);
        redisManager.hadd(sessionId.toString(), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
    	log.debug("Read session,id = {}.", sessionId.toString());
        return redisManager.hget(sessionId.toString());
    }
}
