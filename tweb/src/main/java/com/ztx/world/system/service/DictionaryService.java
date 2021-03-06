package com.ztx.world.system.service;

import java.util.List;

import com.ztx.world.system.entity.Dictionary;
import com.ztx.world.system.vo.DictionaryVo;

public interface DictionaryService {

	/**
	 * 根据字典类型或取所有数据
	 * @return
	 */
	List<Dictionary> getDictionaryByType(String dictionaryType);
	
	/**
	 * 获取字典值
	 * @param dictionaryType
	 * @param dictionaryKey
	 * @return
	 */
	String getDictionaryValue(String dictionaryType, String dictionaryKey);
	
	/**
	 * 新增字典
	 * @param dictionary
	 * @return
	 */
	Long saveDictionary(DictionaryVo dictionary);
	
	/**
	 * 修改字典
	 * @param dictionary
	 * @return
	 */
	Long updateDictionary(DictionaryVo dictionary);
	
	/**
	 * 删除字典
	 * @param ids
	 */
	void deleteDictionary(List<Long> ids);
}
