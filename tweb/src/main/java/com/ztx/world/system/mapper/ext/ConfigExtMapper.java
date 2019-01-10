package com.ztx.world.system.mapper.ext;

import java.util.List;

public interface ConfigExtMapper {

	/**
	 * 获取所有配置类型
	 * @return
	 */
	List<String> findConfigType();
}
