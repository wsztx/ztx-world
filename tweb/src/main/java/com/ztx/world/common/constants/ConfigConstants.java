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
	public static final String DICTIONARY_PRE = "Dictionary.";
	
	/**
	 * 配置缓存
	 */
	public static final String CONFIG_PRE = "Config.";
	
	/**
	 * 用户信息版本缓存
	 */
	public static final String USER_VERSION_PRE = "UserVersion.";
	
	/**
	 * 用户登录版本缓存
	 */
	public static final String LOGIN_VERSION_PRE = "LoginVersion.";
	
	/**
	 * 用户权限版本缓存
	 */
	public static final String AUTH_VERSION_PRE = "AuthVersion.";
}
