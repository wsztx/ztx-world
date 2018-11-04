package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Dictionary;

public interface DictionaryService {

	/**
	 * 根据字典类型或取所有数据
	 * @return
	 */
	List<Dictionary> getDictionaryByType(String dictionaryType);
}
