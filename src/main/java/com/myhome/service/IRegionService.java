package com.myhome.service;

import java.util.List;
import java.util.Map;

import com.myhome.entity.Region;



public interface IRegionService extends IService {

    public Region get(Long id);

	public List<Map> getRegionListMobile(int level) throws Exception;

	public List<Map> getRegionParentByCodeMobile( Integer regionCode) throws Exception;

    public Region getRegionByName(String searchregion);

	public List<Map<String, String>> getRegion(Long code,Integer level)  throws Exception;

	public void update(Region region);




}
