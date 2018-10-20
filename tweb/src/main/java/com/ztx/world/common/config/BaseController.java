package com.ztx.world.common.config;

import com.alibaba.fastjson.JSONObject;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResultCodeUtil;

public class BaseController {
	
	protected ResponseData success(){
		ResponseData response = new ResponseData();
		response.setSuccess(true);
		response.setCode(ResultCode.SYS_OPERATION_SUCCESS);
		response.setMessage(ResultCodeUtil.get(ResultCode.SYS_OPERATION_SUCCESS));
		return response;
	}
	
	protected ResponseData success(String message){
		ResponseData response = new ResponseData();
		response.setSuccess(true);
		response.setCode(ResultCode.SYS_OPERATION_SUCCESS);
		response.setMessage(message);
		return response;
	}

	protected ResponseData success(Object data){
		ResponseData response = new ResponseData();
		response.setSuccess(true);
		response.setCode(ResultCode.SYS_OPERATION_SUCCESS);
		response.setMessage(ResultCodeUtil.get(ResultCode.SYS_OPERATION_SUCCESS));
		response.setData(JSONObject.toJSONString(data));
		return response;
	}
	
	protected ResponseData error(Integer code){
		ResponseData response = new ResponseData();
		response.setSuccess(false);
		response.setCode(code);
		response.setMessage(ResultCodeUtil.get(code));
		return response;
	}
	
	protected ResponseData error(Integer code, String message){
		ResponseData response = new ResponseData();
		response.setSuccess(false);
		response.setCode(code);
		response.setMessage(message);
		return response;
	}
}
