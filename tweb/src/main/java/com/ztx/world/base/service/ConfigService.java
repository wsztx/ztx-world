package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Config;

public interface ConfigService {

	/**
	 * 根据字典类型或取所有数据
	 * @param configType
	 * @return
	 */
	public List<Config> getConfigByType(String configType);
}
