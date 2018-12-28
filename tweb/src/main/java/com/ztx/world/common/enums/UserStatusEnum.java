package com.ztx.world.common.enums;

/**
 * 用户状态枚举
 * @author ztx
 *
 */
public enum UserStatusEnum {

	USER_NORMAL(0, "用户正常");
	
	private UserStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	private Integer code;
	
	private String value;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getValue(Integer code){
		String value = "";
		if(code != null){
			for (UserStatusEnum e : UserStatusEnum.values()) {
				if(e.getCode().equals(code)){
					value = e.getValue();
					break;
				}
			}
		}
		return value;
	}
}
