package com.myhome.dao;

import java.util.List;

import com.myhome.entity.BaseDataDictionaryType;

public interface IBaseDataDictionaryTypeDAO extends IDAO {

	/**
	 * 获取企业类别大类
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	List<BaseDataDictionaryType> getEnterpriseSort(String type) throws Exception;

}
