package com.myhome.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.BaseDataDictionary;
import com.myhome.service.IBaseDataDictionaryService;

@Component("baseDataDictionaryServiceImpl")
public class BaseDataDictionaryServiceImpl extends AbstractServiceImpl implements IBaseDataDictionaryService {

	@Override
	@Transactional
	public List<BaseDataDictionary> getBaseDataDictionary(String code) throws Exception {

		return super.getBaseDataDictionaryDAO().getBaseDataDictionary(code);

	}

}
