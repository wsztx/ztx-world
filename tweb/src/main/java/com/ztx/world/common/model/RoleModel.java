package com.ztx.world.common.model;

import java.io.Serializable;

public class RoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 368561929380576062L;

	private Long roleId;
	
	private String roleCode;
	
	private String roleName;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
