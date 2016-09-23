package com.myhome.service;

import java.util.List;

import com.myhome.entity.Region;
import com.myhome.entity.Tag;



public interface ITagService extends IService {

    public Tag get(Long id);

	public List<Tag> getTagList() throws Exception;

	public List<Region> getListByLevelAndCode(Integer i, String code);
	/**
	 * 
	 * @param worksType
	 * @return
	 */
	public List<Tag> getTagListByName(String worksType);




}
