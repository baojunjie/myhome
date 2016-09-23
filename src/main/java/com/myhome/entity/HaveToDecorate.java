package com.myhome.entity;

import java.sql.Date;



/**
 * 
 * 我已装修
 * 
 */
public class HaveToDecorate extends AbstractEntity {

    /**
     * 
     * 用户id
     * 
     */
    private User user;

    /**
     * 
     * 城市id
     * 
     */
    private Region region;

    /**
     * 
     * 楼盘名称
     * 
     */
    private String name;

    /**
     * 
     * 购买时间
     * 
     */
    private Date purchaseTime;

    /**
     * 
     * 面积
     * 
     */
    private Integer area;

    /**
     * 
     * 单价
     * 
     */
    private Integer unitPrice;

    /**
     * 
     * 装修总价
     * 
     */
    private Integer totalPrice;

    /**
     * 
     * 工匠名称
     * 
     */
    private String craftsmanName;

    /**
     * 
     * 工匠手机号
     * 
     */
    private String craftsmanMobile;

    /**
     * 
     * 工种
     * 
     */
    private String craftsmanType;

    /**
     * 
     * 评价
     * 
     */
    private String craftsmanComment;

    /**
     * 
     * 设计师姓名
     * 
     */
    private String designerName;

    /**
     * 
     * 设计师手机号
     * 
     */
    private String designerMobile;

    /**
     * 
     * 设计师评论
     * 
     */
    private String designerComment;

    /**
     * 
     * 客厅
     * 
     */
    private String livingRoom;

    /**
     * 
     * 厨房
     * 
     */
    private String kitchen;

    /**
     * 
     * 卫生间
     * 
     */
    private String toilet;

    /**
     * 
     * 卧室
     * 
     */
    private String bedroom;

    /**
     * 
     * 书房
     * 
     */
    private String study;

    /**
     * 
     * 阳台
     * 
     */
    private String balcony;

    /**
     * 
     * 浴缸
     * 
     */
    private String bathtub;

    /**
     * 
     * 水槽
     * 
     */
    private String waterChannel;

    /**
     * 
     * 花洒
     * 
     */
    private String sprinkler;

    /**
     * 
     * 马桶
     * 
     */
    private String closestool;

    /**
     * 
     * 涂料
     * 
     */
    private String coating;

    /**
     * 
     * 开关插坐
     * 
     */
    private String switchInsert;

    /**
     * 
     * 灯具
     * 
     */
    private String lamps;

    /**
     * 
     * 瓷砖
     * 
     */
    private String tile;

    /**
     * 
     * 地板
     * 
     */
    private String floor;

    /**
     * 
     * 空调
     * 
     */
    private String airCondition;

    /**
     * 
     * 热水器
     * 
     */
    private String waterHeater;

    /**
     * 
     * 浴霸
     * 
     */
    private String bathHeater;

    /**
     * 
     * 冰箱
     * 
     */
    private String refrigerator;

    /**
     * 
     * 净水器
     * 
     */
    private String waterPurifier;

    /**
     * 
     * 油烟机
     * 
     */
    private String smokeExhaust;

    /**
     * 
     * 煤气灶
     * 
     */
    private String gasCooker;

    /**
     * 
     * 衣柜
     * 
     */
    private String garderobe;

    /**
     * 
     * 沙发
     * 
     */
    private String sofa;

    /**
     * 
     * 床垫
     * 
     */
    private String mattess;

    /**
     * 
     * 床
     * 
     */
    private String bed;

    /**
     * 
     * 橱柜
     * 
     */
    private String cabinet;

    /**
     * 
     * 加入短租 1是，0否
     * 
     */
    private Integer airbnb;

    /**
     * 
     * 房屋出售 1是，0否
     * 
     */
    private Integer houseForSale;

    
   
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

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }

    public String getCraftsmanMobile() {
        return craftsmanMobile;
    }

    public void setCraftsmanMobile(String craftsmanMobile) {
        this.craftsmanMobile = craftsmanMobile;
    }

    public String getCraftsmanType() {
        return craftsmanType;
    }

    public void setCraftsmanType(String craftsmanType) {
        this.craftsmanType = craftsmanType;
    }

    public String getCraftsmanComment() {
        return craftsmanComment;
    }

    public void setCraftsmanComment(String craftsmanComment) {
        this.craftsmanComment = craftsmanComment;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getDesignerMobile() {
        return designerMobile;
    }

    public void setDesignerMobile(String designerMobile) {
        this.designerMobile = designerMobile;
    }

    public String getDesignerComment() {
        return designerComment;
    }

    public void setDesignerComment(String designerComment) {
        this.designerComment = designerComment;
    }

    public String getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(String livingRoom) {
        this.livingRoom = livingRoom;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getBathtub() {
        return bathtub;
    }

    public void setBathtub(String bathtub) {
        this.bathtub = bathtub;
    }

    public String getWaterChannel() {
        return waterChannel;
    }

    public void setWaterChannel(String waterChannel) {
        this.waterChannel = waterChannel;
    }

    public String getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(String sprinkler) {
        this.sprinkler = sprinkler;
    }

    public String getClosestool() {
        return closestool;
    }

    public void setClosestool(String closestool) {
        this.closestool = closestool;
    }

    public String getCoating() {
        return coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public String getSwitchInsert() {
        return switchInsert;
    }

    public void setSwitchInsert(String switchInsert) {
        this.switchInsert = switchInsert;
    }

    public String getLamps() {
        return lamps;
    }

    public void setLamps(String lamps) {
        this.lamps = lamps;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }

    public String getWaterHeater() {
        return waterHeater;
    }

    public void setWaterHeater(String waterHeater) {
        this.waterHeater = waterHeater;
    }

    public String getBathHeater() {
        return bathHeater;
    }

    public void setBathHeater(String bathHeater) {
        this.bathHeater = bathHeater;
    }

    public String getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(String refrigerator) {
        this.refrigerator = refrigerator;
    }

    public String getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(String waterPurifier) {
        this.waterPurifier = waterPurifier;
    }

    public String getSmokeExhaust() {
        return smokeExhaust;
    }

    public void setSmokeExhaust(String smokeExhaust) {
        this.smokeExhaust = smokeExhaust;
    }

    public String getGasCooker() {
        return gasCooker;
    }

    public void setGasCooker(String gasCooker) {
        this.gasCooker = gasCooker;
    }

    public String getGarderobe() {
        return garderobe;
    }

    public void setGarderobe(String garderobe) {
        this.garderobe = garderobe;
    }

    public String getSofa() {
        return sofa;
    }

    public void setSofa(String sofa) {
        this.sofa = sofa;
    }

    public String getMattess() {
        return mattess;
    }

    public void setMattess(String mattess) {
        this.mattess = mattess;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
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

}
