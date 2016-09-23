package com.myhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 照片
 * 
 * @author gwb
 * @version $Id: WithPicture.java, v 0.1 2015年10月16日 下午4:55:58 gwb Exp $
 */
public class WithPicture extends AbstractEntity{

	private String path;//缩略图
	
	private String originPath;//原图
	
	private String description;//描述
	
	private Integer type=1;//用户类型
	
	private String token;//用户类型
	
	@JsonIgnore
	private User user; //y用户

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOriginPath() {
		return originPath;
	}

	public void setOriginPath(String originPath) {
		this.originPath = originPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
