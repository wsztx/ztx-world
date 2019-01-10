package com.ztx.world.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.system.entity.Log;
import com.ztx.world.system.entity.LogExample;
import com.ztx.world.system.mapper.LogMapper;
import com.ztx.world.system.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;

	@Override
	public void deleteLog(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
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
		example.createCriteria().andStatusNotEqualTo(BaseConstants.UNDELETE_STATUS);
		logMapper.deleteByExample(example);
	}
	
}
