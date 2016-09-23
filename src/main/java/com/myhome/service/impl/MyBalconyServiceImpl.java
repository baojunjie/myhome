package com.myhome.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.MyBalcony;
import com.myhome.entity.UserInfo;
import com.myhome.service.IMyBalconyService;
import com.myhome.utils.BaseCodeParam;

@Component("myBalconyServiceImpl")
public class MyBalconyServiceImpl extends AbstractServiceImpl implements IMyBalconyService {

	@Override
	@Transactional
	public void add(MyBalcony myBalcony) throws Exception {

		String img = myBalcony.getBalconyImg();
		myBalcony.setBalconyImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/myBalcony/" + img);
		myBalcony.setBalconyImgThum(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/myBalcony/thumb_" + img);
		boolean flage = super.getMyBalconyDAO().getMyBalconyByUser(myBalcony.getUser().getId());
		if (flage) {
			return;
		}

		super.getMyBalconyDAO().add(myBalcony);
		UserInfo userInfo = new UserInfo();
		userInfo.setId(myBalcony.getUser().getId());
	//	userInfo.setScore(10);
		if(myBalcony.getUser().getUserInfo()!=null&&myBalcony.getUser().getUserInfo().getInvitationCode()!=null
		        && !"".equals(myBalcony.getUser().getUserInfo().getInvitationCode())){
			userInfo.setInvitationCode(myBalcony.getUser().getUserInfo().getInvitationCode());
		}
		super.getUserInfoDAO().updateMobileUserScore(userInfo);
	}

	@Override
	@Transactional
	public boolean getMyBalcony(Long id) throws Exception {
		return super.getMyBalconyDAO().getMyBalconyByUser(id);
	}

}
