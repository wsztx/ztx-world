package com.ztx.world.base.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.model.RoleModel;

public interface UserExtMapper {

	/**
	 * 根据用户id获取登录的session信息
	 * @param id
	 * @return
	 */
	CustomSession getSessionByUserId(Long id);
	
	/**
	 * 获取role集合
	 * @param id
	 * @return
	 */
	List<RoleModel> findRoleModelList(Long id);
	
	/**
	 * 根据用户ids获取所有的用户登录名
	 * @param ids
	 * @return
	 */
	List<String> findUserCodesByIds(@Param("ids")List<Long> ids);
}
