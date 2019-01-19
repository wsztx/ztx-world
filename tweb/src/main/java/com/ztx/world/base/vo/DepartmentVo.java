package com.ztx.world.base.vo;

import com.alibaba.fastjson.JSONObject;
import com.ztx.world.base.entity.Department;

public class DepartmentVo extends Department {

	@Override
	public String toString() {
		return "DepartmentVo:" + JSONObject.toJSONString(this);
	}

}
