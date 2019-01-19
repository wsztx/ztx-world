package com.ztx.world.common.config;

import com.alibaba.fastjson.JSONObject;
import com.ztx.world.common.enums.ResultEnum;

public class BaseController {
	
	protected BaseResponse result(Boolean success, Integer code, String message, Object data){
		BaseResponse result = new BaseResponse();
        result.setCode(code);
        result.setData(data == null ? null : JSONObject.toJSONString(data));
        result.setSuccess(success);
        result.setMessage(message);
        return result;
	}
	
	protected BaseResponse success(){
		return result(true, ResultEnum.OPERATION_SUCCESS.getCode(), ResultEnum.OPERATION_SUCCESS.getMessage(), null);
	}

	protected BaseResponse success(Object data){
		return result(true, ResultEnum.OPERATION_SUCCESS.getCode(), ResultEnum.OPERATION_SUCCESS.getMessage(), JSONObject.toJSONString(data));
	}
	
	protected BaseResponse error(Integer code){
		return result(false, code, ResultEnum.getMessage(code), null);
	}
	
	protected BaseResponse error(Integer code, String message){
		return result(false, code, message, null);
	}
}
