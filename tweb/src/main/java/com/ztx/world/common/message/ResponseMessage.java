package com.ztx.world.common.message;

import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResultCodeUtil;

public class ResponseMessage<T>{

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
	private T data;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public void setResultCodeAndMessage(String code) {
		this.code = code;
		this.message = ResultCodeUtil.get(this.code);
	}

	public void setResultCodeAndMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ResponseMessage(String resultCode) {
		this(resultCode, null);
	}

	public boolean hasData() {
		return (data != null);
	}

	public ResponseMessage() {
		this(ResultCode.SYS_OPERATION_SUCCESS, null);
	}

	public ResponseMessage(String code, T data) {
		this.code = code;
		this.message = ResultCodeUtil.get(this.code);
		this.data = data;
	}

	@Override
	public String toString() {
		return "MessageResult [code=" + code + ", message=" + message 
				+ ", data=" + data == null ? "" : data.toString() + "]";
	}
	
}
