package com.myhome.dao;

import com.myhome.entity.User;
import com.myhome.entity.UserInfo;



public interface IUserDAO extends IDAO {

    public User get(Long id);

	public void save(String name, String password, String mobile);

	public Long addUserMobile(User user);

	public void updateUserMobile(UserInfo userInfo) throws Exception;

	public User getMobileUser(User user)  throws Exception;

}
