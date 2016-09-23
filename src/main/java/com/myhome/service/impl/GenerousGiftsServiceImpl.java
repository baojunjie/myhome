package com.myhome.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.GenerousGifts;
import com.myhome.service.IGenerousGiftsService;
@Component("generousGiftsServiceImpl")
public class GenerousGiftsServiceImpl extends AbstractServiceImpl implements IGenerousGiftsService{

	@Override
	@Transactional
	public void addMobileGenerousGifts(GenerousGifts generousGifts)
			throws Exception {

		super.getGenerousGiftsDAO().addMobileGenerousGifts(generousGifts);
	}

	@Override
	@Transactional(readOnly=true)
	public List<GenerousGifts>  getMobileGenerousGifts(GenerousGifts generousGifts)
			throws Exception {
		return super.getGenerousGiftsDAO().getMobileGenerousGifts(generousGifts);
	}

	@Override
	@Transactional
    public void addMobileGenerousGiftsList(List<GenerousGifts> list) throws Exception {
		for(GenerousGifts generousGifts:list ){
			super.getGenerousGiftsDAO().getMobileGenerousGifts(generousGifts);
		}
		
    }

	

	
}
