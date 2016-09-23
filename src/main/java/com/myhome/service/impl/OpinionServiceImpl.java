/**
 * Project Name:webmyhome
 * File Name:OpinionServiceImpl.java
 * Package Name:com.myhome.service.impl
 * Date:2015年9月21日下午6:19:49
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Opinion;
import com.myhome.service.IOpinionService;

/**
 * gwb
 * ClassName:OpinionServiceImpl <br/>
 * Date:     2015年9月21日 下午6:19:49 <br/>
 * @see 	 
 */
@Service("opinionServiceImpl")
public class OpinionServiceImpl extends AbstractServiceImpl  implements IOpinionService{

	@Override
	@Transactional
    public void addOpinion(Opinion op) throws Exception{
	   super.getOpinionDao().addOpinion(op);
	    
    }

	
	

}

