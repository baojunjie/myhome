package com.myhome.dao;

import com.myhome.entity.WantBuyHouse;

public interface IWantBuyHouseDAO extends IDAO{
    public WantBuyHouse get(Long id);

	public void addMobileWantBuyHouse(WantBuyHouse wantBuyHouse) throws Exception;

    public boolean getWantBuyHouseByUser(Long id) throws Exception;


}
