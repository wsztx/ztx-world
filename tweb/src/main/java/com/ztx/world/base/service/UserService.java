package com.ztx.world.base.service;

import com.ztx.world.base.entity.User;

/**
 * 用户服务接口
 * @author zhoutianxiang 2018年9月14日15:46:46
 * @since 1.0.0
 */
public interface UserService {

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
}
