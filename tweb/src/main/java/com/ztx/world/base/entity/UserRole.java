package com.ztx.world.base.entity;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-10-14
 */
public class UserRole {
    /**
     * 主键
     */
    private Long id;

    /**
     * 逻辑删除位，0删除，1未删除
     */
    private Integer status;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}