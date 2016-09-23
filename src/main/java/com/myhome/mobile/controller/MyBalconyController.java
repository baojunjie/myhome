package com.myhome.mobile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.MyBalcony;
import com.myhome.entity.UserInfo;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.ReturnData;
import com.myhome.utils.ReturnDataH5;

/**
 * 调查问卷 我的阳台
 * 
 * @author gwb
 */
@Controller(value = "myBalconyController")
@RequestMapping(value = "/mobile/myBalcony", produces = "text/html;charset=UTF-8")
public class MyBalconyController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(MyBalconyController.class);

	@RequestMapping(value = "/addMyBalcony")
	@ResponseBody
	public String addMyBalcony(MyBalcony myBalcony, HttpServletRequest request) {
		ReturnDataH5 returnData = new ReturnDataH5();
		// ResultData data = new ResultData();
		JSONObject json = new JSONObject();
		try {

			boolean falg = super.getMyBalconyService().getMyBalcony(myBalcony.getUser().getId());
			if (falg) {
				returnData.setReturnCode(PublicConstants.ADD_ERROR);
				returnData.setSuccess(false);
				returnData.setMsg("您已提交调该查问题");
				return returnData.toString();
			}

			super.getMyBalconyService().add(myBalcony);
			if (myBalcony.getUser() != null) {
				UserInfo userInfo = super.getUserInfoService().get(myBalcony.getUser().getId());
				if (userInfo != null) {
					json.put("url", BaseCodeParam.getObject("service_url") + "/mobile/myBalcony/myBalcony.do?user.id="
					        + myBalcony.getUser().getId() + "&invitationCode=" + userInfo.getInvitationCode());
				}
			}
			// data.setSuccess(true);

		} catch (Exception e) {
			logger.error("", e);
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
			returnData.setMsg("新增调查问卷失败");
			returnData.setSuccess(false);
		}
		returnData.setResult(json);
		return returnData.toString();
	}

	@RequestMapping("myBalcony")
	public String byHouse(MyBalcony wantBuyHouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		model.addAttribute("id", wantBuyHouse.getUser().getId());
		model.addAttribute("invitationCode", wantBuyHouse.getUser().getUserInfo().getInvitationCode());
		// model.addAttribute("id", "140");
		return "/app/questionnaire/mybalcony";
	}

	
	/**
     * 是否已填写跳槽问卷
     * 
     * @param wantBuyHouse
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("myBalconyYesOrNo")
    @ResponseBody
    public String myBalconyYesOrNo(MyBalcony wantBuyHouse, HttpServletRequest request, HttpServletResponse response, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            boolean falg = super.getMyBalconyService().getMyBalcony(wantBuyHouse.getUser().getId());
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
