/**
 * Project Name:webmyhome
 * File Name:Version.java
 * Package Name:com.myhome.entity
 * Date:2015年9月21日下午5:55:51
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.entity;
/**
 * ClassName:Version <br/>
 * Date:     2015年9月21日 下午5:55:51 <br/>
 * @author   1
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Opinion extends AbstractEntity{

	private String content;//更新内容
	private User User;//更新地址
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	
	
	
}

