package com.myhome.service;

import com.myhome.entity.HaveToDecorate;

public interface IHaveToDecorateService extends IService {

	public void add(HaveToDecorate haveToDecorate) throws Exception;

	boolean getMyBalconyByUser(Long id) throws Exception;

}

