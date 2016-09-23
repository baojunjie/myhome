package com.myhome.entity.vo;

import com.myhome.entity.UserInfo;

public class UserInfoVO extends UserInfo{
    private Integer malestr;
    private Integer city;
    private Integer province;
    private String regionid;
    private Integer age;
    public Integer getMalestr() {
        return malestr;
    }
    public void setMalestr(Integer malestr) {
        this.malestr = malestr;
    }
    public String getRegionid() {
        return regionid;
    }
    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }
    public Integer getCity() {
        return city;
    }
    public void setCity(Integer city) {
        this.city = city;
    }
    public Integer getProvince() {
        return province;
    }
    public void setProvince(Integer province) {
        this.province = province;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    

}
