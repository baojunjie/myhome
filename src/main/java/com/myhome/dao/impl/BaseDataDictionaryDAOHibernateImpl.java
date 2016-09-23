package com.myhome.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IBaseDataDictionaryDAO;
import com.myhome.entity.BaseDataDictionary;

@Component("baseDataDictionaryDAOHibernateImpl")
public class BaseDataDictionaryDAOHibernateImpl extends AbstractDAOHibernateImpl implements IBaseDataDictionaryDAO {

	@Override
	protected Class getEntityClass() {
		return BaseDataDictionary.class;
	}

	@Override
	public List<BaseDataDictionary> getBaseDataDictionary(String code) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = " from BaseDataDictionary where baseDataCode=:code";
		Query query = session.createQuery(hql);
		query.setString("code", code);
		return query.list();
	}

}
