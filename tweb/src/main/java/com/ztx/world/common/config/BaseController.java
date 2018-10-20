package com.ztx.world.common.config;

import com.alibaba.fastjson.JSONObject;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResultCodeUtil;

public class BaseController {
	
	protected BaseResponse result(Boolean success, Integer code, String message, Object data){
		BaseResponse result = new BaseResponse();
        result.setCode(code);
        result.setData(data);
        result.setSuccess(success);
        result.setMessage(message);
        return result;
	}
	
	protected BaseResponse success(){
		return result(true, ResultCode.SYS_OPERATION_SUCCESS, ResultCodeUtil.get(ResultCode.SYS_OPERATION_SUCCESS), null);
	}
	
	protected BaseResponse success(String message){
		return result(true, ResultCode.SYS_OPERATION_SUCCESS, message, null);
	}

	protected BaseResponse success(Object data){
		return result(true, ResultCode.SYS_OPERATION_SUCCESS, ResultCodeUtil.get(ResultCode.SYS_OPERATION_SUCCESS), JSONObject.toJSONString(data));
	}
	
	protected BaseResponse error(Integer code){
		return result(false, code, ResultCodeUtil.get(code), null);
	}
	
	protected BaseResponse error(Integer code, String message){
		return result(false, code, message, null);
	}
}
