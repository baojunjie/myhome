package com.myhome.dao.impl;

import java.util.List;

import com.myhome.dao.IWeiboAuthenticationDAO;
import com.myhome.entity.WeiboAuthentication;
import com.myhome.entity.WeixinAuthentication;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




@Component("weiboAuthenticationDAOHibernateImpl")
public class WeiboAuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IWeiboAuthenticationDAO {

    

    



    @Override
    public WeiboAuthentication get(Long id) {
        return (WeiboAuthentication) super.get(id);
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return WeiboAuthentication.class;
    }

    @Override
    public List<WeiboAuthentication> QueryByLogin(String openid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from WeiboAuthentication where invalid = false  and login=:login";
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
