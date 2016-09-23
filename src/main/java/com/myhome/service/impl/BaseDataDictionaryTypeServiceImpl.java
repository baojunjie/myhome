package com.myhome.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.BaseDataDictionary;
import com.myhome.entity.BaseDataDictionaryType;
import com.myhome.service.IBaseDataDictionaryTypeService;

@Component("baseDataDictionaryTypeServiceImpl")
public class BaseDataDictionaryTypeServiceImpl extends AbstractServiceImpl implements IBaseDataDictionaryTypeService {

	/**
	 * 获取企业类别大类
	 * 
	 * @see com.myhome.service.IBaseDataDictionaryTypeService#getEnterpriseSort(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseDataDictionaryType> getEnterpriseSort(String type) throws Exception {
		List<BaseDataDictionaryType> list = super.getBaseDataDictionaryTypeDAO().getEnterpriseSort(type);

		for (int i = 0; i < list.size(); i++) {

			List<BaseDataDictionary> baseDataDictionaryList = super.getBaseDataDictionaryDAO().getBaseDataDictionary(
			        list.get(i).getCode());

			list.get(i).setList(baseDataDictionaryList);

		}

		return list;
	}
}
