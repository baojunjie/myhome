package com.myhome.mobile.controller;

import java.util.List;

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
import com.myhome.entity.BaseDataDictionary;
import com.myhome.entity.HaveToDecorate;
import com.myhome.entity.MyBalcony;
import com.myhome.entity.UserInfo;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.ReturnData;
import com.myhome.utils.ReturnDataH5;

/**
 * 调查问卷 我已装修
 * 
 * @author gwb
 * @version $Id: HaveToDecorateController.java, v 0.1 2015年11月6日 上午9:31:37 gwb
 *          Exp $
 */
@Controller(value = "haveToDecorateController")
@RequestMapping(value = "/mobile/haveToDecorate", produces = "text/html;charset=UTF-8")
public class HaveToDecorateController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(HaveToDecorateController.class);

	@RequestMapping(value = "/addHaveToDecorate")
	@ResponseBody
	public String addHaveToDecorate(HaveToDecorate haveToDecorate, HttpServletRequest request) {
		ReturnDataH5 returnData = new ReturnDataH5();
		JSONObject json = new JSONObject();
		try {

			boolean falg = super.getHaveToDecorateService().getMyBalconyByUser(haveToDecorate.getUser().getId());

			if (falg) {
				returnData.setReturnCode(PublicConstants.ADD_ERROR);
				returnData.setSuccess(false);
				returnData.setMsg("您已提交调该查问题");
				return returnData.toString();
			}

			super.getHaveToDecorateService().add(haveToDecorate);
			UserInfo userInfo = super.getUserInfoService().get(haveToDecorate.getUser().getId());
			json.put("url", BaseCodeParam.getObject("service_url")
			        + "/mobile/haveToDecorate/haveToDecorate.do?user.id=" + haveToDecorate.getUser().getId()
			        + "&invitationCode=" + userInfo.getInvitationCode());
		} catch (Exception e) {
			logger.error("", e);
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
			returnData.setSuccess(false);
			returnData.setMsg("新增调查问卷失败");
		}
		returnData.setResult(json);
		return returnData.toString();

	}

	/**
	 * 我已装修 view
	 * 
	 * @param wantBuyHouse
	 * @param model
	 * @return
	 */
	@RequestMapping("haveToDecorate")
	public String byHouse(HaveToDecorate haveToDecorate, Model model, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		// List<BaseDataDictionary> baseDataDictionaryList = null;
		try {
			// baseDataDictionaryList =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(
			// "41");
			// 浴缸

			List<BaseDataDictionary> craftsmanType = super.getBaseDataDictionaryService().getBaseDataDictionary("42");
			model.addAttribute("craftsmanTypeList", craftsmanType);

			List<BaseDataDictionary> bathtub = super.getBaseDataDictionaryService().getBaseDataDictionary("1010");
			model.addAttribute("bathtubList", bathtub);
			// 洁具_脸盆 水槽
			List<BaseDataDictionary> waterChannel = super.getBaseDataDictionaryService().getBaseDataDictionary("1011");
			model.addAttribute("waterChannelList", waterChannel);

			// 花洒
			List<BaseDataDictionary> sprinkler = super.getBaseDataDictionaryService().getBaseDataDictionary("1012");
			model.addAttribute("sprinklerList", sprinkler);
			// 洁具_马桶
			List<BaseDataDictionary> closestool = super.getBaseDataDictionaryService().getBaseDataDictionary("1013");
			model.addAttribute("closestoolList", closestool);
			// 涂料
			List<BaseDataDictionary> dope = super.getBaseDataDictionaryService().getBaseDataDictionary("11");
			model.addAttribute("dopeList", dope);
			// 开关插座
			List<BaseDataDictionary> socket = super.getBaseDataDictionaryService().getBaseDataDictionary("12");
			model.addAttribute("socketList", socket);
			// 灯具
			List<BaseDataDictionary> lamp = super.getBaseDataDictionaryService().getBaseDataDictionary("13");
			model.addAttribute("lampList", lamp);
			// 瓷砖
			List<BaseDataDictionary> tile = super.getBaseDataDictionaryService().getBaseDataDictionary("14");
			model.addAttribute("tileList", tile);
			// 地板
			List<BaseDataDictionary> floor = super.getBaseDataDictionaryService().getBaseDataDictionary("15");
			model.addAttribute("floorList", floor);
			// 家用电器_空调
			List<BaseDataDictionary> airCondition = super.getBaseDataDictionaryService().getBaseDataDictionary("1610");
			model.addAttribute("airConditionList", airCondition);
			// 家用电器_热水器
			List<BaseDataDictionary> waterHeater = super.getBaseDataDictionaryService().getBaseDataDictionary("1611");
			model.addAttribute("waterHeaterList", waterHeater);
			// 家用电器_浴霸
			List<BaseDataDictionary> yuba = super.getBaseDataDictionaryService().getBaseDataDictionary("1612");
			model.addAttribute("yubaList", yuba);
			// 家用电器_冰箱
			List<BaseDataDictionary> freezer = super.getBaseDataDictionaryService().getBaseDataDictionary("1613");
			model.addAttribute("freezerList", freezer);
			// 家用电器_净水器
			List<BaseDataDictionary> waterCleaner = super.getBaseDataDictionaryService().getBaseDataDictionary("1614");
			model.addAttribute("waterCleanerList", waterCleaner);
			// 家用电器_油烟机
			List<BaseDataDictionary> rangeHood = super.getBaseDataDictionaryService().getBaseDataDictionary("1615");
			model.addAttribute("rangeHoodList", rangeHood);
			// 家用电器_煤气灶
			List<BaseDataDictionary> gasCooker = super.getBaseDataDictionaryService().getBaseDataDictionary("1616");
			model.addAttribute("gasCookerList", gasCooker);
			// 家具_衣柜
			List<BaseDataDictionary> bureau = super.getBaseDataDictionaryService().getBaseDataDictionary("1710");
			model.addAttribute("bureauList", bureau);
			// 家具_沙发
			List<BaseDataDictionary> sofa = super.getBaseDataDictionaryService().getBaseDataDictionary("1711");
			model.addAttribute("sofaList", sofa);
			// 家具_床垫
			List<BaseDataDictionary> mattress = super.getBaseDataDictionaryService().getBaseDataDictionary("1712");
			model.addAttribute("mattressList", mattress);
			// 家具_床
			List<BaseDataDictionary> bed = super.getBaseDataDictionaryService().getBaseDataDictionary("1713");
			model.addAttribute("bedList", bed);
			// 橱柜
			List<BaseDataDictionary> cabinet = super.getBaseDataDictionaryService().getBaseDataDictionary("18");
			model.addAttribute("cabinetList", cabinet);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("id", haveToDecorate.getUser().getId());
		model.addAttribute("invitationCode", haveToDecorate.getUser().getUserInfo().getInvitationCode());
		// model.addAttribute("id", 140);
		// model.addAttribute("id",wantBuyHouse.getUser().getId());
		// model.addAttribute("id", "140");
		return "/app/questionnaire/renovated";
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
    @RequestMapping("haveToDecorateYesOrNo")
    @ResponseBody
    public String haveToDecorateYesOrNo(HaveToDecorate haveToDecorate, HttpServletRequest request, HttpServletResponse response, Model model) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            boolean falg = super.getHaveToDecorateService().getMyBalconyByUser(haveToDecorate.getUser().getId());
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

	
