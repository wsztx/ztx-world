package com.ztx.world.base.vo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ztx.world.base.entity.User;

public class UserVo extends User{
	
	private List<Long> roleIds;
	
	private String oldPassword;
	
	private String newPassword;
	
	private boolean rememberMe;
	
	private int pageNum = 1;
	
	private int pageSize = 20;

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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "UserVo:" + JSONObject.toJSONString(this);
	}
	
}
