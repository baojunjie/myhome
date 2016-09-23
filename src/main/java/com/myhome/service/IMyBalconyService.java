package com.myhome.service;

import com.myhome.entity.MyBalcony;

public interface IMyBalconyService extends IService {

	void add(MyBalcony myBalcony) throws Exception;

	boolean getMyBalcony(Long id) throws Exception;

}
