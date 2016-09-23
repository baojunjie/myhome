package com.myhome.dao;

import java.util.List;

import com.myhome.entity.GiftsExchange;

public interface IGiftsExchangeDAO extends IDAO{

	List<GiftsExchange> getGiftsExchangeList(GiftsExchange giftsExchange) throws Exception;

}
