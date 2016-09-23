package com.myhome.service.impl;

import java.util.List;

import com.myhome.service.IWeiboAuthenticationService;
import com.myhome.entity.WeiboAuthentication;
import com.myhome.entity.WeixinAuthentication;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("weiboAuthenticationServiceImpl")
public class WeiboAuthenticationServiceImpl extends AbstractServiceImpl 
        implements IWeiboAuthenticationService {

    

    



    @Override
    @Transactional(readOnly=true)
    public WeiboAuthentication get(Long id) {
        return getWeiboAuthenticationDAO().get(id);
    }
    
    
    @Transactional
    public void add(WeiboAuthentication weiboAuthentication) {
        // TODO 待完成
    }
    
    
    @Transactional
    public void update(WeiboAuthentication weiboAuthentication) {
        getWeiboAuthenticationDAO().update(weiboAuthentication);
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
    public List<WeiboAuthentication> QueryByLogin(String openid) {
        return getWeiboAuthenticationDAO().QueryByLogin(openid);
    }
    

}
