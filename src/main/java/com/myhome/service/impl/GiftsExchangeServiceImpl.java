package com.myhome.service.impl;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.GiftsExchange;
import com.myhome.service.IGiftsExchangeService;

@Component("giftsExchangeServiceImpl")
public class GiftsExchangeServiceImpl extends AbstractServiceImpl implements IGiftsExchangeService {

	@Override
	@Transactional
    public void add(GiftsExchange giftsExchange) throws Exception {
		super.getGiftsExchangeDAO().add(giftsExchange);
    }

	@Override
	@Transactional
    public List<GiftsExchange> get(GiftsExchange giftsExchange) throws Exception {
	    return super.getGiftsExchangeDAO().getGiftsExchangeList(giftsExchange);
    }

}

