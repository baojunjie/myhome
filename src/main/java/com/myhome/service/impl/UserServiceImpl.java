package com.myhome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IUserDAO;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.service.IUserService;

@Component("userServiceImpl")
public class UserServiceImpl extends AbstractServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public User get(Long id) {
		return getUserDAO().get(id);
	}

	@Override
	@Transactional
	public void add(User user) {
		userDAO.add(user);
	}

	@Transactional
	public int update(User user) {
		// TODO 待完成
		throw new RuntimeException();
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

	@Override
	public void save(String name, String password, String mobile) {
		userDAO.save(name, password, mobile);

	}

	@Override
	public Long addUserMobile(User user) {

		return super.getUserDAO().addUserMobile(user);
	}

	@Override
	@Transactional
	public void updateUserMobile(UserInfo userInfo) throws Exception {
		super.getUserDAO().updateUserMobile(userInfo);
	}

	@Override
	@Transactional(readOnly = true)
	public User getMobileUser(User user) throws Exception {
		User us = super.getUserDAO().getMobileUser(user);
		return us;
	}

	@Override
	@Transactional
	public void updateUserMobileInvitationCode(Long id, String serialCode) throws Exception {
		super.getUserInfoDAO().updateUserMobileInvitationCode(id, serialCode);
	}

}
