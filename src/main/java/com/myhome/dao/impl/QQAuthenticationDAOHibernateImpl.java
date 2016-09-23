package com.myhome.dao.impl;

import java.util.List;

import com.myhome.entity.QQAuthentication;
import com.myhome.dao.IQQAuthenticationDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




@Component("QQAuthenticationDAOHibernateImpl")
public class QQAuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IQQAuthenticationDAO {

    

    



    @Override
    public QQAuthentication get(Long id) {
        return (QQAuthentication) super.get(id);
    }
    
    
    public void add(QQAuthentication qQAuthentication) {
        // TODO 待完成
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return QQAuthentication.class;
    }


	@Override
	public List<QQAuthentication> QueryByLogin(String openid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from QQAuthentication where invalid = false  and login=:login";
            Query query = s.createQuery(hql);
            query.setString("login", openid);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

}
