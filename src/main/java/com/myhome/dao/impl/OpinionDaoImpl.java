/**
 * Project Name:webmyhome
 * File Name:IVersionDaoImpl.java
 * Package Name:com.myhome.dao.impl
 * Date:2015年9月21日下午7:27:46
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.dao.impl;

import org.springframework.stereotype.Repository;

import com.myhome.dao.IOpinionDao;
import com.myhome.entity.Opinion;

/**
 * ClassName:IVersionDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年9月21日 下午7:27:46 <br/>
 * @author   1
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Repository("opinionDaoImpl")
public class OpinionDaoImpl extends AbstractDAOHibernateImpl implements IOpinionDao{

	@Override
    protected Class getEntityClass() {
	    
	    return Opinion.class;
    }

	@Override
    public void addOpinion(Opinion op) throws Exception{
	   add(op);
    }

}

