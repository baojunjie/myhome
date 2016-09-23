package com.myhome.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.HelpChildren;
import com.myhome.entity.vo.HelpChildrenVo;

public interface IHelpChildrenService extends IService{

public Long addMobileHelpChildren(HelpChildren children,HttpServletRequest request) throws Exception;

	public void updateMobileHelpChildren(HelpChildren children) throws Exception;
	
	public void deleteMobileHelpChildren(HelpChildren children) throws Exception;
	
	public HelpChildren getMobileHelpChildren(HelpChildren children) throws Exception;
	
	public List<HelpChildren> getMobileHelpChildrenList(HelpChildren children) throws Exception;
	
    /**
     * 保存信息
     * lqf
     * @param valueOf
     * @return
     */
    public HelpChildren saveOrUpdate(Integer userid, HelpChildrenVo model,HttpServletRequest request);

	
    public HelpChildrenVo getWebinfo(Long id);
    /**
     * id获取幸运小朋友
     * lqf
     * @param id
     * @return
     */
    public HelpChildren get(Long id);
    /**
     * userid获取幸运小朋友信息
     * lqf
     * @param userid
     * @return
     */
    public HelpChildren getByUserid(Long userid);
}
