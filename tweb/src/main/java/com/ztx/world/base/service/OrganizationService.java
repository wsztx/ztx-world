package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.vo.OrganizationVo;

public interface OrganizationService {

	/**
	 * 新建机构
	 * @param organization
	 * @return
	 */
	Long saveOrganization(OrganizationVo organization);
	
	/**
	 * 修改机构
	 * @param organization
	 * @return
	 */
	Long updateOrganization(OrganizationVo organization);
	
	/**
	 * 删除机构
	 * @param ids
	 */
	void deleteOrganization(List<Long> ids);
}
