package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.base.entity.Role;
import com.ztx.world.base.entity.RoleExample;
import com.ztx.world.base.entity.RolePermission;
import com.ztx.world.base.entity.RolePermissionExample;
import com.ztx.world.base.entity.UserRoleExample;
import com.ztx.world.base.mapper.RoleMapper;
import com.ztx.world.base.mapper.RolePermissionMapper;
import com.ztx.world.base.mapper.UserRoleMapper;
import com.ztx.world.base.mapper.ext.RoleExtMapper;
import com.ztx.world.base.service.RoleService;
import com.ztx.world.base.vo.RoleVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.model.RoleModel;
import com.ztx.world.common.utils.ShiroUtil;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleExtMapper roleExtMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private ShiroUtil shiroUtil;

	@Override
	public List<Role> findRolesByUserId(Long id) {
		List<Role> list = roleExtMapper.findRolesByUserId(id);
		return list;
	}

	@Override
	public void deleteRole(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
			CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
			List<RoleModel> roleList = customSession.getRoleList();
			for(Long id : ids){
				Role role = roleMapper.selectByPrimaryKey(id);
				if(role != null){
					if("SuperAdmin".equals(role.getRoleCode())){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色超级管理员无法删除.");
					}
					UserRoleExample example = new UserRoleExample();
					example.createCriteria().andRoleIdEqualTo(id);
					int count = userRoleMapper.countByExample(example);
					if(count > 0){
						throw new BasicException(ResultCode.BASE_ARG_ERROR, "已有用户分配角色" + role.getRoleName() + ",无法删除.");
					}
					if(CollectionUtils.isEmpty(roleList)){
						for(RoleModel roleModel : roleList){
							if(id.equals(roleModel.getRoleId())){
								throw new BasicException(ResultCode.BASE_ARG_ERROR, "不能删除自己的角色.");
							}
						}
					}
				}
			}
			
			// 逻辑删除角色
			for(Long id : ids){
				// 删除角色表信息
				Role role = new Role();
				role.setId(id);
				role.setStatus(-id);
				roleMapper.updateByPrimaryKeySelective(role);
				
				// 删除角色权限关联表信息
				RolePermissionExample rolePermissionExample = new RolePermissionExample();
				rolePermissionExample.createCriteria().andRoleIdEqualTo(id);
				rolePermissionMapper.deleteByExample(rolePermissionExample);
				
				// 删除用户角色信息表,理论上不存在
				UserRoleExample userRoleExample = new UserRoleExample();
				userRoleExample.createCriteria().andRoleIdEqualTo(id);
				userRoleMapper.deleteByExample(userRoleExample);
				
				// 清除所有该角色的用户的权限缓存,理论上不存在
				List<String> codes = roleExtMapper.findUserCodesByRoleId(id);
				shiroUtil.clearAuthCache(codes);
			}
		}
	}

	@Override
	public Long saveRole(RoleVo role) {
		if(role == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(StringUtils.isEmpty(role.getRoleCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色编码不能为空.");
		}
		if(StringUtils.isEmpty(role.getRoleName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色名称不能为空.");
		}
		RoleExample example = new RoleExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andRoleCodeEqualTo(role.getRoleCode());
		int count = roleMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色编码" + role.getRoleCode() + "已存在.");
		}
		role.setStatus(BaseConstants.UNDELETE_STATUS);
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		role.setCreateUserId(customSession.getUserId());
		roleMapper.insertSelective(role);
		if(role.getId() == null){
			throw new BasicException(ResultCode.BASE_DATA_ERROR, "新增角色失败.");
		}
		// 新增角色权限
		List<Long> permissionIds = role.getPermissionIds();
		if(!CollectionUtils.isEmpty(permissionIds)){
			for(Long id : permissionIds){
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getId());
				rolePermission.setPermissionId(id);
				rolePermissionMapper.insertSelective(rolePermission);
			}
		}
		return role.getId();
	}

	@Override
	public Long updateRole(RoleVo role) {
		if(role == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(role.getId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色不存在.");
		}
		if(StringUtils.isEmpty(role.getRoleCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色编码不能为空.");
		}
		if(StringUtils.isEmpty(role.getRoleName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色名称不能为空.");
		}
		if("SuperAdmin".equals(role.getRoleCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色超级管理员无法修改.");
		}
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andRoleCodeEqualTo(role.getRoleCode())
			.andIdNotEqualTo(role.getId());
		int count = roleMapper.countByExample(roleExample);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "角色编码" + role.getRoleCode() + "已存在.");
		}
		role.setUpdateTime(new Date());
		roleMapper.updateByPrimaryKeySelective(role);
		// 删除原来的角色权限
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(role.getId());
		rolePermissionMapper.deleteByExample(example);
		// 新增角色权限
		List<Long> permissionIds = role.getPermissionIds();
		if(!CollectionUtils.isEmpty(permissionIds)){
			for(Long id : permissionIds){
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getId());
				rolePermission.setPermissionId(id);
				rolePermissionMapper.insertSelective(rolePermission);
			}
		}
		
		// 清除所有该角色的用户的权限缓存
		List<String> codes = roleExtMapper.findUserCodesByRoleId(role.getId());
		shiroUtil.clearAuthCache(codes);
		return role.getId();
	}

}
