package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Permission;

public interface PermissionService {

	/**
	 * 通过角色id获取权限
	 * @param id
	 * @return
	 */
	List<Permission> findPermissionByRoleId(Long id);
}
