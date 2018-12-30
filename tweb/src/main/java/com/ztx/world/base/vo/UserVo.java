package com.ztx.world.base.vo;

import java.util.List;

import com.ztx.world.base.entity.User;

public class UserVo extends User{
	
	private List<Long> roleIds;
	
	private String oldPassword;
	
	private String newPassword;
	
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

	@Override
	public String toString() {
		return "UserVo [getId()=" + getId() + ", getStatus()=" + getStatus() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getUserCode()=" + getUserCode() + ", getUserName()=" + getUserName() + ", getPassword()="
				+ getPassword() + ", getOrgId()=" + getOrgId() + ", getDeptId()=" + getDeptId() + ", getDescription()="
				+ getDescription() + ", getLastLoginTime()=" + getLastLoginTime() + ", getUserStatus()="
				+ getUserStatus() + ", getSessionVersion()=" + getSessionVersion() + "]";
	}

}
