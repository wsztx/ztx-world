package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Log;

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
	
	/**
	 * 新增日志
	 * @param log
	 * @return
	 */
	Long saveLog(Log log);
}
