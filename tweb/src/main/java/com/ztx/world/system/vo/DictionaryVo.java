package com.ztx.world.system.vo;

import com.ztx.world.system.entity.Dictionary;

public class DictionaryVo extends Dictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280034678114201630L;

	@Override
	public String toString() {
		return "DictionaryVo [getId()=" + getId() + ", getStatus()=" + getStatus() + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getDictionaryType()=" + getDictionaryType() + ", getDictionaryKey()=" + getDictionaryKey()
				+ ", getDictionaryName()=" + getDictionaryName() + ", getDictionaryValue()=" + getDictionaryValue()
				+ ", getDescription()=" + getDescription() + "]";
	}

}
