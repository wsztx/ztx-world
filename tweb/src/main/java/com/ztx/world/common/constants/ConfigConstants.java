package com.ztx.world.common.constants;

/**
 * 配置类型常量
 * @author ztx
 *
 */
public class ConfigConstants {

	/**
	 * shiro的md5加密盐值
	 */
	public static final String SALT_SOURCE = "w6s8z6t8xstkw";
	
	/**
	 * ShiroRedisSession
	 */
	public static final String SHIRO_REDIS_SESSION_PRE = "ShiroRedisSession:";
	
	/**
	 * RedisSession
	 */
	public static final String REDIS_SESSION = "RedisSession";
	
	/**
	 * ShiroKickoutSession
	 */
	public static final String SHIRO_KICKOUT_SESSION = "ShiroKickoutSession";
	
	/**
	 * 字典缓存
	 */
	public static final String DICTIONARY_PRE = "dictionary.";
	
	/**
	 * 配置缓存
	 */
	public static final String CONFIG_PRE = "config.";
	
	/**
	 * 用户版本缓存
	 */
	public static final String VERSION_PRE = "version.";
	
	/**
	 * 用户强退缓存
	 */
	public static final String LOGOUT_PRE = "logout.";
}
