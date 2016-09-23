package com.myhome.entity.vo;

import com.myhome.entity.Teacher;

public class TeacherVo extends Teacher{
    /**
     * 生日
     */
    private String birthdayStr;
    /**
     * (区)
     */
    private Integer regionStr;
    /**
     * 性别
     */
    private Integer maleStr;
    /**
     * （省）
     */
    private String province;
    /**
     * （市）
     */
    private String city;
    /**
     * 
     * @return
     */
    private String picturetag= "useless";
    public Integer getMaleStr() {
        return maleStr;
    }
    public void setMaleStr(Integer maleStr) {
        this.maleStr = maleStr;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getRegionStr() {
        return regionStr;
    }
    public void setRegionStr(Integer regionStr) {
        this.regionStr = regionStr;
    }
    public String getBirthdayStr() {
        return birthdayStr;
    }
    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }
    public String getPicturetag() {
        return picturetag;
    }
    public void setPicturetag(String picturetag) {
        this.picturetag = picturetag;
    }
  
    

}
