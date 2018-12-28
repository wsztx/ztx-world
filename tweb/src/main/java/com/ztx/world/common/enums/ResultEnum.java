package com.ztx.world.common.enums;

/**
 * 操作结果枚举类
 * @author ztx
 *
 */
public enum ResultEnum {
	
	OPERATION_SUCCESS(1, "操作成功."),
	OPERATION_FAILED(0, "操作失败."),
	BASE_ARG_ERROR(10001, "参数异常."),
	BASE_DATA_ERROR(10002, "数据异常."),
	BASE_AUTH_ERROR(10003, "权限异常."),
	SHIRO_KICKOUT_ERROR(10004, "用户被强制退出."),
	DATA_UPDATE_ERROR(10005, "用户信息更细.");
	
	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	private Integer code;
	
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static String getMessage(Integer code){
		String message = "";
		if(code != null){
			for (ResultEnum e : ResultEnum.values()) {
				if(e.getCode().equals(code)){
					message = e.getMessage();
					break;
				}
			}
		}
		return message;
	}
}
