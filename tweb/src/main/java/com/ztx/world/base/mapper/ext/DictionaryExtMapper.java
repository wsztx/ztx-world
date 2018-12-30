package com.ztx.world.base.mapper.ext;

import java.util.List;

public interface DictionaryExtMapper {

	/**
	 * 获取所有字典类型
	 * @return
	 */
	List<String> findDictionaryType();
}
