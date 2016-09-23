package com.myhome.entity;

import java.sql.Date;

/**
 * 问卷（是否要装修）
 * 
 * @author lqf
 *
 */
public class WantToDecorate extends AbstractEntity{
    
    private String name;//楼盘名称
    private Integer area;//面积
    private Integer budget;//装修预算
    private String wish;//对未来家的想象和愿望
    private Integer airbnb;//加入短租airbnb:1是，0否
    private Integer houseForSale;//房屋出售1是，0否
    private Integer unitPrice;//单价
    private Date date;//购房时间
    private User user;//用户
    private Region region;//你家所在的城市
    
    private Integer likeStyle;//喜欢的风格
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getArea() {
        return area;
    }
    public void setArea(Integer area) {
        this.area = area;
    }
    public Integer getBudget() {
        return budget;
    }
    public void setBudget(Integer budget) {
        this.budget = budget;
    }
    public String getWish() {
        return wish;
    }
    public void setWish(String wish) {
        this.wish = wish;
    }
    public Integer getAirbnb() {
        return airbnb;
    }
    public void setAirbnb(Integer airbnb) {
        this.airbnb = airbnb;
    }

    public Integer getHouseForSale() {
        return houseForSale;
    }
    public void setHouseForSale(Integer houseForSale) {
        this.houseForSale = houseForSale;
    }
    public Integer getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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
	public Integer getLikeStyle() {
		return likeStyle;
	}
	public void setLikeStyle(Integer likeStyle) {
		this.likeStyle = likeStyle;
	}

}
