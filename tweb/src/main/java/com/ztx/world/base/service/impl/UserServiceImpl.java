package com.ztx.world.base.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.service.UserService;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public Long updateUser(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andIdEqualTo(user.getId());
		userMapper.updateByExampleSelective(user, example);
		return user.getId();
	}

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
			}
			
		}
	}

}
