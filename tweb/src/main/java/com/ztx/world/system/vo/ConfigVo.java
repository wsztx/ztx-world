package com.ztx.world.system.vo;

import com.ztx.world.system.entity.Config;

public class ConfigVo extends Config {

	/**
	 * 
	 */
	private static final long serialVersionUID = 974341292796729716L;

	@Override
	public String toString() {
		return "ConfigVo [getId()=" + getId() + ", getStatus()=" + getStatus() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getConfigType()=" + getConfigType() + ", getConfigKey()=" + getConfigKey() + ", getConfigName()="
				+ getConfigName() + ", getConfigValue()=" + getConfigValue() + ", getUseStatus()=" + getUseStatus()
				+ ", getDescription()=" + getDescription() + "]";
	}

}
