package com.myhome.entity;

/**
 * 
 * 调查问卷
 * 
 */
public class BaseDataDictionary extends AbstractEntity {

	/**
	 * 
	 * 名称
	 * 
	 */
	private String name;

	/**
	 * 
	 * 编码
	 * 
	 */
	private String code;

	/**
	 * 
	 * t_base_data_dictionary_type CODE
	 * 
	 */
	private String baseDataCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBaseDataCode() {
		return baseDataCode;
	}

	public void setBaseDataCode(String baseDataCode) {
		this.baseDataCode = baseDataCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
