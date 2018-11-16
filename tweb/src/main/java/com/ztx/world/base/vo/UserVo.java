package com.ztx.world.base.vo;

import java.util.List;

import com.ztx.world.base.entity.User;

public class UserVo extends User{
	
	private List<Long> roleIds;
	
	private String oldPassword;
	
	private String newPassword;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
