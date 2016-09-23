package com.myhome.service;

import java.util.List;

import com.myhome.entity.WeixinAuthentication;



public interface IWeixinAuthenticationService extends IService {

    public WeixinAuthentication get(Long id);
    /**
     * 通过openid收索
     * @param openid
     * @return
     */
	public List<WeixinAuthentication> QueryByLogin(String openid);
	/**
	 * 保存
	 * @param weixin
	 */
	public void add(WeixinAuthentication weixin);
	/**
	 * 修改
	 * @param weixinAuthentication
	 */
	public void update(WeixinAuthentication weixinAuthentication);




}
