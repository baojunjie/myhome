package com.myhome.entity;




/**
 *
 * 权限
 *
 */
public class Permission extends AbstractEntity {

    /**
     *
     * 唯一码，是英文
     *
     */
    private String code;
    
    /**
     *
     * 中文名称，也是唯一的
     *
     */
    private String name;
    
    private String description;
    
    

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    



    
}
