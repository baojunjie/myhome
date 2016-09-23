package com.myhome.service.impl;

import java.util.List;

import com.myhome.entity.WeixinAuthentication;
import com.myhome.service.IWeixinAuthenticationService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("weixinAuthenticationServiceImpl")
public class WeixinAuthenticationServiceImpl extends AbstractServiceImpl 
        implements IWeixinAuthenticationService {

    

    



    @Override
    @Transactional(readOnly=true)
    public WeixinAuthentication get(Long id) {
        return getWeixinAuthenticationDAO().get(id);
    }
    
    
    @Transactional
    public void add(WeixinAuthentication weixinAuthentication) {
    	getWeixinAuthenticationDAO().add(weixinAuthentication);
    }
    
    
    @Transactional
    public void update(WeixinAuthentication weixinAuthentication) {
    	getWeixinAuthenticationDAO().update(weixinAuthentication);
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
	public List<WeixinAuthentication> QueryByLogin(String openid) {
		return getWeixinAuthenticationDAO().QueryByLogin(openid);
	}
    
    
    

}
