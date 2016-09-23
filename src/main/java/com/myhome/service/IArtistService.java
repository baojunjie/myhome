package com.myhome.service;



import java.util.List;
import java.util.Map;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.vo.ArtistModel;




public interface IArtistService extends IService {

    public Artist get(Long id) ;
    
    public boolean addMobileArtist(Artist artist) throws Exception;

    public void updateMobileArtist(Artist artist) throws Exception;

    public Map<String, Object> getArtistMobile(Long id) throws Exception;

    public void checkArtistMobile(Long id,int status) throws Exception;

    /**
     * 获取画家列表   最新画家，画家排行
     * getArtistListMobile
     * @author gwb
     * @param age
     * @param region_code
     * @param male
     * @param pageSize
     * @param pageIndex
     * @param type  1最新画家，2画家排行
     * @return
     * @throws Exception
     * 2015年9月16日 上午11:22:57
     */
    public List<Map<String, Object>> getArtistListMobile(Integer age, String region_code, String male, int pageSize,
            int pageIndex, String type,int status,String typeRegion) throws Exception;

    
	public ArtistInfo getUserArtistInfoMobile(ArtistInfo artistInfo) throws Exception;
////////////////////////////////////////////////////////////////////////////////////



    public List<ArtistModel> getList(Integer searchmale, Long regionid,
            Integer searchage,Integer index,Integer size);

    public Integer getListCount(Integer searchmale, Long searchregion,
            Integer searchage);

    public Artist getByUserid(String userid);

    public int update(Artist artist);

	public void add(Artist artist);
	/**
	 * 模糊查询
	 * @param keywords
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Artist> findByKeywords(String keywords, String index,
			String size);
	/**
	 * count
	 * @param keywords
	 * @return
	 */
	public Integer countByKeywords(String keywords);
	/**
	 * 更具人物排序
	 * @return
	 */
	public List<Map<String, Object>> getCitySort();
	/**
	 * 
	 * @description
	 * @author lqf
	 * @date 2016年3月15日
	 * @param type
	 * @param size
	 * @param index
	 * @return
	 */
	public List<Map<String, Object>> findListByWorksType(String type, String age,int size,
			int index,String awards);
	/**
	 * 
	 * @description
	 * @author lqf
	 * @date 2016年3月15日
	 * @param valueOf
	 * @return
	 */
	public Integer findCountByWorksType(String type, String age,String awards);

	public List<Map<String, Object>> findPassArtist();








}

