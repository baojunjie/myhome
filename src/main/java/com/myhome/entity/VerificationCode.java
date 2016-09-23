package com.myhome.entity;
/**
 * 用户注册时存放验证码
 * @author lqf
 */
public class VerificationCode extends AbstractEntity{
	/**
	 * 电话号码
	 */
	private String mobile;
	/**
	 * 生成的六位验证码  如：123465
	 */
	private String Code;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	

}
