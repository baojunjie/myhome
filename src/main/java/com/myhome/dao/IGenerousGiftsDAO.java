package com.myhome.dao;

import java.util.List;

import com.myhome.entity.GenerousGifts;

public interface IGenerousGiftsDAO extends IDAO{
    public GenerousGifts get(Long id);

	public void addMobileGenerousGifts(GenerousGifts generousGifts) throws Exception;

	public List<GenerousGifts>  getMobileGenerousGifts(GenerousGifts generousGifts) throws Exception;
}
