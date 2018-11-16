package com.ztx.world.base.service;

import java.util.List;

import com.ztx.world.base.vo.DepartmentVo;

public interface DepartmentService {

	/**
	 * 新建部门
	 * @param dDepartment
	 * @return
	 */
	Long saveDepartment(DepartmentVo department);
	
	/**
	 * 修改部门
	 * @param dDepartment
	 * @return
	 */
	Long updateDepartment(DepartmentVo department);
	
	/**
	 * 删除部门
	 * @param ids
	 */
	void deleteDepartment(List<Long> ids);
}
