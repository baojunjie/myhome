package com.myhome.entity;

import java.util.List;

/**
 * 
 * 基础数据表
 * 
 */
public class BaseDataDictionaryType extends AbstractEntity {

	/**
	 * 
	 * 基础数据类别名称
	 * 
	 */
	private String name;

	/**
	 * 
	 * 基础数据编码
	 * 
	 */
	private String code;

	private List<BaseDataDictionary> list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<BaseDataDictionary> getList() {
		return list;
	}

	public void setList(List<BaseDataDictionary> list) {
		this.list = list;
	}

}
