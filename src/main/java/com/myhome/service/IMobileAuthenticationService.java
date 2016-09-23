package com.myhome.service;

import com.myhome.entity.MobileAuthentication;



public interface IMobileAuthenticationService extends IService {

    public MobileAuthentication get(Long id);

	public void add(MobileAuthentication mobileAuthentication);




}
