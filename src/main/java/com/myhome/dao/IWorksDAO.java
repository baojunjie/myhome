package com.myhome.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.myhome.entity.Region;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ArtistModel;



public interface IWorksDAO extends IDAO {
	
	public List<Works> getWorksByTime(Integer index,Integer size);

    public Works get(Long id);

    
    /**
     * app 作品展现
     * getMobileWorksList
     * @author gwb
     * @param works
     * @return
     * 2015年9月9日 下午1:44:24
     */
	public List<Works> getMobileWorksList(Works works) throws Exception;





	public long addMobileWork(Works works) throws Exception;


	public List<Map<String, Object>> getWorksInfoMobile(Long id) throws Exception;


	public void deleteWorksMobile(Long id) throws Exception;


	public void updateWorksMobile (Works works)  throws Exception;


	public Map<String, Object> getgetWorksByIdMobile(Long id) throws Exception;

	/**
	 * 获取画家参赛作品列表
	 * getArtistGameWorks
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月12日 下午3:13:04
	 */
	public List<Map<String, Object>> getArtistGameWorks(Long id,int pageNo,int pageSize,int status) throws Exception;


	public int getCheckWorks(String id, int status) throws Exception;


	/**
	 * 获取最新作品
	 * getWorksMobile
	 * @author gwb
	 * @param age
	 * @param region_code
	 * @param male
	 * @param pageSize
	 * @param pageIndex
	 * @param type
	 * @return
	 * 2015年9月16日 上午10:22:22
	 */
	public List<Map<String,Object>> getWorksMobile(Integer age, String region_code, String male, int pageSize, int pageIndex,
            String type,int status,	String typeRegion) throws Exception;


	/**
	 * 添加评论num
	 * addCommentNum
	 * @author gwb
	 * @param id
	 * @throws Exception
	 * 2015年9月18日 下午1:50:37
	 */
	public void addCommentNum(Long id) throws Exception;

//////////////////////////////////////////////////////////////////////三八/////////////////////////////////////////////


    public List<ArtistModel> getBoyList();


    public List<ArtistModel> getGirlList();


    public List<Map<String,Object>> getTop100();


    public List<Works> getListByAge(Integer age);


    public int getCountByAge(Integer age,String male)throws Exception;

    
    public List<Works> getListByAgeAndMale(Integer age, boolean male);


    public List<Works> getListByRegion(Long region);

    public int getCountByRegion(Long region,String male)throws Exception;

    public List<Works> getListByRegionAndMale(Long region, boolean male);


    public Integer getByArtistid(long artist_id, Integer type);


    public List<Works> getWorksByAgeAndMaleAndRegion(Integer age, Boolean male,
            Integer code);
    public List<Works> getWorksByAgeAndMaleAndRegion1(Integer age, Boolean male,
        Integer code);

    public List<Works> getListByAge0fPage(Integer searchage, int index,
            Integer size);


    public List<Works> getListByAgeAndMale0fPage(Integer age, boolean male,
            int index, Integer size);


    public List<Works> getListByRegionOfPage(Long region, int index,
            Integer size);


    public List<Works> getListByRegionAndMaleOfPage(Long region,
            boolean male, int index, Integer size);


    public List<WorksTagItem> getByArtistidOfPage(long artist_id, Integer type,
            Integer index, Integer size);


    public WorksTagItem getWorksTagItemByWorksid(Long worksid);

    /**
     * 通过id获取works数据
     * @param id
     * @return
     */
    public Works getById(long id);


    public int getWorksCount();


    public List<Map<String, Object>> getWorksOnStateThree()throws Exception;

    public Map<String, Object> getTheWorkSort(Long worksid);

    public int getWorksByRegion(Region region);


    public Works getWorksByArtistId(Works works);

	public int getArtistByRegion(Region region);

	/**
	 * 模糊查询
	 * @param keywords
	 * @param index
	 * @param size
	 * @return
	 */
	public List<WorksTagItem> findByKeywords(String keywords, String index, String size);

	/**
	 * 推荐作品
	 * @return
	 */
	public List<WorksTagItem> findList(Integer index,Integer size);
	/**
	 * count
	 * @param keywords
	 * @return
	 */

	public Integer countByKeywords(String keywords);


    public List<Map<String, Object>> getCitySort() throws Exception;

    /**
     * 
     * @description
     * @author lqf
     * @date 2016年3月15日
     * @param type
     * @return
     */
	public List<WorksTagItem> findListByType(Integer type, String age,String size,
			String index,String condition,String awards);

	/**
	 * 
	 * @description
	 * @author lqf
	 * @date 2016年3月15日
	 * @param type
	 * @return
	 */
	public Integer findCountByType(Integer type,String age,String award);


	public List<WorksTagItem> findPassWorks();


//    public CityAndWork getWorksWithoutRegion();







}

