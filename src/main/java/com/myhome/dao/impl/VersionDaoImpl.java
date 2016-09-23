/**
 * Project Name:webmyhome
 * File Name:IVersionDaoImpl.java
 * Package Name:com.myhome.dao.impl
 * Date:2015年9月21日下午7:27:46
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.dao.impl;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.myhome.dao.IVersionDao;
import com.myhome.entity.Version;

/**
 * ClassName:IVersionDaoImpl <br/>
 * Date:     2015年9月21日 下午7:27:46 <br/>
 * @see 	 
 */
@Repository("versionDaoImpl")
public class VersionDaoImpl extends AbstractDAOHibernateImpl implements IVersionDao{

	@Override
    protected Class<Version> getEntityClass() {
	    return Version.class;
    }

	@Override
    public Map<String,String> getVersionMobile() throws Exception{
		String sql="select serviceAddress,versions,size from t_version where invalid=0";
	    Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query query=session.createSQLQuery(sql);
	    query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	    Version ve=new Version();
	    Map<String,String> map= (Map<String, String>) query.uniqueResult();
	    return map;
    }

}

