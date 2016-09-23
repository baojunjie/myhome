package com.myhome.service;

import com.myhome.entity.LoginAuthentication;



public interface ILoginAuthenticationService extends IService {

    public LoginAuthentication get(Long id);
    /**
     * 添加
     * @param loginAuthentication
     */
	public void add(LoginAuthentication loginAuthentication);
	/**
	 *批量注册
	 * @param mobile
	 * @param name
	 */
	public Long registerImport(String mobile, String name);




}
