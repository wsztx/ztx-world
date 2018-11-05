package com.ztx.world.common.config;

import java.io.Serializable;
import java.util.List;

import com.ztx.world.common.model.RoleModel;

/**
 * 登录的自定义session
 * @author ztx
 *
 */
public class CustomSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454459644130001348L;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 用户code
	 */
	private String userCode;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 部门id
	 */
	private Long deptId;
	
	/**
	 * 部门code
	 */
	private String deptCode;
	
	/**
	 * 部门名
	 */
	private String deptName;
	
	/**
	 * 机构id
	 */
	private Long orgId;
	
	/**
	 * 机构code
	 */
	private String orgCode;
	
	/**
	 * 机构名
	 */
	private String orgName;
	
	/**
	 * 角色集合
	 */
	private List<RoleModel> roleList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<RoleModel> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}
	
}
