package com.ztx.world.base.entity;

import java.util.Date;

/**
 * 
 * 
 * @author ztx
 * 
 * @date 2018-11-21
 */
public class Log {
    /**
     * 主键
     */
    private Long id;

    /**
     * 逻辑删除位，0未删除
     */
    private Long status;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作人id
     */
    private Long operateUserId;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 所属模块
     */
    private Integer modelType;

    /**
     * 操作IP地址
     */
    private String operateIp;

    /**
     * 操作mac地址
     */
    private String operateMac;

    /**
     * 操作对象
     */
    private String operateObject;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }

    public String getOperateMac() {
        return operateMac;
    }

    public void setOperateMac(String operateMac) {
        this.operateMac = operateMac == null ? null : operateMac.trim();
    }

    public String getOperateObject() {
        return operateObject;
    }

    public void setOperateObject(String operateObject) {
        this.operateObject = operateObject == null ? null : operateObject.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}