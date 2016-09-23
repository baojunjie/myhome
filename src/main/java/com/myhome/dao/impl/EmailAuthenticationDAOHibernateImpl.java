package com.myhome.dao.impl;

import com.myhome.entity.EmailAuthentication;
import com.myhome.dao.IEmailAuthenticationDAO;
import org.springframework.stereotype.Component;




@Component("emailAuthenticationDAOHibernateImpl")
public class EmailAuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IEmailAuthenticationDAO {

    

    



    @Override
    public EmailAuthentication get(Long id) {
        return (EmailAuthentication) super.get(id);
    }
    
    
    public void add(EmailAuthentication emailAuthentication) {
        // TODO 待完成
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return EmailAuthentication.class;
    }

}
