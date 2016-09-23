package com.myhome.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IActivityDAO;
import com.myhome.entity.Activity;

@Component("activityDAOHibernateImpl")
public class ActivityDAOHibernateImpl extends AbstractDAOHibernateImpl implements IActivityDAO {

	@Override
	public List<Activity> get()  throws Exception{
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = " from Activity  where invalid = false order by time DESC ";
		Query query = session.createQuery(hql);
		List<Activity> list = query.list();
		return list;
	}

	@Override
	protected Class<?> getEntityClass() {
		return Activity.class;
	}

	@Override
	public List<Activity> getIActivity(
			) throws Exception {
		   Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
	        String hql = "";
	            hql = " from Activity  where invalid = false and status in ( 2 ) order by time DESC ";
	        Query query = session.createQuery(hql);
	     
	        List<Activity> list = query.list();
	        return list;
	}
}
