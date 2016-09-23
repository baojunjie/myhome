package com.myhome.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.myhome.entity.AbstractEntity;
import com.myhome.entity.Region;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.entity.vo.GameVo;
import com.myhome.entity.vo.ImpWorks;
import com.myhome.entity.vo.WorksVo;



public interface IWorksService extends IService {
	
	public List<Works> getWorksByTime(Integer index,Integer size);

    public Works get(Long id);

	public List<Works> getMobileWorksList(Works works) throws Exception;

	public List<ArtistModel> getBoyList();

	public List<ArtistModel> getGirlList();

	public void update(Works works);



	public long addMobileWork(Works works)  throws Exception;

	public List<Map<String, Object>> getWorksInfoMobile(Long id) throws Exception;

	public void deleteWorksMobile  (Long id)  throws Exception;

	public boolean updateWorksMobile (WorksVo works)  throws Exception;

	public Map<String, Object> getgetWorksByIdMobile(Long id) throws Exception;

	/**
	 * 获取画家参赛作品列表
	 * getArtistGameWorks
	 * @author gwb
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * 2015年9月16日 下午1:55:13
	 */
	public List<Map<String, Object>> getArtistGameWorks(Long id,int pageNo,int pageSize,int status) throws Exception;

	public boolean getCheckWorks(String[] ids,int status) throws Exception;

	
	/**
	 * 最新画家
	 * getWorksByAgeAndMaleAndRegionMobile
	 * @author gwb
	 * @param age
	 * @param b
	 * @param object
	 * @return
	 * 2015年9月16日 上午10:18:30
	 */
	public List<Map<String,Object>> getWorksMobile(Integer age, String region_code, String male, int pageSize,
            int pageIndex, String type,int status,String typeRegion) throws Exception;

	/**
	 * 修改画家信息 同时修改画家作品信息
	 * updateWorksMobile
	 * @author gwb
	 * @param works
	 * 2015年9月24日 下午5:34:20
	 * @throws Exception 
	 */
	public void updateWorksMobile(Works works) throws Exception;
//////////////////////////////////////////////////////////////////////////////////////////////////////


    public List<Works> getListByAge(Integer age);

    public int getCountByAge(Integer age,String male)throws Exception;

    public List<Works> getListByAgeAndMale(Integer searchmale,
            boolean male);

    public List<Works> getListByRegion(Long long1);

    public int getCountByRegion(Long region, String male)throws Exception;

    public List<Works> getListByRegionAndMale(Long long1,
            boolean male);

    public Integer getByArtistid(long artist_id, Integer type);

    public List<Works> getWorksByAgeAndMaleAndRegion(Integer age, Boolean b,
            Integer code);
    public List<Works> getWorksByAgeAndMaleAndRegion1(Integer age, Boolean b,
        Integer code);

    public List<Works> getListByAge0fPage(Integer searchage, int index, Integer size);

    public List<Works> getListByAgeAndMale0fPage(Integer searchage, boolean b,
            int i, Integer size);

    public List<Works> getListByRegionOfPage(Long long1, int i,
            Integer size);

    public List<Works> getListByRegionAndMaleOfPage(Long long1,
            boolean b, int i, Integer size);

    public List<WorksTagItem> getByArtistidOfPage(long artist_id, Integer type, Integer index, Integer size);
    /**
     * 通过作品id获取作品标签和作品的相关表数据
     * @param 作品id
     * @return
     */
    public WorksTagItem getWorksTagItemByWorksid(Long worksid);
    /**
     * 上传作品
     * @param game
     * @param userid
     * @param artistid
     * @return
     */
    public String upload(GameVo game, String userid, String artistid);
    /**
     * 修改上传作品的信息
     * @param game
     * @param userid
     * @param artistid
     * @return
     */
    public Works uploaduUpdate(GameVo game, String userid, String artistid);

    public Works getById(long l);

    public int getWorksCount();

    public List<Map<String, Object>> getCitySort() throws Exception;
    
    public List<Map<String, Object>> getWorksOnStateThree()throws Exception;

    public Map<String, Object> getTheWorkSort(Long worksid);
//    public CityAndWork getWorksWithoutRegion();

    public int getWorksByRegion(Region region);
    /**
	 * 作品批量导入时添加图片
	 * @param filename
	 * @param artistId
	 */
	public Long addImg(String filename);
	/**
	 * 批量成为小画家
	 * @param impWorks
	 */
	public void batchImport(ImpWorks impWorks,Long userid);
	/**
	 * 
	 * @param works
	 */
	public void add(Works works);

	public int getArtistByRegion(Region region);
	/**
	 * 模糊查询
	 * @param size 
	 * @param index 
	 * @param keywords 
	 * @return
	 */
	public List<WorksTagItem> findByKeywords(String keywords, String index, String size);
	/**
	 * 查询推荐作品
	 * @return
	 */
	public List<WorksTagItem> findList(Integer index,Integer size);
	/**
	 * 数量
	 * @param keywords
	 * @return
	 */
	public Integer countByKeywords(String keywords);
	/**
	 * 
	 * @description根据入围级别获取作品
	 * @author lqf
	 * @date 2016年3月15日
	 * @param valueOf
	 * @return
	 */

	public List<WorksTagItem> findListByType(Integer type, String size,
			String index,String condition,String age,String awards);
	/**
	 * 
	 * @description根据入围级别获取作品总数
	 * @author lqf
	 * @date 2016年3月15日
	 * @param valueOf
	 * @return
	 */
	public Integer findCountByType(Integer valueOf,String age,String awards);

	public List<WorksTagItem> findPassWorks();

	public void uploadImg(GameVo game, Long id);

    public Works getWorksByArtistId(Works works);


}
