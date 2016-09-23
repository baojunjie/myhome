package com.myhome.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.Sponsors;
import com.myhome.entity.vo.SponsorsVo;

public interface ISponsorsService extends IService{
	
	public void updateMobileSponsors(Sponsors children) throws Exception;
	
	public void deleteMobileHSponsors(Sponsors children) throws Exception;
	
	public Sponsors getMobileSponsors(Sponsors children) throws Exception;
	
	public List<Sponsors> getMobileSponsorsList(Sponsors children) throws Exception;

	public Sponsors addMobileSponsors(Sponsors sponsors) throws Exception;
	/**
	 * 保存或更新
	 * lqf
	 * @param userid
	 * @param model
	 * @return
	 */
    public Sponsors saveOrUpdate(Integer userid, SponsorsVo model,HttpServletRequest request);
    /**
     * 获取信息
     * lqf
     * @param valueOf
     * @return
     */
    public SponsorsVo getWebinfo(Long valueOf);
    /**
     * 根据id获取赞助商信息
     * @param valueOf
     * @return
     */
    public Sponsors get(Long id);
    /**
     * 根据userid 获取赞助商信息
     * @param valueOf
     * @return
     */
    public Sponsors getByUserid(Long suerid);
	
	
	
}
