package com.ztx.world.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.entity.Role;
import com.ztx.world.base.mapper.ext.RoleExtMapper;
import com.ztx.world.base.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleExtMapper roleExtMapper;

	@Override
	public List<Role> findRolesByUserId(Long id) {
		List<Role> list = roleExtMapper.findRolesByUserId(id);
		return list;
	}

}
