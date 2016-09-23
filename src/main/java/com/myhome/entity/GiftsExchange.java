package com.myhome.entity;

/**
 * 
 * 我要装修 礼物兑
 * 
 * @author gwb
 */
public class GiftsExchange extends AbstractEntity {
	private GenerousGifts generousGifts;
	private User user;// 所在区域

	public GenerousGifts getGenerousGifts() {
		return generousGifts;
	}

	public void setGenerousGifts(GenerousGifts generousGifts) {
		this.generousGifts = generousGifts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
