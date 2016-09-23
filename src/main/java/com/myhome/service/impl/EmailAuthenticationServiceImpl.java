package com.myhome.service.impl;

import com.myhome.entity.EmailAuthentication;
import com.myhome.service.IEmailAuthenticationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("emailAuthenticationServiceImpl")
public class EmailAuthenticationServiceImpl extends AbstractServiceImpl 
        implements IEmailAuthenticationService {

    

    



    @Override
    @Transactional(readOnly=true)
    public EmailAuthentication get(Long id) {
        return getEmailAuthenticationDAO().get(id);
    }
    
    
    @Transactional
    public void add(EmailAuthentication emailAuthentication) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(EmailAuthentication emailAuthentication) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int remove(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int delete(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    

}
