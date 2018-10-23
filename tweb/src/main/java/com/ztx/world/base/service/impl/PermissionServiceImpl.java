package com.ztx.world.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.entity.Permission;
import com.ztx.world.base.mapper.ext.PermissionExtMapper;
import com.ztx.world.base.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionExtMapper permissionExtMapper;
	
	@Override
	public List<Permission> findPermissionsByRoleId(Long id) {
		List<Permission> list = permissionExtMapper.findPermissionsByRoleId(id);
		return list;
	}

}
