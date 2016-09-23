package com.myhome.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IWantToDecorateDAO;
import com.myhome.entity.WantToDecorate;
@Component("wantToDecorateDAOHibernateImpl")
public class WantToDecorateDAOHibernateImpl extends AbstractDAOHibernateImpl implements IWantToDecorateDAO{
    @Override
    protected Class<?> getEntityClass() {
        // TODO Auto-generated method stub
        return WantToDecorate.class;
    }
    @Override
    public WantToDecorate get(Long id) {
          return (WantToDecorate) super.get(id);
    }
    
	@Override
	public void addMobileWantToDecorate(WantToDecorate wantToDecorate)
			throws Exception {
		super.add(wantToDecorate);
	}
    @Override
    public boolean getWantToDecorateByUser(Long id) throws Exception {
        String sql=" select *  from t_want_to_decorate where user_id=:id";
        Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query=session.createSQLQuery(sql);
        query.setLong("id", id);
        return query.list().size()>0;
    }
}
