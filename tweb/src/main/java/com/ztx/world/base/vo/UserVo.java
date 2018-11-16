package com.ztx.world.base.vo;

import java.util.List;

import com.ztx.world.base.entity.User;

public class UserVo extends User{
	
	List<Long> roleIds;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
	
}
