package com.myhome.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IWantBuyHouseDAO;
import com.myhome.entity.WantBuyHouse;
@Component("wantBuyHouseDAOHibernateImpl")
public class WantBuyHouseDAOHibernateImpl extends AbstractDAOHibernateImpl implements IWantBuyHouseDAO{
    @Override
    protected Class<?> getEntityClass() {
        // TODO Auto-generated method stub
        return WantBuyHouse.class;
    }
    @Override
    public WantBuyHouse get(Long id) {
          return (WantBuyHouse) super.get(id);
    }
	@Override
	public void addMobileWantBuyHouse(WantBuyHouse wantBuyHouse)
			throws Exception {
		super.add(wantBuyHouse);
	}
    @Override
    public boolean getWantBuyHouseByUser(Long id) throws Exception {
        String sql=" select *  from t_want_buy_House where user_id=:id";
        Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query=session.createSQLQuery(sql);
        query.setLong("id", id);
        return query.list().size()>0;
    }

}
