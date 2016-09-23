package com.myhome.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IParameterDAO;
import com.myhome.entity.Parameter;

@Component("parameterDAOHibernateImpl")
public class ParameterDAOHibernateImpl extends AbstractDAOHibernateImpl implements IParameterDAO {

    @Override
    public List<Parameter> get() throws Exception {
        Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        String hql = "";
        hql = " from Parameter  where invalid = false ";
        Query query = session.createQuery(hql);
        List<Parameter> list = query.list();
        return list;
    }

    @Override
    protected Class<?> getEntityClass() {
        return Parameter.class;
    }

    @Override
    public Parameter get(Long id) {
        return (Parameter) super.get(id);
    }
}
