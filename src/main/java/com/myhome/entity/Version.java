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
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年9月21日 下午5:55:51 <br/>
 * @author   1
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Version extends AbstractEntity{

	private String content;//更新内容
	private String versions;//版本号
	private String serviceAddress;//更新地址
	private String size;//更新大小
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVersions() {
		return versions;
	}
	public void setVersions(String versions) {
		this.versions = versions;
	}
	public String getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}

