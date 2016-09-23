package com.myhome.dao;

import java.util.List;

import com.myhome.entity.WeiboAuthentication;



public interface IWeiboAuthenticationDAO extends IDAO {

    public WeiboAuthentication get(Long id);

    public List<WeiboAuthentication> QueryByLogin(String openid);




}
