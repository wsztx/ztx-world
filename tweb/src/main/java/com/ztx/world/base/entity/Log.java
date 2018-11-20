package com.ztx.world.base.entity;

import java.util.Date;

/**
 * 
 * 
 * @author ztx
 * 
 * @date 2018-11-20
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
    private String operateType;

    /**
     * 所属模块名称
     */
    private String modelName;

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

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
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