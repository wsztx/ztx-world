package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.vo.UserVo;

/**
 * 用户服务接口
 * @author zhoutianxiang 2018年9月14日15:46:46
 * @since 1.0.0
 */
public interface UserService {
	
	/**
	 * 删除用户
	 * @param ids
	 */
	void deleteUser(List<Long> ids);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	Long saveUser(UserVo user);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	Long updateUser(UserVo user);
}
