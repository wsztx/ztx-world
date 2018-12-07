package com.ztx.world.base.vo;

import com.ztx.world.base.entity.Organization;

public class OrganizationVo extends Organization {

	@Override
	public String toString() {
		return "OrganizationVo [getId()=" + getId() + ", getStatus()=" + getStatus() + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getOrgCode()=" + getOrgCode() + ", getOrgName()=" + getOrgName() + ", getDescription()="
				+ getDescription() + ", getOrgPath()=" + getOrgPath() + ", getParentId()=" + getParentId() + "]";
	}

}
