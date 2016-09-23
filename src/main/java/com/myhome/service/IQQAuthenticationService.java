package com.myhome.service;

import java.util.List;

import com.myhome.entity.QQAuthentication;
import com.myhome.entity.WeixinAuthentication;



public interface IQQAuthenticationService extends IService {

    public QQAuthentication get(Long id);
    /**
     * 通过openid收索
     * @param openid
     * @return
     */
	public List<QQAuthentication> QueryByLogin(String openid);
	/**
	 * 保存
	 * @param qq认证
	 */
	public void add(QQAuthentication qq);



}
