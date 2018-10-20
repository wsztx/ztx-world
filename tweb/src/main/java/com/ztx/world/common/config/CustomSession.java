package com.ztx.world.common.config;

import java.io.Serializable;

import com.ztx.world.base.entity.Department;
import com.ztx.world.base.entity.Organization;
import com.ztx.world.base.entity.User;

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
	
	private User user;
	
	private Department dept;
	
	private Organization org;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
		
}
