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
import com.ztx.world.common.utils.SerializeUtil;

@Component
public class RedisSessionDAO extends AbstractSessionDAO {
	
	private static Logger log = LoggerFactory.getLogger(SerializeUtil.class);

    @Autowired
    private RedisManager redisManager;
    
    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
        	log.info("Session is null.");
            return;
        }
        redisManager.hdelete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        List<Session> list = redisManager.hmget();
        return list;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session == null || session.getId() == null){
        	log.info("Session is null.");
            return;
        }
        Serializable sessionId = session.getId();
        redisManager.hadd(sessionId.toString(), session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        // 添加进redis
        redisManager.hadd(sessionId.toString(), session);
        
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return redisManager.hget(sessionId.toString());
    }
}
