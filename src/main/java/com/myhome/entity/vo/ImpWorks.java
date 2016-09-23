package com.myhome.entity.vo;

import java.util.Date;


/**
 * 批量导入
 * @author lqf
 *
 */
public class ImpWorks {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 画家姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 选送城市
	 */
	private Long city;
	/**
	 * 选送地区
	 */
	private Long region;
	/**
	 * 家长联系方式
	 */
	private String parentMobile;
	/**
	 * 画家头像
	 */
	private String headImg;
	/**
	 * 学校
	 */
	private String school;
	/**
	 * 培训机构
	 */
	private String trainingInstitution;
	/**
	 * 最喜欢的卡通形象
	 */
	private String cartoon;
	/**
	 * 支付宝账号
	 */
	private String alipayAccount;
	/**
	 * 微信账号
	 */
	private String weChatAccount;
	/**
	 * 银行卡账号
	 */
	private String bankAccount;
	/**
	 * 银行卡开户人
	 */
	private String accountName;
	/**
	 * 作品名称
	 */
	private String worksName;
	/**
	 * 作品类别
	 */
	private String worksType;
	/**
	 * 作品简介
	 */
	private String description;
	/**
	 * 指导老师
	 */
	private String  instructor;
	/**
	 * 指导老师联系方式
	 */
	private String instructorMobile;
	/**
	 * 作品图片名称
	 */
	private String worksImg;
	/**
	 * 
	 * @return
	 */
	private String Msg;
	
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Long getRegion() {
		return region;
	}
	public void setRegion(Long region) {
		this.region = region;
	}
	public String getParentMobile() {
		return parentMobile;
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTrainingInstitution() {
		return trainingInstitution;
	}
	public void setTrainingInstitution(String trainingInstitution) {
		this.trainingInstitution = trainingInstitution;
	}
	public String getCartoon() {
		return cartoon;
	}
	public void setCartoon(String cartoon) {
		this.cartoon = cartoon;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getWeChatAccount() {
		return weChatAccount;
	}
	public void setWeChatAccount(String weChatAccount) {
		this.weChatAccount = weChatAccount;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getWorksName() {
		return worksName;
	}
	public void setWorksName(String worksName) {
		this.worksName = worksName;
	}
	public String getWorksType() {
		return worksType;
	}
	public void setWorksType(String worksType) {
		this.worksType = worksType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getInstructorMobile() {
		return instructorMobile;
	}
	public void setInstructorMobile(String instructorMobile) {
		this.instructorMobile = instructorMobile;
	}
	public String getWorksImg() {
		return worksImg;
	}
	public void setWorksImg(String worksImg) {
		this.worksImg = worksImg;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	
}
