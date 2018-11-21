package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.Log;
import com.ztx.world.base.entity.LogExample;
import com.ztx.world.base.mapper.LogMapper;
import com.ztx.world.base.service.LogService;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;

	@Override
	public void deleteLog(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			for(Long id : ids){
				Log log = new Log();
				log.setId(id);
				log.setStatus(-id);
				logMapper.updateByPrimaryKeySelective(log);
			}
		}
	}

	@Override
	public void clearLog() {
		LogExample example = new LogExample();
		logMapper.deleteByExample(example);
	}

	@Override
	public Long saveLog() {
		CustomSession session = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		Log log = new Log();
		log.setStatus(BaseConstants.UNDELETE_STATUS);
		log.setOperateTime(new Date());
		return null;
	}
}
