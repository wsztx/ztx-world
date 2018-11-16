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
	 * REDIS_SESSION
	 */
	public static final String REDIS_SESSION = "RedisSession";
	
	/**
	 * shiro_kickout_session
	 */
	public static final String SHIRO_KICKOUT_SESSION = "ShiroKickoutSession";
	
	/**
	 * 禁止直接访问的页面
	 */
	public static final String FORBID_VIEW = "/web/";
}
