package com.ztx.world.base.vo;

import com.ztx.world.base.entity.Department;

public class DepartmentVo extends Department {

	@Override
	public String toString() {
		return "DepartmentVo [getId()=" + getId() + ", getStatus()=" + getStatus() + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getDeptCode()=" + getDeptCode() + ", getDeptName()=" + getDeptName() + ", getDescription()="
				+ getDescription() + ", getParentId()=" + getParentId() + "]";
	}

}
