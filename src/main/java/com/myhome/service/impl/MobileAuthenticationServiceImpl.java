package com.myhome.service.impl;

import com.myhome.service.IMobileAuthenticationService;
import com.myhome.entity.MobileAuthentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("mobileAuthenticationServiceImpl")
public class MobileAuthenticationServiceImpl extends AbstractServiceImpl 
        implements IMobileAuthenticationService {

    

    



    @Override
    @Transactional(readOnly=true)
    public MobileAuthentication get(Long id) {
        return getMobileAuthenticationDAO().get(id);
    }
    
    
    @Override
    @Transactional
    public void add(MobileAuthentication mobileAuthentication) {
    	getMobileAuthenticationDAO().add(mobileAuthentication);
    }
    
    
    @Transactional
    public int update(MobileAuthentication mobileAuthentication) {
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
