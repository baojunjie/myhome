package com.myhome.dao;

import java.util.List;
import java.util.Map;

import com.myhome.entity.Region;



public interface IRegionDAO extends IDAO {

    public Region get(Long id);

	public List<Map> getRegionListMobile(int level) throws Exception;

	public List<Map> getRegionParentByCodeMobile(Integer regionCode) throws Exception;

    public Region getRegionByName(String searchregion);

	public List<Map<String, String>> getRegion(Long code,Integer  level)throws Exception;





}
