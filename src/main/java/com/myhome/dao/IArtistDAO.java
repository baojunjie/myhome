package com.myhome.dao;


import java.util.List;
import java.util.Map;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.vo.ArtistModel;



public interface IArtistDAO extends IDAO {

    public Artist get(Long id);

    public List<ArtistModel> getList(Integer searchmale, Long sregionid,
            Integer searchage,Integer index,Integer size);
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

	public Artist getByUserid(String userid);

    public void updateweb(Artist artist);

    public Integer getListCount(Integer searchmale, Long sregionid,
            Integer searchage);

    public ArtistInfo getUserArtistInfoMobile(ArtistInfo artist) throws Exception;
    /**
     * 模糊查询
     * @param keywords
     * @param index
     * @param size
     * @return
     */
	public List<Artist> findByKeywords(String keywords, String index,
			String size);

	public Integer countByKeywords(String keywords);
	/**
	 * 根据城市排序
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
	public List<Map<String, Object>> findListByWorksType(String type,String age, int size,
			int index, String awards);
	/**
	 * 
	 * @description
	 * @author lqf
	 * @date 2016年3月15日
	 * @param type
	 * @return
	 */
	public Integer findCountByWorksType(String type,String age, String awards);

	public List<Map<String, Object>> findPassArtist();
}

