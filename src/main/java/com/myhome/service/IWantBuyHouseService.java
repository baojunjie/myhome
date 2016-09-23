package com.myhome.service;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.WantBuyHouse;
import com.myhome.entity.vo.WantBuyHouseVo;

public interface IWantBuyHouseService extends IService {
	/**
	 * 是否买房文件信息
	 * 
	 * @param userid
	 * @param house
	 * @param request
	 * @return
	 */
	public WantBuyHouse saveOrUpdate(Integer userid, WantBuyHouseVo house, HttpServletRequest request);

	public void addMobileWantBuyHouse(WantBuyHouse wantBuyHouse) throws Exception;

	public boolean getWantBuyHouse(Long id) throws Exception;

}
