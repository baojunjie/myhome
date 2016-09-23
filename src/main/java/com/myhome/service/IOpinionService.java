/**
 * Project Name:webmyhome
 * File Name:OpinionService.java
 * Package Name:com.myhome.service
 * Date:2015年9月21日下午6:17:37
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.service;

import com.myhome.entity.Opinion;

/**
 * ClassName:OpinionService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年9月21日 下午6:17:37 <br/>
 * @see 	 
 */
public interface IOpinionService  extends IService{

	void addOpinion(Opinion op) throws Exception;

}

