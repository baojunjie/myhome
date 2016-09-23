package com.myhome.service;

import java.util.List;

import com.myhome.entity.GiftsExchange;

public interface IGiftsExchangeService extends IService {

	void add(GiftsExchange giftsExchange) throws Exception;

	List<GiftsExchange> get(GiftsExchange giftsExchange) throws Exception;


}

