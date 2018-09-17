package com.ztx.world.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztx.world.base.dao.UserMapper;
import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.service.UserService;
import com.ztx.world.common.constants.Constants;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean updateUser(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(Constants.UNDELETE_STATUS)
			.andIdEqualTo(user.getId());
		userMapper.updateByExampleSelective(user, example);
		return false;
	}

}
