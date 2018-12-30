package com.ztx.world.base.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztx.world.base.entity.Role;
import com.ztx.world.base.entity.User;

public interface RoleExtMapper {

	/**
	 * 根据用户id查询角色信息
	 * @param id
	 * @return
	 */
	List<Role> findRoleByUserId(@Param("id") Long id);
	
	/**
	 * 根据角色id获取所有登录名
	 * @param id
	 * @return
	 */
	List<String> findUserCodeByRoleId(@Param("id") Long id);
	
	/**
	 * 根据角色id获取用户集合
	 * @param id
	 * @return
	 */
	List<User> findUserByRoleId(@Param("id") Long id);
}
