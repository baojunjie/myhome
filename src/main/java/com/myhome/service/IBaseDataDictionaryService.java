package com.myhome.service;

import java.util.List;

import com.myhome.entity.BaseDataDictionary;

public interface IBaseDataDictionaryService extends IService {

	List<BaseDataDictionary> getBaseDataDictionary(String code) throws Exception;

}
