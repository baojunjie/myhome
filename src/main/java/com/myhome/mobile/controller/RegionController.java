package com.myhome.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.utils.ReturnData;

@Controller(value = "MobileRegionController")
@RequestMapping(value = "/mobile/region", produces = "text/html;charset=UTF-8")
public class RegionController extends AbstractController {

	/**
	 * 一次性查询出所有的 城市查询 getRegionList
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月11日 上午10:03:34
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getRegionListMobile")
	@ResponseBody
	public String getRegionList(int id, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();

		JSONObject json = new JSONObject();
		List<Map> regionList = null;

		int level = 1;

		try {
			regionList = super.getRegionService().getRegionListMobile(level);

			for (Map re : regionList) {
				System.out.println("------------------------" + re.get("id"));
				List<Map> re2 = super.getRegionService().getRegionParentByCodeMobile(
				        Integer.parseInt(re.get("region_code") + ""));

				re.put("level1", re2);
				for (Map re3 : re2) {
					List<Map> re24 = super.getRegionService().getRegionParentByCodeMobile(
					        Integer.parseInt(re3.get("region_code") + ""));
					re3.put("level2", re24);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		json.put("regionList", regionList);
		returnData.setData(json);
		return returnData.toString();
	}

	/**
	 * test region
	 * getRegion
	 * @author gwb
	 * @param code
	 * @param level
	 * @return
	 * 2015年10月15日 下午7:11:21
	 */
	@RequestMapping("getRegion")
	@ResponseBody
	public String getRegion(Long code,Integer  level){
	try {
		List<Map<String, String>> map=	super.getRegionService(). getRegion(code,level);
    } catch (Exception e) {
	    e.printStackTrace();
    }
		return "";
	}
	
	
	
}

