package com.myhome.html5.controller;

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
import com.myhome.entity.BaseDataDictionaryType;
import com.myhome.utils.ReturnData;

@Controller(value = "H5BasicDataController")
@RequestMapping(value = "/H5/baseDataDictionary", produces = "text/html;charset=UTF-8")
public class BaseDataDictionaryController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(BaseDataDictionaryController.class);

	public String getBaseDataDictionary(HttpServletRequest request,HttpServletResponse response, Model model) {
		JSONObject json = new JSONObject();
        response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			// 洁具_浴缸

			// List<BaseDataDictionary> sprinkler =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1012);
			// model.addAttribute("sprinkler", sprinkler);
			// // 洁具_马桶
			// List<BaseDataDictionary> closestool =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1013);
			// model.addAttribute("closestool", closestool);
			// // 涂料
			// List<BaseDataDictionary> dope =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(11);
			// model.addAttribute("dope", dope);
			// // 开关插座
			// List<BaseDataDictionary> socket =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(12);
			// model.addAttribute("socket", socket);
			// // 灯具
			// List<BaseDataDictionary> lamp =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(13);
			// model.addAttribute("lamp", lamp);
			// // 瓷砖
			// List<BaseDataDictionary> tile =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(14);
			// model.addAttribute("tile", tile);
			// // 地板
			// List<BaseDataDictionary> floor =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(15);
			// model.addAttribute("floor", floor);
			// // 家用电器_空调
			// List<BaseDataDictionary> airCondition =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1610);
			// model.addAttribute("airCondition", airCondition);
			// // 家用电器_热水器
			// List<BaseDataDictionary> waterHeater =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1611);
			// model.addAttribute("waterHeater", waterHeater);
			// // 家用电器_浴霸
			// List<BaseDataDictionary> yuba =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1612);
			// model.addAttribute("yuba", yuba);
			// // 家用电器_冰箱
			// List<BaseDataDictionary> freezer =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1613);
			// model.addAttribute("freezer", freezer);
			// // 家用电器_净水器
			// List<BaseDataDictionary> waterCleaner =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1614);
			// model.addAttribute("waterCleaner", waterCleaner);
			// // 家用电器_油烟机
			// List<BaseDataDictionary> rangeHood =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1615);
			// model.addAttribute("rangeHood", rangeHood);
			// // 家用电器_煤气灶
			// List<BaseDataDictionary> gasCooker =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1616);
			// model.addAttribute("gasCooker", gasCooker);
			// // 家具_衣柜
			// List<BaseDataDictionary> bureau =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1710);
			// model.addAttribute("bureau", bureau);
			// // 家具_沙发
			// List<BaseDataDictionary> sofa =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1711);
			// model.addAttribute("sofa", sofa);
			// // 家具_床垫
			// List<BaseDataDictionary> mattress =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1712);
			// model.addAttribute("mattress", mattress);
			// // 家具_床
			// List<BaseDataDictionary> bed =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(1713);
			// model.addAttribute(" bed", bed);
			// // 橱柜
			// List<BaseDataDictionary> cabinet =
			// super.getBaseDataDictionaryService().getBaseDataDictionary(18);
			// model.addAttribute("cabinet", cabinet);

		} catch (Exception e) {
			logger.error("", e);
		}

		return json.toJSONString();
	}

	/**
	 * 获取企业类别
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("getMobileEnterpriseSort")
	@ResponseBody
	public String getMobileEnterpriseSort(HttpServletRequest request,HttpServletResponse response, Model model) {
        response.addHeader("Access-Control-Allow-Origin", "*");
	    ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		String type = request.getParameter("code");

		try {
			List<BaseDataDictionaryType> enterpriseSort = super.getBaseDataDictionaryTypeService().getEnterpriseSort(
			        type);
			json.put("enterpriseSort", enterpriseSort);
			returnData.setData(json);
		} catch (Exception e) {
            returnData.setSuccess(false);
			logger.error("", e);
		}
		return returnData.toString();
	}
}
