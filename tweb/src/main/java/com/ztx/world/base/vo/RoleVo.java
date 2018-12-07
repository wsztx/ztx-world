package com.ztx.world.base.vo;

import java.util.List;

import com.ztx.world.base.entity.Role;

public class RoleVo extends Role {

	private List<Long> permissionIds;

	public List<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}

	@Override
	public String toString() {
		return "RoleVo [permissionIds=" + permissionIds + ", getId()=" + getId() + ", getStatus()=" + getStatus()
				+ ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()=" + getUpdateTime()
				+ ", getCreateUserId()=" + getCreateUserId() + ", getRoleCode()=" + getRoleCode() + ", getRoleName()="
				+ getRoleName() + ", getDescription()=" + getDescription() + "]";
	}
	
	
}
