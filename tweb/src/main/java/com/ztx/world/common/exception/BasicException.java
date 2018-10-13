package com.ztx.world.common.exception;

import com.ztx.world.common.utils.ResultCodeUtil;

/**
 * 自定义基础异常
 * @author zhoutianxiang 2018年9月17日19:09:25
 * @since 1.0.0
 */
public class BasicException extends RuntimeException {

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
	
    public BasicException(){
        super();
    }

	public BasicException(String resultCode) {
		this(resultCode, ResultCodeUtil.get(resultCode));
	}
	
	public BasicException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

    public BasicException(String message, Throwable cause){
        super(message,cause);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public String toString() {
		return "BasicException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
