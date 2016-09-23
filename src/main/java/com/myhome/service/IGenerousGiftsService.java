package com.myhome.service;

import java.util.List;

import com.myhome.entity.GenerousGifts;

public interface IGenerousGiftsService extends IService{


	public void addMobileGenerousGifts(GenerousGifts generousGifts) throws Exception;

	public List<GenerousGifts>  getMobileGenerousGifts(GenerousGifts generousGifts) throws Exception;

	public void addMobileGenerousGiftsList(List<GenerousGifts> list) throws Exception;

}
