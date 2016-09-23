package com.myhome.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Region;
import com.myhome.service.IRegionService;



@Component("regionServiceImpl")
public class RegionServiceImpl extends AbstractServiceImpl 
        implements IRegionService {

    

    



    @Override
    @Transactional(readOnly=true)
    public Region get(Long id) {
        return getRegionDAO().get(id);
    }
    
    
    @Transactional
    public void add(Region region) {
        // TODO 待完成
    }
    
    
    @Transactional
    public void update(Region region) {
    	getRegionDAO().update(region);
    }
    
    
    @Transactional
    public int remove(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int delete(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }


    /**
     * 根据level查询region
     * TODO 简单描述该方法的实现功能（可选）.
     * @see com.myhome.service.IRegionService#getRegionListMobile(int)
     */
	@Override
	@Transactional
    public List<Map> getRegionListMobile(int level) throws Exception {
	    
	    return  super.getRegionDAO().getRegionListMobile(level);
	    
    }


	/**
	 * 根据 region_code 查询list
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.myhome.service.IRegionService#getRegionParentByCodeMobile(int, java.lang.Integer)
	 */
	@Override
	@Transactional
    public List<Map> getRegionParentByCodeMobile(Integer regionCode) throws Exception {
		  return  super.getRegionDAO().getRegionParentByCodeMobile(regionCode);
    }


    @Override
    public Region getRegionByName(String searchregion) {
        return getRegionDAO().getRegionByName(searchregion);
    }


	@Override
	@Transactional
    public List<Map<String, String>> getRegion(Long code,Integer  level) throws Exception {
	    return getRegionDAO().getRegion(code,level);
    }
    
    
    

}
