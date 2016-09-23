package com.myhome.dao;

import java.util.List;

import com.myhome.entity.HelpChildren;

public interface IHelpChildrenDao extends IDAO{

	public Long addMobileHelpChildren(HelpChildren children) throws Exception;
	
	public void updateMobileHelpChildren(HelpChildren children) throws Exception;
	
	public void deleteMobileHelpChildren(HelpChildren children) throws Exception;
	
	public HelpChildren getMobileHelpChildren(HelpChildren children) throws Exception;
	
	public List<HelpChildren> getMobileHelpChildrenList(HelpChildren children) throws Exception;

    public HelpChildren get(Long id);
    /**
     * 更具userid获取休息
     * lqf
     * @param userid
     * @return
     */
    public HelpChildren getByUserid(Long userid);
}
