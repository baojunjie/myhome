package com.myhome.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.GenerousGifts;
import com.myhome.entity.Region;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.WantToDecorate;
import com.myhome.entity.vo.WantToDecorateVo;
import com.myhome.service.IWantToDecorateService;

@Component("wantToDecorateServiceImpl")
public class WantToDecorateServiceImpl extends AbstractServiceImpl implements IWantToDecorateService {

	@Override
	@Transactional
	public WantToDecorate saveOrUpdate(Integer userid, WantToDecorateVo model, HttpServletRequest request) {
		String pattern = "yyy-MM-dd"; // 首先定义时间格式
		SimpleDateFormat format = new SimpleDateFormat(pattern);// 然后创建一个日期格式化类
		User user = new User();
		user.setId(Long.valueOf(userid));
		Region region = new Region();
		region.setId(Long.valueOf(model.getRegionid()));
		WantToDecorate decorate = new WantToDecorate();
		try {
			if (model.getId() == null) {// 保存
				decorate.setName(model.getName());// 楼盘名称
				decorate.setArea(model.getArea());// 面积
				decorate.setBudget(model.getBudget());// 装修预算
				decorate.setWish(model.getWish());// 对未来家的想象和愿望
				decorate.setAirbnb(model.getAirbnb());// 加入短租airbnb:1是，0否
				decorate.setHouseForSale(model.getHouseForSale());// 房屋出售1是，0否
				decorate.setUnitPrice(model.getUnitPrice());// 单间
				decorate.setDate(model.getDate());// 购房时间
				decorate.setUser(user);
				decorate.setRegion(region);
				getWantToDecorateDAO().add(decorate);
				// 保存推荐人的信息（看他传什么值）
			} else {// 修改
				decorate = getWantToDecorateDAO().get(model.getId());
				// 等待塞值
				getWantToDecorateDAO().update(decorate);
			}
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public void addMobileWantToDecorate(WantToDecorate wantToDecorate, List<String> nameList, List<String> mobileList)
	        throws Exception {

		boolean flage = super.getWantToDecorateDAO().getWantToDecorateByUser(wantToDecorate.getUser().getId());
		if (flage) {
			return;
		}

		super.getWantToDecorateDAO().addMobileWantToDecorate(wantToDecorate);
		UserInfo userInfo = new UserInfo();
		userInfo.setId(wantToDecorate.getUser().getId());

		if (wantToDecorate.getUser().getUserInfo() != null
		                && wantToDecorate.getUser().getUserInfo().getInvitationCode() != null
		                && !"".equals(wantToDecorate.getUser().getUserInfo().getInvitationCode())) {
			userInfo.setInvitationCode(wantToDecorate.getUser().getUserInfo().getInvitationCode());
		}
		super.getUserInfoDAO().updateMobileUserScore(userInfo);

		// 填写推荐人信息
		for (int i = 0; i < nameList.size(); i++) {
			GenerousGifts generousGifts = new GenerousGifts();
			generousGifts.setUser(wantToDecorate.getUser());
			generousGifts.setName(nameList.get(i));
			generousGifts.setMobile(mobileList.get(i));
			super.getGenerousGiftsDAO().addMobileGenerousGifts(generousGifts);
		}
	}

	@Override
	@Transactional
	public boolean getMobileWantToDecorate(Long id) throws Exception {
		return super.getWantToDecorateDAO().getWantToDecorateByUser(id);

	}

}
