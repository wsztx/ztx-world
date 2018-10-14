package com.ztx.world.base.entity;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-10-14
 */
public class RolePermission {
    /**
     * 主键
     */
    private Long id;

    /**
     * 逻辑删除位，0删除，1未删除
     */
    private Integer status;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}