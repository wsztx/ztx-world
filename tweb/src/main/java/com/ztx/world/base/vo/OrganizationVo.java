package com.ztx.world.base.vo;

import com.alibaba.fastjson.JSONObject;
import com.ztx.world.base.entity.Organization;

public class OrganizationVo extends Organization {

	@Override
	public String toString() {
		return "OrganizationVo:" + JSONObject.toJSONString(this);
	}

}
