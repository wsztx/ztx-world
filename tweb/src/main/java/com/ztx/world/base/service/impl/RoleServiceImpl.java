package com.ztx.world.base.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ztx.world.base.entity.Role;
import com.ztx.world.base.entity.others.RoleBean;
import com.ztx.world.base.mapper.RoleMapper;
import com.ztx.world.base.mapper.ext.RoleExtMapper;
import com.ztx.world.base.service.RoleService;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleExtMapper roleExtMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> findRolesByUserId(Long id) {
		List<Role> list = roleExtMapper.findRolesByUserId(id);
		return list;
	}

	@Override
	public void deleteRole(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
			List<RoleBean> roleList = customSession.getRoleList();
			for(Long id : ids){
				Role role = roleMapper.selectByPrimaryKey(id);
				if(role != null){
					if("SuperAdmin".equals(role.getRoleCode())){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色超级管理员无法删除!");
					}
					if(CollectionUtils.isEmpty(roleList)){
						for(RoleBean roleBean : roleList){
							if(id.equals(roleBean.getRoleId())){
								throw new BasicException(ResultCode.BASE_ARG_ERROR, "不能删除自己的角色!");
							}
						}
					}
				}
			}
			
			// 逻辑删除角色
			for(Long id : ids){
				Role role = new Role();
				role.setId(id);
				role.setStatus(BaseConstants.DELETE_STATUS);
				roleMapper.updateByPrimaryKeySelective(role);
			}
			
			// 清除所有权限信息
		}
	}

}
