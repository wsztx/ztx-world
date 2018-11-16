package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.entity.UserRole;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.mapper.UserRoleMapper;
import com.ztx.world.base.service.UserService;
import com.ztx.world.base.vo.UserVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.utils.ShiroUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private ShiroUtil shiroUtil;

	@Override
	public void deleteUser(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
			for(Long id : ids){
				User user = userMapper.selectByPrimaryKey(id);
				if(user != null){
					if("SuperAdmin".equals(user.getUserCode())){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户超级管理员无法删除!");
					}
					if(customSession != null && id.equals(customSession.getUserId())){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "不能删除自己!");
					}
				}
			}
			
			// 逻辑删除用户
			for(Long id : ids){
				User user = userMapper.selectByPrimaryKey(id);
				user.setId(id);
				user.setStatus(BaseConstants.DELETE_STATUS);
				userMapper.updateByPrimaryKeySelective(user);
				
				// 强制将用户登出
				shiroUtil.deleteSession(user.getUserCode());
			}
			
		}
	}

	@Override
	public Long saveUser(UserVo user) {
		List<Long> roleIds = user.getRoleIds();
		if(CollectionUtils.isEmpty(roleIds)){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "用户角色不能为空!");
		}
		user.setStatus(BaseConstants.UNDELETE_STATUS);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		user.setCreateUserId(customSession.getUserId());
		userMapper.insertSelective(user);
		if(user.getId() == null){
			throw new BasicException(ResultCode.BASE_DATA_ERROR, "新增用户失败!");
		}
		// 插入用户角色关联表
		for(Long id : roleIds){
			UserRole userRole = new UserRole();
			userRole.setRoleId(id);
			userRole.setUserId(user.getId());
			userRoleMapper.insertSelective(userRole);
		}
		return user.getId();
	}

	@Override
	public Long updateUser(UserVo user) {
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andIdEqualTo(user.getId());
		userMapper.updateByExampleSelective(user, example);
		return user.getId();
	}

}
