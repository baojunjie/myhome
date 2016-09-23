package com.myhome.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.HaveToDecorate;
import com.myhome.entity.UserInfo;
import com.myhome.service.IHaveToDecorateService;

@Component("haveToDecorateServiceImpl")
public class HaveToDecorateServiceImpl extends AbstractServiceImpl implements IHaveToDecorateService {

	@Override
	@Transactional
	public void add(HaveToDecorate haveToDecorate) throws Exception {

		boolean flage = super.getHaveToDecorateDAO().getHaveToDecorateByUser(haveToDecorate.getUser().getId());
		if (flage) {
			return;
		}

		super.getHaveToDecorateDAO().add(haveToDecorate);

		UserInfo userInfo = new UserInfo();
		userInfo.setId(haveToDecorate.getUser().getId());
		// userInfo.setScore(10);
		if (haveToDecorate.getUser().getUserInfo() != null
		        && haveToDecorate.getUser().getUserInfo().getInvitationCode() != null) {
			userInfo.setInvitationCode(haveToDecorate.getUser().getUserInfo().getInvitationCode());
		}
		super.getUserInfoDAO().updateMobileUserScore(userInfo);
	}

	@Transactional
	@Override
	public boolean getMyBalconyByUser(Long id) throws Exception {
		return super.getHaveToDecorateDAO().getHaveToDecorateByUser(id);
	}

}
