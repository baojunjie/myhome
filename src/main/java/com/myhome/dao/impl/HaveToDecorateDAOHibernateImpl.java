package com.myhome.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IHaveToDecorateDAO;
import com.myhome.entity.HaveToDecorate;


@Component("haveToDecorateDAOHibernateImpl")
public class HaveToDecorateDAOHibernateImpl extends AbstractDAOHibernateImpl implements IHaveToDecorateDAO {

    @Override
    protected Class getEntityClass() {
        return HaveToDecorate.class;
    }

    @Override
    public boolean getHaveToDecorateByUser(Long id) throws Exception{
        String sql=" select *  from t_have_to_decorate where user_id=:id";
        Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query=session.createSQLQuery(sql);
        query.setLong("id", id);
        return query.list().size()>0;
    }

}

