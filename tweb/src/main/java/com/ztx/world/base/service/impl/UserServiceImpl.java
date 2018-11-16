package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.entity.UserRole;
import com.ztx.world.base.entity.UserRoleExample;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.mapper.UserRoleMapper;
import com.ztx.world.base.mapper.ext.UserExtMapper;
import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.shiro.ShiroToken;
import com.ztx.world.common.utils.MD5Util;
import com.ztx.world.common.utils.ShiroUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserExtMapper userExtMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private ShiroUtil shiroUtil;
	
	@Override
	public void login(UserVo user) {
		if(user == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(StringUtils.isEmpty(user.getUserCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户名不能为空.");
		}
		if(StringUtils.isEmpty(user.getPassword())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户密码不能为空.");
		}
        Subject currentUser = SecurityUtils.getSubject();
        // 当前的用户是否已经被认证,即是否已经登陆
        if (!currentUser.isAuthenticated()) {
        	ShiroToken token = new ShiroToken(user.getUserCode(), MD5Util.md5(user.getPassword()));
            token.setRememberMe(true);
            currentUser.login(token);
            // 修改最后登录时间
            UserExample example = new UserExample();
            example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andUserCodeEqualTo(user.getUserCode());
            User record = new User();
            record.setLastLoginTime(new Date());
            userMapper.updateByExampleSelective(record, example);
        }
	}

	@Override
	public void deleteUser(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
			CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
			for(Long id : ids){
				User user = userMapper.selectByPrimaryKey(id);
				if(user != null){
					if("SuperAdmin".equals(user.getUserCode())){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户超级管理员无法删除.");
					}
					if(customSession != null && id.equals(customSession.getUserId())){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "不能删除自己.");
					}
				}
			}
			
			// 逻辑删除用户
			for(Long id : ids){
				User user = userMapper.selectByPrimaryKey(id);
				user.setId(id);
				user.setStatus(-id);
				userMapper.updateByPrimaryKeySelective(user);
			}
			
			List<String> codes = userExtMapper.findUserCodesByIds(ids);
			// 强制将用户登出
			shiroUtil.deleteSession(codes);
		}
	}

	@Override
	public Long saveUser(UserVo user) {
		if(user == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(StringUtils.isEmpty(user.getUserCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户名不能为空.");
		}
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andUserCodeEqualTo(user.getUserCode());
		int count = userMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户名" + user.getUserCode() + "已存在.");
		}
		user.setStatus(BaseConstants.UNDELETE_STATUS);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		user.setCreateUserId(customSession.getUserId());
		// 设置用户初始密码为password
		user.setPassword(MD5Util.md5("password"));
		userMapper.insertSelective(user);
		if(user.getId() == null){
			throw new BasicException(ResultCode.BASE_DATA_ERROR, "新增用户失败.");
		}
		return user.getId();
	}

	@Override
	public Long updateUser(UserVo user) {
		if(user == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(user.getId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户不存在.");
		}
		if("SuperAdmin".equals(user.getUserCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户超级管理员无法修改.");
		}
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andUserCodeEqualTo(user.getUserCode())
			.andIdNotEqualTo(user.getId());
		int count = userMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户名" + user.getUserCode() + "已存在.");
		}
		user.setUpdateTime(new Date());
		user.setPassword(null);
		userMapper.updateByPrimaryKeySelective(user);
		return user.getId();
	}

	@Override
	public void saveUserRole(Long id, List<Long> roleIds) {
		if(id == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户不存在.");
		}
		if(CollectionUtils.isEmpty(roleIds)){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户角色不能为空.");
		}
		// 删除原有分配的角色
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);
		userRoleMapper.deleteByExample(example);
		// 插入用户角色关联表
		for(Long roleId : roleIds){
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(id);
			userRoleMapper.insertSelective(userRole);
		}
		// 删除用户的权限缓存信息
		User user = userMapper.selectByPrimaryKey(id);
		if(user != null){
			shiroUtil.clearAuthCache(user.getUserCode());
		}
	}

	@Override
	public void updatePassword(Long id, String oldPassword, String newPassword) {
		if(id == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户不存在.");
		}
		if(StringUtils.isEmpty(oldPassword)){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "原密码不能为空.");
		}
		if(StringUtils.isEmpty(newPassword)){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "新密码不能为空.");
		}
		UserExample example = new UserExample();
		example.createCriteria().andPasswordEqualTo(MD5Util.md5(oldPassword)).andIdEqualTo(id);
		int count = userMapper.countByExample(example);
		if(count == 0){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "原密码不正确.");
		}
		User record = new User();
		record.setId(id);
		record.setPassword(MD5Util.md5(newPassword));
		userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void resetPassword(Long id) {
		if(id == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户不存在.");
		}
		User user = new User();
		user.setId(id);
		user.setPassword(MD5Util.md5("password"));
		userMapper.updateByPrimaryKeySelective(user);
	}

}
