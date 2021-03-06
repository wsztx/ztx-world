package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztx.world.base.entity.Role;
import com.ztx.world.base.entity.RoleExample;
import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.entity.UserRole;
import com.ztx.world.base.entity.UserRoleExample;
import com.ztx.world.base.mapper.RoleMapper;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.mapper.UserRoleMapper;
import com.ztx.world.base.mapper.ext.UserExtMapper;
import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.common.shiro.ShiroToken;
import com.ztx.world.common.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserExtMapper userExtMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Override
	public void login(UserVo user) {
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		if(StringUtils.isEmpty(user.getUserCode())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户名不能为空.");
		}
		if(StringUtils.isEmpty(user.getPassword())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户密码不能为空.");
		}
        Subject currentUser = SecurityUtils.getSubject();
        // 当前的用户是否已经被认证,即是否已经登陆
        if (!currentUser.isAuthenticated()) {
        	ShiroToken token = new ShiroToken(user.getUserCode(), MD5Util.md5(user.getPassword()));
            token.setRememberMe(user.isRememberMe());
            currentUser.login(token);
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
						throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户超级管理员无法删除.");
					}
					if(customSession != null && id.equals(customSession.getUserId())){
						throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "不能删除自己.");
					}
				}
			}
			
			// 逻辑删除用户
			for(Long id : ids){
				User user = userMapper.selectByPrimaryKey(id);
				user.setId(id);
				user.setStatus(-id);
				userMapper.updateByPrimaryKeySelective(user);
				// 通知缓存session版本
				redisOperator.set(ConfigConstants.LOGIN_VERSION_PRE + user.getUserCode(), user.getSessionVersion());
			}
		}
	}

	@Override
	public Long saveUser(UserVo user) {
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		if(StringUtils.isEmpty(user.getUserCode())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户名不能为空.");
		}
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andUserCodeEqualTo(user.getUserCode());
		int count = userMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户名" + user.getUserCode() + "已存在.");
		}
		user.setStatus(BaseConstants.UNDELETE_STATUS);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setSessionVersion(new Date().getTime());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		user.setCreateUserId(customSession.getUserId());
		// 设置用户初始密码为password
		user.setPassword(MD5Util.md5("password"));
		userMapper.insertSelective(user);
		if(user.getId() == null){
			throw new BasicException(ResultEnum.BASE_DATA_ERROR.getCode(), "新增用户失败.");
		}
		// 分配默认角色普通用户
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andRoleCodeEqualTo("OrdinaryUser");
		List<Role> roleList = roleMapper.selectByExample(roleExample);
		if(!CollectionUtils.isEmpty(roleList)){
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleList.get(0).getId());
			userRole.setUserId(user.getId());
			userRoleMapper.insertSelective(userRole);
		}
		// 通知缓存session版本
		redisOperator.set(ConfigConstants.USER_VERSION_PRE + user.getUserCode(), user.getSessionVersion());
		return user.getId();
	}

	@Override
	public Long updateUser(UserVo user) {
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
		}
		if(user.getId() == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户不存在.");
		}
		if(StringUtils.isEmpty(user.getUserCode())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户名不能为空.");
		}
		if("SuperAdmin".equals(user.getUserCode())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户超级管理员无法修改.");
		}
		String userCode = user.getUserCode();
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andUserCodeEqualTo(user.getUserCode())
			.andIdNotEqualTo(user.getId());
		int count = userMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户名" + user.getUserCode() + "已存在.");
		}
		user.setUpdateTime(new Date());
		user.setSessionVersion(new Date().getTime());
		// 无法修改密码和用户名
		user.setPassword(null);
		user.setUserCode(null);
		userMapper.updateByPrimaryKeySelective(user);
		// 通知缓存session版本
		redisOperator.set(ConfigConstants.USER_VERSION_PRE + userCode, user.getSessionVersion());
		return user.getId();
	}

	@Override
	public void saveUserRole(Long id, List<Long> roleIds) {
		if(id == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户不存在.");
		}
		if(CollectionUtils.isEmpty(roleIds)){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户角色不能为空.");
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
		User newuser = new User();
		newuser.setId(id);
		newuser.setSessionVersion(new Date().getTime());
		userMapper.updateByPrimaryKey(newuser);
		// 通知缓存session版本
		redisOperator.set(ConfigConstants.USER_VERSION_PRE + user.getUserCode(), newuser.getSessionVersion());
		redisOperator.set(ConfigConstants.AUTH_VERSION_PRE + user.getUserCode(), newuser.getSessionVersion());
	}

	@Override
	public void updatePassword(Long id, String oldPassword, String newPassword) {
		if(id == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户不存在.");
		}
		if(StringUtils.isEmpty(oldPassword)){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "原密码不能为空.");
		}
		if(StringUtils.isEmpty(newPassword)){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "新密码不能为空.");
		}
		User record = userMapper.selectByPrimaryKey(id);
		if(record == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户不存在.");
		}
		UserExample example = new UserExample();
		example.createCriteria().andPasswordEqualTo(MD5Util.md5(oldPassword)).andIdEqualTo(id);
		int count = userMapper.countByExample(example);
		if(count == 0){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "原密码不正确.");
		}
		record.setId(id);
		record.setSessionVersion(new Date().getTime());
		record.setPassword(MD5Util.md5(newPassword));
		userMapper.updateByPrimaryKeySelective(record);
		// 通知缓存session版本
		redisOperator.set(ConfigConstants.LOGIN_VERSION_PRE + record.getUserCode(), record.getSessionVersion());
	}

	@Override
	public void resetPassword(Long id) {
		if(id == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户不存在.");
		}
		User user = userMapper.selectByPrimaryKey(id);
		if(user == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "用户不存在.");
		}
		user.setId(id);
		user.setSessionVersion(new Date().getTime());
		user.setPassword(MD5Util.md5("password"));
		userMapper.updateByPrimaryKeySelective(user);
		// 通知缓存session版本
		redisOperator.set(ConfigConstants.LOGIN_VERSION_PRE + user.getUserCode(), user.getSessionVersion());
	}

	@Override
	public void updateSessionVersion(String type, List<String> userCodes) {
		if(!CollectionUtils.isEmpty(userCodes)){
			for(String userCode : userCodes){
				if(type.equals(ConfigConstants.LOGIN_VERSION_PRE) && "SuperAdmin".equals(userCode)){
					throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "无法强制下线超级管理员.");
				}
			}
			for(String userCode : userCodes){
				UserExample example = new UserExample();
				example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andUserCodeEqualTo(userCode);
				User user = new User();
				user.setSessionVersion(new Date().getTime());
				userMapper.updateByExampleSelective(user, example);
				// 通知缓存session版本
				redisOperator.set(type + userCode, user.getSessionVersion());
			}
		}
	}

	@Override
	public PageInfo<UserVo> page(UserVo condition) {
	    PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
	    List<UserVo> list = userExtMapper.findUserList(condition);
	    PageInfo<UserVo> page = new PageInfo<>(list);
		return page;
	}

}
