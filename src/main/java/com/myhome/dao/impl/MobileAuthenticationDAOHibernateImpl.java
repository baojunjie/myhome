package com.myhome.dao.impl;

import com.myhome.dao.IMobileAuthenticationDAO;
import com.myhome.entity.MobileAuthentication;
import org.springframework.stereotype.Component;




@Component("mobileAuthenticationDAOHibernateImpl")
public class MobileAuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IMobileAuthenticationDAO {

    

    



    @Override
    public MobileAuthentication get(Long id) {
        return (MobileAuthentication) super.get(id);
    }
    
    
    public void add(MobileAuthentication mobileAuthentication) {
        // TODO 待完成
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return MobileAuthentication.class;
    }

}
