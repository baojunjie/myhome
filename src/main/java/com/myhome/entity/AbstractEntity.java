package com.myhome.entity;

import java.sql.Timestamp;



abstract public class AbstractEntity {

    private Long id;
    
    /**
     *
     * 该记录是否无效：
     *     true 无效
     *     false 有效
     *
     */
    private Boolean invalid = false;
    
    private Integer status = 1;
    
    private Timestamp createdDatetime;
    
    /**
     *
     * 最近修改时间
     *
     */
    private Timestamp updatedDatetime;
    
    /**
     *
     * 排序标签
     *
     */
    private Integer orderTag = 1;
    
    

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getInvalid() {
        return this.invalid;
    }
    
    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Timestamp getCreatedDatetime() {
        return this.createdDatetime;
    }
    
    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
    
    public Timestamp getUpdatedDatetime() {
        return this.updatedDatetime;
    }
    
    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
    
    public Integer getOrderTag() {
        return this.orderTag;
    }
    
    public void setOrderTag(Integer orderTag) {
        this.orderTag = orderTag;
    }
    
    



    
}
