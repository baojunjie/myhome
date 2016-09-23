package com.myhome.service.impl;

import java.util.List;

import com.myhome.service.IQQAuthenticationService;
import com.myhome.entity.QQAuthentication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("qQAuthenticationServiceImpl")
public class QQAuthenticationServiceImpl extends AbstractServiceImpl 
        implements IQQAuthenticationService {

    

    



    @Override
    @Transactional(readOnly=true)
    public QQAuthentication get(Long id) {
        return getQQAuthenticationDAO().get(id);
    }
    
    
    @Transactional
    public void add(QQAuthentication qQAuthentication) {
    	getQQAuthenticationDAO().add(qQAuthentication);
    }
    
    
    @Transactional
    public int update(QQAuthentication qQAuthentication) {
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
    
	@Override
	public List<QQAuthentication> QueryByLogin(String openid) {
		return getQQAuthenticationDAO().QueryByLogin(openid);
	}
    
    

}
