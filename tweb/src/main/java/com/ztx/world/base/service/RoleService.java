package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Role;

public interface RoleService {

	/**
	 * 通过用户id获取角色
	 * @param id
	 * @return
	 */
	List<Role> findRolesByUserId(Long id);
}
