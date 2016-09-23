package com.myhome.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IGiftsExchangeDAO;
import com.myhome.entity.GiftsExchange;


@Component("giftsExchangeDAOHibernateImpl")
public class GiftsExchangeDAOHibernateImpl extends AbstractDAOHibernateImpl implements IGiftsExchangeDAO{

    @Override
    protected Class getEntityClass() {
        return GiftsExchange.class;
    }

	@Override
    public List<GiftsExchange> getGiftsExchangeList(GiftsExchange giftsExchange) throws Exception {
		
		String hql=" from GiftsExchange where user.id=:userId";
		
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Query query=session.createQuery(hql);
		
		query.setLong("userId", giftsExchange.getUser().getId());
		
	    return query.list();
    }

}

