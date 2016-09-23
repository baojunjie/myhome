package com.myhome.dao.impl;

import java.util.List;

import com.myhome.dao.IWeixinAuthenticationDAO;
import com.myhome.entity.WeixinAuthentication;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;




@Component("weixinAuthenticationDAOHibernateImpl")
public class WeixinAuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IWeixinAuthenticationDAO {

    

    



    @Override
    public WeixinAuthentication get(Long id) {
        return (WeixinAuthentication) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return WeixinAuthentication.class;
    }





	@Override
	public List<WeixinAuthentication> QueryByLogin(String openid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from WeixinAuthentication where invalid = false  and login=:login";
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
