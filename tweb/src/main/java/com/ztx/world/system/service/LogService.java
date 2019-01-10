package com.ztx.world.system.service;

import java.util.List;

public interface LogService {

	/**
	 * 删除日志
	 * @param ids
	 */
	void deleteLog(List<Long> ids);
	
	/**
	 * 清空数据库逻辑删除的日志
	 */
	void clearLog();
	
}
