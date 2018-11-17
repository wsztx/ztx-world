package com.ztx.world.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.base.entity.DepartmentExample;
import com.ztx.world.base.entity.Organization;
import com.ztx.world.base.entity.OrganizationExample;
import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.mapper.DepartmentMapper;
import com.ztx.world.base.mapper.OrganizationMapper;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.service.OrganizationService;
import com.ztx.world.base.vo.OrganizationVo;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.redis.RedisOperator;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisOperator redisOperator;

	@Override
	public Long saveOrganization(OrganizationVo organization) {
		if(organization == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(StringUtils.isEmpty(organization.getOrgCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构编码不能为空.");
		}
		if(StringUtils.isEmpty(organization.getOrgName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构名称不能为空.");
		}
		if(organization.getParentId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构上级机构不能为空.");
		}
		OrganizationExample example = new OrganizationExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andOrgCodeEqualTo(organization.getOrgCode());
		int count = organizationMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构编码已存在.");
		}
		organization.setStatus(BaseConstants.UNDELETE_STATUS);
		organization.setCreateTime(new Date());
		organization.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		organization.setCreateUserId(customSession.getUserId());
		organizationMapper.insertSelective(organization);
		if(organization.getId() == null){
			throw new BasicException(ResultCode.BASE_DATA_ERROR, "新增机构失败.");
		}
		
		// 修改org_path
		Organization org = organizationMapper.selectByPrimaryKey(organization.getParentId());
		if(org != null){
			organization.setOrgPath(org.getOrgPath() + organization.getId() + ",");
			organizationMapper.updateByPrimaryKeySelective(organization);
		}
		return organization.getId();
	}

	@Override
	public Long updateOrganization(OrganizationVo organization) {
		if(organization == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "数据不能为空.");
		}
		if(organization.getId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构不存在.");
		}
		if(StringUtils.isEmpty(organization.getOrgCode())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构编码不能为空.");
		}
		if(StringUtils.isEmpty(organization.getOrgName())){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构名称不能为空.");
		}
		if(organization.getParentId() == null){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构上级机构不能为空.");
		}
		OrganizationExample example = new OrganizationExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andOrgCodeEqualTo(organization.getOrgCode())
			.andIdNotEqualTo(organization.getId());
		int count = organizationMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构编码已存在.");
		}
		organization.setUpdateTime(new Date());
		// 修改org_path
		Organization org = organizationMapper.selectByPrimaryKey(organization.getParentId());
		if(org != null){
			organization.setOrgPath(org.getOrgPath() + organization.getId() + ",");
		}
		organizationMapper.updateByPrimaryKeySelective(organization);
		return organization.getId();
	}

	@Override
	public void deleteOrganization(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
			UserExample example = new UserExample();
			example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andOrgIdIn(ids);
			int count = userMapper.countByExample(example);
			if(count > 0){
				throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构下存在用户,无法删除.");
			}
			
			DepartmentExample departmentExample = new DepartmentExample();
			departmentExample.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS).andParentIdIn(ids);
			int dcount = departmentMapper.countByExample(departmentExample);
			if(dcount > 0){
				throw new BasicException(ResultCode.BASE_ARG_ERROR, "机构下存在部门,无法删除.");
			}
			
			// 逻辑删除机构
			for(Long id : ids){
				Organization organization = new Organization();
				organization.setId(id);
				organization.setStatus(-id);
				organizationMapper.updateByPrimaryKeySelective(organization);
			}
		}
	}
}
