package com.myhome.service.impl;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Region;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.WantBuyHouse;
import com.myhome.entity.vo.WantBuyHouseVo;
import com.myhome.service.IWantBuyHouseService;

@Component("wantBuyHouseServiceImpl")
public class WantBuyHouseServiceImpl extends AbstractServiceImpl implements IWantBuyHouseService {

	@Override
	public WantBuyHouse saveOrUpdate(Integer userid, WantBuyHouseVo model, HttpServletRequest request) {
		String pattern = "yyy-MM-dd"; // 首先定义时间格式
		SimpleDateFormat format = new SimpleDateFormat(pattern);// 然后创建一个日期格式化类
		User user = new User();
		user.setId(Long.valueOf(userid));
		Region region = new Region();
		region.setId(Long.valueOf(model.getRegionid()));
		WantBuyHouse house = new WantBuyHouse();
		try {
			if (model.getId() == null) {// 保存
				house.setArea(model.getArea());
				house.setName(model.getName());
				house.setHouseType(model.getHouseType());
				house.setPurchaseTime(model.getPurchaseTime());
				house.setPurchasingBudget(model.getPurchasingBudget());
				house.setHouseForSale(model.getHouseForSale());
				house.setUser(user);
				house.setRegion(region);
				getWantBuyHouseDAO().add(house);
			} else {// 修改
				house = getWantBuyHouseDAO().get(model.getId());
				// 等待塞值
				getWantBuyHouseDAO().update(house);
			}
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public void addMobileWantBuyHouse(WantBuyHouse wantBuyHouse) throws Exception {

		boolean flage = super.getWantBuyHouseDAO().getWantBuyHouseByUser(wantBuyHouse.getUser().getId());
		if (flage) {
			return;
		}

		super.getWantBuyHouseDAO().addMobileWantBuyHouse(wantBuyHouse);

		UserInfo userInfo = new UserInfo();
		userInfo.setId(wantBuyHouse.getUser().getId());
	//	userInfo.setScore(10);
		
		if(wantBuyHouse.getUser().getUserInfo()!=null&&wantBuyHouse.getUser().getUserInfo().getInvitationCode()!=null
		        && !"".equals(wantBuyHouse.getUser().getUserInfo().getInvitationCode())){
			userInfo.setInvitationCode(wantBuyHouse.getUser().getUserInfo().getInvitationCode());
		}

		super.getUserInfoDAO().updateMobileUserScore(userInfo);
	}

	@Override
	@Transactional
	public boolean getWantBuyHouse(Long id) throws Exception {
		return super.getWantBuyHouseDAO().getWantBuyHouseByUser(id);

	}

}
