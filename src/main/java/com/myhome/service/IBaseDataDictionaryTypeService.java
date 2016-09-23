package com.myhome.service;

import java.util.List;

import com.myhome.entity.BaseDataDictionaryType;

public interface IBaseDataDictionaryTypeService extends IService {

	/**
	 * 获取企业类别大类
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	List<BaseDataDictionaryType> getEnterpriseSort(String string) throws Exception;

}
