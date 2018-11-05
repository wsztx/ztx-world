package com.ztx.world.base.mapper.ext;

import java.util.List;

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
}
