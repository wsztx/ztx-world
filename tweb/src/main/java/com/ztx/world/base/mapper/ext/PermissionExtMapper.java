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
	List<Permission> findPermissionsByRoleId(@Param("id") Long id);
	
}
