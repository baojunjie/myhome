package com.myhome.html5.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Sponsors;
import com.myhome.utils.ReturnData;

@Controller("H5SponsorsController")
@RequestMapping(value = "H5/sponsors", produces = "text/html;charset=UTF-8")
public class SponsorsController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(SponsorsController.class);

	/**
	 * 新增赞助商信息
	 * 
	 * @param sponsors
	 * @return
	 */
	@RequestMapping("addMobileSponsors")
	@ResponseBody
	public String addMobileSponsors(Sponsors sponsors) {
		ReturnData returnData = new ReturnData();
		logger.error("新增赞助商信息开始----------------------------------------------------------------------------------");
		JSONObject json = new JSONObject();
		try {
			logger.error("新增赞助商信息第一步----------------------------------------------------------------------------------");
			super.getSponsorsService().addMobileSponsors(sponsors);
			returnData.setData(sponsors.getId());
		} catch (Exception e) {
			logger.error("新增赞助商信息失败", e);
			returnData.setReturnCode("1001");
			returnData.setMsg("新增赞助商信息失败");
		}
		return returnData.toString();
	}

	/**
	 * 修改赞助商信息
	 * 
	 * @param sponsors
	 * @return
	 */
	@RequestMapping("updateMobileSponsors")
	@ResponseBody
	public String updateMobileSponsors(Sponsors sponsors) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		try {
			super.getSponsorsService().updateMobileSponsors(sponsors);
		} catch (Exception e) {
			logger.error("修改赞助商信息失败", e);
			returnData.setReturnCode(PublicConstants.UPDATE_ERROR);
		}

		return returnData.toString();
	}

	/**
	 * 
	 * 
	 * @param sponsors
	 * @return
	 */
	@RequestMapping("deleteMobileSponsors")
	@ResponseBody
	public String deleteMobileHSponsors(Sponsors sponsors) {
		return null;

	}

	/**
	 * 根据用户id获取赞助商信息
	 * 
	 * @param sponsors
	 * @return
	 */
	@RequestMapping("getMobileSponsors")
	@ResponseBody
	public String getMobileSponsors(Sponsors sponsors) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		Sponsors sp = new Sponsors();
		try {
			sp = super.getSponsorsService().getMobileSponsors(sponsors);
			json.put("type", sp.getUser().getUserInfo().getType());
			
			json.put("region", null);
			json.put("userInfo", sp.getUser().getUserInfo());
			sp.setUser(null);
			json.put("sponsors", sp);
			
			returnData.setData(json);
		} catch (Exception e) {
			logger.error("", e);
			returnData.setReturnCode(PublicConstants.GET_ERROR);
		}
		return returnData.toJSon(returnData);
	}

	/**
	 * 获取赞助商列表
	 * 
	 * @param sponsors
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("getSponsorsList")
	@ResponseBody
	public String getSponsorsList(Sponsors sponsors) {
		List<Sponsors> ls = new ArrayList<Sponsors>();
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		try {
			ls = super.getSponsorsService().getMobileSponsorsList(sponsors);
			json.put("sponsorsList", ls);
			returnData.setData(json);
		} catch (Exception e) {
			logger.error("", e);
			returnData.setMsg("查询赞助商列表失败");
			returnData.setReturnCode("1002");
		}
		return returnData.toJSon(returnData);
	}
}
