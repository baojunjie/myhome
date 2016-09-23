package com.myhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * 省 市 区
 *
 */
public class Region extends AbstractEntity {

    /**
     *
     * 0 省
     * 1 市
     * 2 区
     *
     */
    private Integer level = 1;
//    @JsonIgnore
    private Region parent;
    
    private String name;
    
    private Integer regionCode;
    
    private String nameCode;
    

    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public Region getParent() {
        return this.parent;
    }
    
    public void setParent(Region parent) {
        this.parent = parent;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
    
	
}

