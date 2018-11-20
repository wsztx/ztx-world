package com.ztx.world.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.mapper.LogMapper;
import com.ztx.world.base.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;
}
