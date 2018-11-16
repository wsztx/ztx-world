package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Config;
import com.ztx.world.base.vo.ConfigVo;

public interface ConfigService {

	/**
	 * 根据字典类型或取所有数据
	 * @param configType
	 * @return
	 */
	List<Config> getConfigByType(String configType);
	
	/**
	 * 获取配置值
	 * @param configType
	 * @param configKey
	 * @return
	 */
	String getConfigValue(String configType, String configKey);
	
	/**
	 * 新增配置
	 * @param config
	 * @return
	 */
	Long saveConfig(ConfigVo config);
	
	/**
	 * 修改配置
	 * @param config
	 * @return
	 */
	Long updateConfig(ConfigVo config);
	
	/**
	 * 删除配置信息
	 * @param ids
	 */
	void deleteConfig(List<Long> ids);
}
