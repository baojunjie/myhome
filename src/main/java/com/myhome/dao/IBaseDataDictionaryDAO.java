package com.myhome.dao;

import java.util.List;

import com.myhome.entity.BaseDataDictionary;

public interface IBaseDataDictionaryDAO extends IDAO {

	List<BaseDataDictionary> getBaseDataDictionary(String code) throws Exception;

}
