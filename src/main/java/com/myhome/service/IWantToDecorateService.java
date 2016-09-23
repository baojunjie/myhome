package com.myhome.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.WantToDecorate;
import com.myhome.entity.vo.WantToDecorateVo;

public interface IWantToDecorateService extends IService {
	/**
	 * 保存是否装修问卷信息 lqf
	 * 
	 * @param userid
	 * @param house
	 * @param request
	 * @return
	 */
	public WantToDecorate saveOrUpdate(Integer userid, WantToDecorateVo decorate, HttpServletRequest request);

	public void addMobileWantToDecorate(WantToDecorate wantToDecorate, List<String> nameList, List<String> mobileList)
	        throws Exception;

	public boolean getMobileWantToDecorate(Long id) throws Exception;

}
