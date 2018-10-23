package com.ztx.world.base.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztx.world.base.entity.Role;

public interface RoleExtMapper {

	/**
	 * 根据用户id查询角色信息
	 * @param id
	 * @return
	 */
	List<Role> findRolesByUserId(@Param("id") Long id);
}
