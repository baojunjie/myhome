package com.myhome.service;

import java.util.List;

import com.myhome.entity.WeiboAuthentication;



public interface IWeiboAuthenticationService extends IService {

    public WeiboAuthentication get(Long id);

    public List<WeiboAuthentication> QueryByLogin(String login);

    public void update(WeiboAuthentication weiboAuthentication);




}
