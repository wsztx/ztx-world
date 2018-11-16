package com.ztx.world.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.mapper.OrganizationMapper;
import com.ztx.world.base.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationMapper organizationMapper;
}
