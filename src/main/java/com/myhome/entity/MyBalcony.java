package com.myhome.entity;

/**
 * 
 * 调查问题     我的阳台
 * 
 * @author gwb
 */
public class MyBalcony extends AbstractEntity {
	private Region region;  //所在城市
	
	private User user;// 用户名
	
	private String balconyImg;//阳台图片
	
	private String balconyImgThum;//阳台图片缩略图
	
	private Integer balconyFunction;//阳台功能
	
	private Integer balconyStyle;//阳台风格
	
	private Integer diy;//diy
	
	private Integer spend;//花费

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public String getBalconyImg() {
		return balconyImg;
	}

	public void setBalconyImg(String balconyImg) {
		this.balconyImg = balconyImg;
	}

	public String getBalconyImgThum() {
		return balconyImgThum;
	}

	public void setBalconyImgThum(String balconyImgThum) {
		this.balconyImgThum = balconyImgThum;
	}

	public Integer getBalconyFunction() {
		return balconyFunction;
	}

	public void setBalconyFunction(Integer balconyFunction) {
		this.balconyFunction = balconyFunction;
	}

	public Integer getBalconyStyle() {
		return balconyStyle;
	}

	public void setBalconyStyle(Integer balconyStyle) {
		this.balconyStyle = balconyStyle;
	}

	public Integer getDiy() {
		return diy;
	}

	public void setDiy(Integer diy) {
		this.diy = diy;
	}

	public Integer getSpend() {
		return spend;
	}

	public void setSpend(Integer spend) {
		this.spend = spend;
	}
}
