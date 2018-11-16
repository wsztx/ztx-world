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
	
	/**
	 * 修改用户的角色
	 * @param id
	 * @param roleIds
	 */
	void saveUserRole(Long id, List<Long> roleIds);
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 */
	void updatePassword(Long id, String oldPassword, String newPassword);
	
	/**
	 * 重置密码
	 * @param id
	 */
	void resetPassword(Long id);
}
