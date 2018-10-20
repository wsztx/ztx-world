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
	private Integer code;
	
	/**
	 * 错误信息
	 */
	private String msg;
	
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BasicException(){
        super();
    }

	public BasicException(Integer code) {
		this(code, ResultCodeUtil.get(code));
	}
	
	public BasicException(Integer code, String message) {
		super(message);
		this.code = code;
		this.msg = message;
	}

    public BasicException(String message, Throwable cause){
        super(message, cause);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public String toString() {
		return "BasicException [errorCode=" + code + ", errorMessage=" + msg + "]";
	}
	
}
