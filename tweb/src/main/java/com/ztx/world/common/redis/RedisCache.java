package com.ztx.world.common.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.utils.SerializeUtil;

/**
 * 用redis操作实现shiro缓存接口
 * @author Administrator
 * @param <K>
 * @param <V>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisCache<K,V> implements Cache<K,V> {
 
	private RedisTemplate redisTemplate; 
	
	private String keyPrefix = ConfigConstants.SHIRO_REDIS_SESSION_PRE;
	
	public RedisCache(RedisTemplate redisTemplate) {
		super();
		//构造函数传递redisTemplate过来
		this.redisTemplate = redisTemplate;
	}
	
    public String getKeyPrefix() {
        return keyPrefix;
    }
 
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
 
    
    /**
     * 获得byte[]型的key
     * @param key
     * @return
     */
    private byte[] getByteKey(Object key){
        if(key instanceof String){
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        }else{
            return SerializeUtil.serialize((Serializable) key);
        }
    }
 
	private RedisConnection getRedisConnect() {
    	return redisTemplate.getConnectionFactory().getConnection();
    }
 
    @Override
    public Object get(Object key) throws CacheException {
        byte[] bytes = getByteKey(key);
        byte[] value = getRedisConnect().get(bytes);
        if(value == null){
            return null;
        }
        return SerializeUtil.deserialize(value);
    }
 
    /**
     * 将shiro的缓存保存到redis中
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
    	RedisConnection redisConnection = getRedisConnect();
    	redisConnection.set(getByteKey(key), SerializeUtil.serialize((Serializable)value));
        byte[] bytes = redisConnection.get(getByteKey(key));
        Object object = SerializeUtil.deserialize(bytes);
 
        return object;
 
    }
 
    @Override
    public Object remove(Object key) throws CacheException {
    	RedisConnection redisConnection = getRedisConnect();
 
        byte[] bytes = redisConnection.get(getByteKey(key));
 
        redisConnection.del(getByteKey(key));
 
        return SerializeUtil.deserialize(bytes);
    }
 
    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
    	RedisConnection redisConnection = getRedisConnect();
    	redisConnection.flushDb();
    }
 
    /**
     * 缓存的个数
     */
    @Override
    public int size() {
    	RedisConnection redisConnection = getRedisConnect();
        Long size = redisConnection.dbSize();
        return size.intValue();
    }
 
    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
    	RedisConnection redisConnection = getRedisConnect();
        Set<byte[]> keys = redisConnection.keys(new String("*").getBytes());
        Set<Object> set = new HashSet<Object>();
        for (byte[] bs : keys) {
            set.add(SerializeUtil.deserialize(bs));
        }
        return set;
    }
 
 
    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
    	RedisConnection redisConnection = getRedisConnect();
        Set keys = this.keys();
 
        List<Object> values = new ArrayList<Object>();
        for (Object key : keys) {
            byte[] bytes = redisConnection.get(getByteKey(key));
            values.add(SerializeUtil.deserialize(bytes));
        }
        return values;
    }
}
