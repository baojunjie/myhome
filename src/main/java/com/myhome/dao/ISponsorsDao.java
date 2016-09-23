package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Sponsors;

public interface ISponsorsDao extends IDAO{

	public Sponsors addMobileSponsors(Sponsors children) throws Exception;
	
	public void updateMobileSponsors(Sponsors children) throws Exception;
	
	public void deleteMobileHSponsors(Sponsors children) throws Exception;
	
	public Sponsors getMobileSponsors(Sponsors children) throws Exception;
	
	public List<Sponsors> getMobileSponsorsList(Sponsors children) throws Exception;

    public Sponsors get(Long id);
    /**
     * 根据userid获取信息
     * @param userid
     * @return
     */
    public Sponsors getByUserid(Long userid);
	
}
