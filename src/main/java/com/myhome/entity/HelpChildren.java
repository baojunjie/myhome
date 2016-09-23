package com.myhome.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myhome.utils.DateUtil;

// Generated 2015-10-10 12:35:19 by Hibernate Tools 3.4.0.CR1

/**
 * 受帮助的小朋友
 * 
 * @author gwb
 * @version $Id: HelpChildren.java, v 0.1 2015年10月13日 下午2:56:33 gwb Exp $
 */
public class HelpChildren extends AbstractEntity {

	private String name; // 姓名
	private String img;// 照片
	private String thumbnailImg;// 照片(缩略图)
	private Boolean male = true;// 性别
	private Date birthday;// 生日
	private String zodiac;// 属相
	private String constellation;// 星座
	private String nation;// 民族
	private String origin;// 籍贯
	private String address;// 通讯地址
	private String idCode;// 身份证号
	private String parentMobile;// 家长手机号
	private String parnetIdCode;// 家长身份证号
	private String parentName;// 家长姓名
	private String currentStatus;// 目前状态
	private String wish;// 最大的愿望
	private String significative;// 最有意愿的事

	private Double dreamFund;// 梦想基金
	private String school;// 学校
	private Integer age;// 学校
	private String token;// 验证手机端上传是否成功

	private List<WithPicture> withPictureList;
	@JsonIgnore
	private User user;

	private Region region;
//2.0
	private String alipayAccount;// 支付宝账户

	private String bankAccount;// 银行卡号

	private String bankName;// 开户银行名称

	private String accountName;// 开户人

	private String weChatAccount;// 微信账户

	public HelpChildren() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}

	public Boolean getMale() {
		return male;
	}

	public void setMale(Boolean male) {
		this.male = male;
	}

	public Date getBirthday() {

		return birthday;

	}

	public void setBirthday(Date birthday) {

		this.birthday = (birthday == null ? null : birthday);
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getParnetIdCode() {
		return parnetIdCode;
	}

	public void setParnetIdCode(String parnetIdCode) {
		this.parnetIdCode = parnetIdCode;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}

	public String getSignificative() {
		return significative;
	}

	public void setSignificative(String significative) {
		this.significative = significative;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public Double getDreamFund() {
		return dreamFund;
	}

	public void setDreamFund(Double dreamFund) {
		this.dreamFund = dreamFund;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getAge() {
		if (this.getBirthday() != null) {
			return DateUtil.getage(this.getBirthday());
		} else {
			return age;
		}

	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<WithPicture> getWithPictureList() {
		return withPictureList;
	}

	public void setWithPictureList(List<WithPicture> withPictureList) {
		this.withPictureList = withPictureList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getWeChatAccount() {
		return weChatAccount;
	}

	public void setWeChatAccount(String weChatAccount) {
		this.weChatAccount = weChatAccount;
	}



}
