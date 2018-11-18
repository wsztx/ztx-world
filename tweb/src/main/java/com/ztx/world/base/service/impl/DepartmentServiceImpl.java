package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.base.entity.Department;
import com.ztx.world.base.entity.DepartmentExample;
import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.mapper.DepartmentMapper;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.service.DepartmentService;
import com.ztx.world.base.vo.DepartmentVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.common.utils.UUIDUtil;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisOperator redisOperator;

	@Override
	public Long saveDepartment(DepartmentVo department) {
		if(department == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(StringUtils.isEmpty(department.getDeptCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门编码不能为空.");
		}
		if(StringUtils.isEmpty(department.getDeptName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门名称不能为空.");
		}
		if(department.getParentId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门上级机构不能为空.");
		}
		DepartmentExample example = new DepartmentExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andDeptCodeEqualTo(department.getDeptCode());
		int count = departmentMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门编码已存在.");
		}
		department.setStatus(BaseConstants.UNDELETE_STATUS);
		department.setCreateTime(new Date());
		department.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		department.setCreateUserId(customSession.getUserId());
		departmentMapper.insertSelective(department);
		if(department.getId() == null){
			throw new BasicException(ResultCode.BASE_DATA_ERROR, "新增部门失败.");
		}
		return department.getId();
	}

	@Override
	public Long updateDepartment(DepartmentVo department) {
		if(department == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(department.getId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门不存在.");
		}
		if(StringUtils.isEmpty(department.getDeptCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门编码不能为空.");
		}
		if(StringUtils.isEmpty(department.getDeptName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门名称不能为空.");
		}
		if(department.getParentId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门上级机构不能为空.");
		}
		DepartmentExample example = new DepartmentExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andDeptCodeEqualTo(department.getDeptCode())
			.andIdNotEqualTo(department.getId());
		int count = departmentMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门编码已存在.");
		}
		department.setUpdateTime(new Date());
		department.setDeptCode(null);
		departmentMapper.updateByPrimaryKeySelective(department);
		
		// 修改部门下用户版本
		UserExample userExample = new UserExample();
		userExample.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andDeptIdEqualTo(department.getId());
		List<User> userList = userMapper.selectByExample(userExample);
		if(CollectionUtils.isEmpty(userList)){
			for(User user : userList){
				user.setSessionVersion(UUIDUtil.getUUID());
				userMapper.updateByPrimaryKeySelective(user);
				// 通知缓存用户改了
				redisOperator.set(ConfigConstants.VERSION_PRE + user.getUserCode(), user.getSessionVersion());
			}
		}
		return department.getId();
	}

	@Override
	public void deleteDepartment(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
			UserExample example = new UserExample();
			example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andDeptIdIn(ids);
			int count = userMapper.countByExample(example);
			if(count > 0){
				throw new BasicException(ResultCode.BASE_ARG_ERROR, "部门下存在用户,无法删除.");
			}
			
			for(Long id : ids){
				Department department = new Department();
				department.setId(id);
				department.setStatus(-id);
				departmentMapper.updateByPrimaryKeySelective(department);
			}
		}
	}
}
