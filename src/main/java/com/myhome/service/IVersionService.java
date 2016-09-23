/**
 * Project Name:webmyhome
 * File Name:Version.java
 * Package Name:com.myhome.service
 * Date:2015年9月21日下午6:13:38
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.service;

import java.util.Map;

import com.myhome.entity.Version;

/**
 * ClassName:Version <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年9月21日 下午6:13:38 <br/>
 * @see 	 
 */
public interface IVersionService  extends IService{
	/**
	 * 获取版本号
	 * getVersionMobile
	 * @author gwb
	 * @return
	 * 2015年9月21日 下午7:25:45
	 */
	public Map<String,String>  getVersionMobile() throws Exception;

}

