package com.myhome.mobile.controller;

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
import com.myhome.entity.BaseDataDictionary;
import com.myhome.entity.UserInfo;
import com.myhome.entity.WantBuyHouse;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.ReturnData;
import com.myhome.utils.ReturnDataH5;

/**
 * 调查问卷---我想买房
 * 
 * @author gwb
 * 
 */
@Controller("MobileWantBuyHouseController")
@RequestMapping(value = "mobile/wantBuyHouse", produces = "text/html;charset=UTF-8")
public class WantBuyHouseController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(WantBuyHouseController.class);

	/**
	 * 新增调查问卷 我想买房数据
	 * 
	 * @param wantBuyHouse
	 * @param request
	 * @return
	 */
	@RequestMapping("addMobileWantBuyHouse")
	@ResponseBody
	public String addMobileWantBuyHouse(WantBuyHouse wantBuyHouse, HttpServletRequest request) {
		System.out.println(request.getParameter("user.id"));
		ReturnDataH5 returnData = new ReturnDataH5();
		JSONObject json = new JSONObject();
		try {

			boolean falg = super.getWantBuyHouseService().getWantBuyHouse(wantBuyHouse.getUser().getId());
			if (falg) {
				returnData.setReturnCode(PublicConstants.ADD_ERROR);
				returnData.setSuccess(false);
				returnData.setMsg("您已提交调该查问题");
				return returnData.toString();
			}

			super.getWantBuyHouseService().addMobileWantBuyHouse(wantBuyHouse);
			UserInfo userInfo = super.getUserInfoService().get(wantBuyHouse.getUser().getId());
			json.put("url", BaseCodeParam.getObject("service_url") + "/mobile/wantBuyHouse/byHouse.do?user.id="
			        + wantBuyHouse.getUser().getId() + "&invitationCode=" + userInfo.getInvitationCode());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调查问卷--我想买房添加失败", e.getMessage());
			returnData.setMsg("调查问卷--我想买房添加失败");
			returnData.setSuccess(false);
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
		}
		returnData.setResult(json);
		return returnData.toString();
	}

	/**
	 * 调查问卷 我想买房
	 * 
	 * @param wantBuyHouse
	 * @param model
	 * @return
	 */
	@RequestMapping("byHouse")
	public String byHouse(WantBuyHouse wantBuyHouse, Model model) {

		List<BaseDataDictionary> baseDataDictionaryList = null;
		
		try {
            baseDataDictionaryList = super.getBaseDataDictionaryService().getBaseDataDictionary("41");
            model.addAttribute("baseDataDictionaryList", baseDataDictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
		model.addAttribute("invitationCode", wantBuyHouse.getUser().getUserInfo().getInvitationCode());
		model.addAttribute("id", wantBuyHouse.getUser().getId());
		// model.addAttribute("id", "140");
		return "/app/questionnaire/buyHouse";
	}
	
	/**
     * 调查问卷 我想买房
     * 
     * @param wantBuyHouse
     * @param model
     * @return
     */
    @RequestMapping("byHouseYesOrNo")
    @ResponseBody
    public String byHouseYesOrNo(WantBuyHouse wantBuyHouse, Model model) {

        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            boolean falg = super.getWantBuyHouseService().getWantBuyHouse(wantBuyHouse.getUser().getId());
            model.addAttribute("yesOrNo",falg);
            json.put("yesOrNo", falg);
            returnData.setData(json);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
        }
        return returnData.toString();
    }

}
