package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.entity.Role;
import com.ztx.world.base.vo.RoleVo;

public interface RoleService {

	/**
	 * 通过用户id获取角色
	 * @param id
	 * @return
	 */
	List<Role> findRolesByUserId(Long id);
	
	/**
	 * 删除角色
	 * @param ids
	 */
	void deleteRole(List<Long> ids);
	
	/**
	 * 新建角色
	 * @param role
	 * @return
	 */
	Long saveRole(RoleVo role);
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	Long updateRole(RoleVo role);
	
	/**
	 * 保存角色的权限
	 * @param id
	 * @param permissionIds
	 */
	void saveRolePermission(Long id, List<Long> permissionIds);
}
