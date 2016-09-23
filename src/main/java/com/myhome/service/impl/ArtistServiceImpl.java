package com.myhome.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.service.IArtistService;

@Component("artistServiceImpl")
public class ArtistServiceImpl extends AbstractServiceImpl implements IArtistService {

	@Transactional
	public void add(Artist artist) {
		getArtistDAO().add(artist);
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
	 * 新增我要参赛 TODO 简单描述该方法的实现功能（可选）.
	 * 
	 */
	@Override
	@Transactional
	public boolean addMobileArtist(Artist artist)  throws Exception{

		return getArtistDAO().addMobileArtist(artist);

	}

	@Override
	@Transactional
	public void updateMobileArtist(Artist artist)  throws Exception{

		getArtistDAO().updateMobileArtist(artist);
	}

	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.myhome.service.IArtistService#getArtistMobile(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
    public Map<String, Object> getArtistMobile(Long id)  throws Exception{
	    
	    return getArtistDAO().getArtistMobile(id);
    }
	
	/**
	 * 审核画家信息
	 */
	@Override
	@Transactional
    public void checkArtistMobile(Long id,int status) throws Exception{
	     super.getArtistDAO().checkArtistMobile(id,status);
    }
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
	@Override
	@Transactional(readOnly=true)
    public List<Map<String, Object>> getArtistListMobile(Integer age, String region_code, String male, int pageSize,
            int pageIndex, String type,int status,String typeRegion) throws Exception {
	    
	    return super.getArtistDAO().getArtistListMobile(age,region_code,male,pageSize,pageIndex,type,status,typeRegion);
    }
	
	
	@Override
	@Transactional
    public ArtistInfo getUserArtistInfoMobile(ArtistInfo artistInfo) throws Exception {
	    
		
		ArtistInfo artis=super.getArtistDAO().getUserArtistInfoMobile(artistInfo);
		
	    return artis;
    }

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    @Transactional(readOnly = true)
    public Artist get(Long id) {
        return getArtistDAO().get(id);
    }

 

    @Override
    @Transactional
    public int update(Artist artist) {
        return getArtistDAO().update(artist);
    }

   
   

  
   
    @Override
    @Transactional
    public List<ArtistModel> getList(Integer searchmale,Long regionid,
            Integer searchage,Integer index,Integer size) {
        return getArtistDAO().getList(searchmale,regionid,searchage,index,size);
    }
    @Override
    @Transactional
    public Integer getListCount(Integer searchmale, Long regionid,
            Integer searchage) {
        return getArtistDAO().getListCount(searchmale,regionid,searchage);
        }

    @Override
    @Transactional
    public Artist getByUserid(String userid) {
        return getArtistDAO().getByUserid(userid);
    }
	@Override
	public List<Artist> findByKeywords(String keywords, String index,
			String size) {
		return getArtistDAO().findByKeywords( keywords,  index,
				 size);
	}
	@Override
	public Integer countByKeywords(String keywords) {
		return getArtistDAO().countByKeywords(keywords);
	}
	@Override
	public List<Map<String, Object>> getCitySort() {
		return getArtistDAO().getCitySort();
	}
	@Override
	public List<Map<String, Object>> findListByWorksType(String type, String age, int size,
			int index,String awards) {
		return getArtistDAO().findListByWorksType(type,age,size,index,awards);
	}
	@Override
	public Integer findCountByWorksType(String type, String age,String awards) {
		return getArtistDAO().findCountByWorksType(type,age,awards);
	}
	@Override
	public List<Map<String, Object>> findPassArtist() {
		return getArtistDAO().findPassArtist();
	}

}

