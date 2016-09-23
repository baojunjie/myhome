package com.myhome.mobile.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.UserInfo;
import com.myhome.entity.WantToDecorate;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.ReturnData;
import com.myhome.utils.ReturnDataH5;

/**
 * 调查问卷 ---
 * 
 * @author gwb
 * 
 */
@Controller("MobileWantToDecorateController")
@RequestMapping(value = "mobile/wantToDecorate", produces = "text/html;charset=UTF-8")
public class WantToDecorateController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(WantToDecorateController.class);

	@RequestMapping("addMobileWantToDecorate")
	@ResponseBody
	public String addMobileWantToDecorate(WantToDecorate wantToDecorate, HttpServletRequest request) {
		ReturnDataH5 returnData = new ReturnDataH5();
		JSONObject json = new JSONObject();
		String[] listName = request.getParameterValues("list.name");
		List<String> nameList = new ArrayList<String>();
		if (listName != null) {
			nameList = Arrays.asList(listName);
		}
		String[] listMobile = request.getParameterValues("list.mobile");
		List<String> mobileList = new ArrayList<String>();
		if (listMobile != null) {
			mobileList = Arrays.asList(listMobile);
		}
		try {

			boolean falg = super.getWantToDecorateService().getMobileWantToDecorate(wantToDecorate.getUser().getId());
			if (falg) {
				returnData.setReturnCode(PublicConstants.ADD_ERROR);
				returnData.setSuccess(false);
				returnData.setMsg("您已提交调该查问题");
				return returnData.toString();
			}

			super.getWantToDecorateService().addMobileWantToDecorate(wantToDecorate, nameList, mobileList);
			UserInfo userInfo = super.getUserInfoService().get(wantToDecorate.getUser().getId());
			json.put("url", BaseCodeParam.getObject("service_url")
			        + "/mobile/wantToDecorate/wantToDecorate.do?user.id=" + wantToDecorate.getUser().getId()
			        + "&invitationCode=" + userInfo.getInvitationCode());
			// super.getGenerousGiftsService().addMobileGenerousGiftsList(list);

		} catch (Exception e) {
			logger.error("调查问卷--我想装修添加失败", e.getMessage());
			returnData.setMsg("调查问卷--我想装修添加失败");
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
			returnData.setSuccess(false);
			e.printStackTrace();
		}
		returnData.setResult(json);
		return returnData.toString();
	}

	@RequestMapping("wantToDecorate")
	public String byHouse(WantToDecorate wantBuyHouse, Model mode) {
		mode.addAttribute("id", wantBuyHouse.getUser().getId());
		mode.addAttribute("invitationCode", wantBuyHouse.getUser().getUserInfo().getInvitationCode());
		// mode.addAttribute("id", 140);
		return "/app/questionnaire/wantRenovate";
	}
	

    @RequestMapping("wantToDecorateYesOrNo")
    @ResponseBody
    public String wantToDecorateYesOrNo(WantToDecorate wantBuyHouse, Model mode) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        // mode.addAttribute("id", 140);
        try {
            boolean falg = super.getWantToDecorateService().getMobileWantToDecorate(wantBuyHouse.getUser().getId());
            json.put("yesOrNo", falg);
            returnData.setData(json);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
        }
        return returnData.toString();
    }

}
