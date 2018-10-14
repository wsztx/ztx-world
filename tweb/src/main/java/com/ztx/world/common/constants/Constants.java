package com.ztx.world.common.constants;

/**
 * 常量
 * @author zhoutianxiang
 * @since 1.0.0
 */
public class Constants {

	/**
	 * 已删除
	 */
	public static final int DELETE_STATUS = 0;
	
	/**
	 * 未删除
	 */
	public static final int UNDELETE_STATUS = 1;
	
	/**
	 * shiro的md5加密盐值
	 */
	public static final String SALT_SOURCE = "w6s8z6t8xstkw";
	
	/**
	 * 全局session名称
	 */
	public static final String GLOBAL_SESSION = "globalData";
	
	/**
	 * 登录session名称
	 */
	public static final String LOGIN_SESSION = "loginData";
	
	
	public static final class UserStatusType{
		public static final Integer USER_NORMAL = 0;
	}
}
