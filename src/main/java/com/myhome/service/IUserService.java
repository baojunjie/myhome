package com.myhome.service;

import com.myhome.entity.User;
import com.myhome.entity.UserInfo;

public interface IUserService extends IService {

	public User get(Long id) throws Exception;

	public void add(User user);

	public void save(String name, String password, String mobile);

	public Long addUserMobile(User user) throws Exception;

	public void updateUserMobile(UserInfo userInfo) throws Exception;

	public User getMobileUser(User user) throws Exception;

	public void updateUserMobileInvitationCode(Long id, String serialCode) throws Exception;

}
