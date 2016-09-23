package com.myhome.dao;

import java.util.List;

import com.myhome.entity.WeixinAuthentication;



public interface IWeixinAuthenticationDAO extends IDAO {

    public WeixinAuthentication get(Long id);
    /**
     * 通过openid查询
     * @param openid
     * @return
     */
	public List<WeixinAuthentication> QueryByLogin(String openid);




}
