package com.ztx.world.base.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztx.world.base.entity.Permission;

public interface PermissionExtMapper {

	/**
	 * 根据角色id查询权限信息
	 * @param id
	 * @return
	 */
	List<Permission> findPermissionByRoleId(@Param("id") Long id);
	
	/**
	 * 获取所有权限id
	 * @return
	 */
	List<Long> findAllIds();
}
