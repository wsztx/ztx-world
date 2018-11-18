package com.ztx.world.common.constants;

/**
 * 系统提示code
 * @author zhoutianxiang 2018年9月17日19:38:05
 * @since 1.0.0
 */
public class ResultCode {

	/**
	 * 操作成功
	 */
	public static final Integer SYS_OPERATION_SUCCESS = 1;
	
	/**
	 * 操作失败
	 */
	public static final Integer SYS_OPERATION_FAILED = 0;
	
	/**
	 * 参数异常
	 */
	public static final Integer BASE_ARG_ERROR = 10001;
	
	/**
	 * 数据异常
	 */
	public static final Integer BASE_DATA_ERROR = 10002;
	
	/**
	 * 权限异常
	 */
	public static final Integer BASE_AUTH_ERROR = 10003;
	
	/**
	 * 用户被强制退出
	 */
	public static final Integer SHIRO_KICKOUT_ERROR = 10004;
	
	/**
	 * 信息更新,重新登录
	 */
	public static final Integer DATA_UPDATE_ERROR = 10005;
}
