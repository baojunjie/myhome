package com.myhome.entity;

import java.sql.Date;

/**
 * 问卷（买房）
 * @author lqf
 *
 */
public class WantBuyHouse extends AbstractEntity {
	private Region  region;          //你家所在的城市
    private String  name;            //您希望购房的楼盘
    private String houseType;        //户型
    private Date    purchaseTime;    //预购时间
    private Integer purchasingBudget; //购买预算
    private Integer houseForSale;    //你是否有房屋需要出售1是，0否
    private Integer area;            //购房面积
    private User    user;            //用户
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getPurchasingBudget() {
        return purchasingBudget;
    }

    public void setPurchasingBudget(Integer purchasingBudget) {
        this.purchasingBudget = purchasingBudget;
    }

    public Integer getHouseForSale() {
        return houseForSale;
    }

    public void setHouseForSale(Integer houseForSale) {
        this.houseForSale = houseForSale;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
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
