package com.myhome.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IBaseDataDictionaryTypeDAO;
import com.myhome.entity.BaseDataDictionaryType;

@Component("baseDataDictionaryTypeDAOHibernateImpl")
public class BaseDataDictionaryTypeDAOHibernateImpl extends AbstractDAOHibernateImpl implements
        IBaseDataDictionaryTypeDAO {

	@Override
	protected Class getEntityClass() {
		return BaseDataDictionaryType.class;
	}

	/**
	 * 获取企业类别大类
	 * 
	 * @see com.myhome.dao.IBaseDataDictionaryTypeDAO#getEnterpriseSort(java.lang.String)
	 */
	@Override
	public List<BaseDataDictionaryType> getEnterpriseSort(String code) throws Exception {
		String hql = " from BaseDataDictionaryType where code like ?";
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, code + "%");
		return query.list();
	}

}
