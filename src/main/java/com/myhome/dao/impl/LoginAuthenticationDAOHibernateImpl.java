package com.myhome.dao.impl;

import com.myhome.entity.LoginAuthentication;
import com.myhome.dao.ILoginAuthenticationDAO;
import org.springframework.stereotype.Component;




@Component("loginAuthenticationDAOHibernateImpl")
public class LoginAuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements ILoginAuthenticationDAO {

    

    



    @Override
    public LoginAuthentication get(Long id) {
        return (LoginAuthentication) super.get(id);
    }
    
    
    public void add(LoginAuthentication loginAuthentication) {
        // TODO 待完成
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return LoginAuthentication.class;
    }

}
