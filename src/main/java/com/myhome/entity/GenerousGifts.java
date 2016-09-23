package com.myhome.entity;
/**
 * 介绍装修的朋友
 * @author lqf
 *
 */
public class GenerousGifts extends AbstractEntity{
    private String name;//姓名
    private String mobile;//手机
    private String address;//地址
    private User user;//用户
    private Region region;//所在区域
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }
    

}
