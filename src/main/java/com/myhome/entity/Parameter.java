package com.myhome.entity;

/**
 * 系统参数
 */
public class Parameter extends AbstractEntity {

    /**
     * 参数说明
     */
    private String name;
    /**
     * 参数
     */
    private String value;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }


}

