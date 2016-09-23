package com.myhome.dao;

import java.util.List;

import com.myhome.entity.UserInfo;

public interface IUserInfoDAO extends IDAO {

	public UserInfo get(Long id);

	public List<UserInfo> findByMobile(String mobile);

	public void save(UserInfo userinfo);

	public List<UserInfo> findByMobileAndPassword(String mobile, String password);

	/**
	 * 更具姓名找用户 lqf
	 * 
	 * @param usename
	 * @return
	 */
	public List<UserInfo> findByName(String usename);

	public void updateUserMobile(UserInfo userInfo) throws Exception;

	/**
	 * 填写调查问卷获取积分
	 * 
	 * @param userInfo
	 * @throws Exception
	 */
	public void updateMobileUserScore(UserInfo userInfo) throws Exception;

	public void updateUserMobileInvitationCode(Long id, String serialCode) throws Exception;

}
