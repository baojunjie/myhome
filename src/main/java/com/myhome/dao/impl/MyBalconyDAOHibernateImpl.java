package com.myhome.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IMyBalconyDAO;
import com.myhome.entity.MyBalcony;


@Component("myBalconyDAOHibernateImpl")
public class MyBalconyDAOHibernateImpl extends AbstractDAOHibernateImpl implements IMyBalconyDAO {

    @Override
    protected Class getEntityClass() {
        return MyBalcony.class;
    }

	@Override
	public boolean getMyBalconyByUser(Long id) throws Exception {
		
		String sql=" select *  from t_my_balcony where user_id=:id";
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createSQLQuery(sql);
		query.setLong("id", id);
		return query.list().size()>0;
	}

}

