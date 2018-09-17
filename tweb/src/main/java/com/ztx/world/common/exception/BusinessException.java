package com.ztx.world.common.exception;

/**
 * 业务错误
 * @author zhoutianxiang 2018年9月17日19:09:25
 * @since 1.0.0
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6415098413164210639L;

	/**
	 * 错误编码
	 */
	private String errorCode;
	
	/**
	 * 错误信息
	 */
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "BusinessException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
