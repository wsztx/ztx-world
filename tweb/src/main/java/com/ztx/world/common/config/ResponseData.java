package com.ztx.world.common.config;

import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResultCodeUtil;

public class ResponseData{

	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回信息
	 */
	private String message;
	
	/**
	 * 返回数据
	 */
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.setMessage(ResultCodeUtil.get(code));
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setCodeAndMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ResponseData(String resultCode) {
		this(resultCode, null);
	}

	public boolean hasData() {
		return (data != null);
	}

	public ResponseData() {
		this(ResultCode.SYS_OPERATION_SUCCESS, null);
	}

	public ResponseData(String code, Object data) {
		this.code = code;
		this.message = ResultCodeUtil.get(this.code);
		this.data = data;
	}
	
	public ResponseData(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", message=" + message 
				+ ", data=" + (data == null ? "" : data.toString()) + "]";
	}
	
}
