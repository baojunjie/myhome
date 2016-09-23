package com.myhome.dao;

import java.util.List;

import com.myhome.entity.QQAuthentication;



public interface IQQAuthenticationDAO extends IDAO {

    public QQAuthentication get(Long id);
    /**
     * qq认证
     * @param openid
     * @return
     */
	public List<QQAuthentication> QueryByLogin(String openid);




}
