package com.ztx.world.common.config;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.ztx.world.common.enums.ResultEnum;

public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465994090441876991L;

	/**
	 * 返回码
	 */
	private Integer code;
	
	/**
	 * 返回信息
	 */
	private String message;
	
	/**
	 * 返回数据
	 */
	private Object data;
	
	/**
	 * 是否成功
	 */
	private Boolean success;
	
	public BaseResponse() {
		super();
	}
	
    public BaseResponse(Boolean success, ResultEnum renum) {
		super();
		this.success = success;
		this.code = renum.getCode();
		this.message = renum.getMessage();
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String toJSONString() {
        return JSON.toJSONString(this);
    }

	@Override
	public String toString() {
		return toJSONString();
	}
	
}
