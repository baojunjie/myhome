/**
 * Project Name:webmyhome
 * File Name:IOpinionDao.java
 * Package Name:com.myhome.dao
 * Date:2015年9月21日下午7:17:34
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.dao;

import com.myhome.entity.Opinion;

/**
 * gwb
 * ClassName:IOpinionDao <br/>
 * Date:     2015年9月21日 下午7:17:34 <br/>
 * @version  
 * @see 	 
 */
public interface IOpinionDao extends IDAO{

	void addOpinion(Opinion op) throws Exception;

}

