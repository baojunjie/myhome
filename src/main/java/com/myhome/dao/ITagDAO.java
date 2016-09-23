package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Region;
import com.myhome.entity.Tag;



public interface ITagDAO extends IDAO {

    public Tag get(Long id);

	public List<Tag> getTagList() throws Exception;

	public List<Region> getListByLevelAndCode(Integer level, String code);
	/**
	 * 作品类型
	 * @param worksType
	 * @return
	 */
	public List<Tag> getTagListByName(String worksType);




}
