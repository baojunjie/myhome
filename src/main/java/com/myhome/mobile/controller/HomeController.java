package com.myhome.mobile.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Works;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.ReturnData;

/**
 * app 首页查询 ClassName: HomeController <br/>
 * date: 2015年9月15日 下午7:30:28 <br/>
 * 
 */
@Controller("MobileHomeController")
@RequestMapping(value = "/mobile/home", produces = "text/html;charset=UTF-8")
public class HomeController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

//    @RequestMapping(value = "/index1", produces = "text/html;charset=UTF-8")
//    public String index1(Model model,HttpServletRequest request) {
//
//        return "mobileH5/views/app/user/login";
//    }

    /**
	 * 最新作品 index
	 * 
	 * @author gwb
	 * @param age
	 * @param city
	 * @param male
	 * @param pageSize
	 * @param pageIndex
	 * @param type
	 *            1最新作品 2、投票
	 * @return 2015年9月16日 上午10:16:59
	 */
	@RequestMapping(value = "/indexWorks")
	@ResponseBody
	public String indexWorks(@RequestParam("age") Integer age, @RequestParam("region_code") String region_code,
	        String male, int pageSize, int pageNo, String type, int status,	String typeRegion) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		List<Map<String, Object>> workList_topBoy=new ArrayList<Map<String,Object>>();
        try {
	        workList_topBoy = getWorksService().getWorksMobile(age, region_code, male, pageSize,
	                pageNo, type, status,typeRegion);
	    	json.put("works", workList_topBoy);
			returnData.setData(json);
        } catch (Exception e) {
        	logger.error("查询最新作品失败", e.getMessage());
	        e.printStackTrace();
	        returnData.setReturnCode("1002");
        }
		return returnData.toString();
	}

	/**
	 * 最新画家 index
	 * 
	 * @author gwb
	 * @param age
	 * @param city
	 * @param male
	 * @param pageSize
	 * @param pageIndex
	 * @param type
	 *            1最新画家 2、投票
	 * @return 2015年9月16日 上午10:16:59
	 */
	@RequestMapping(value = "/indexArtist")
	@ResponseBody
	public String indexArtist(@RequestParam("age") Integer age, @RequestParam("region_code") String region_code,
	        String male, int pageSize, int pageNo, String type, int status,String typeRegion ) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		List<Map<String, Object>> artistList_topBoy = new ArrayList<Map<String, Object>>();

		try {
			artistList_topBoy = super.getArtistService().getArtistListMobile(age, region_code, male, pageSize, pageNo,
			        type, status,typeRegion);
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage());
		}
		json.put("artists", artistList_topBoy);
		returnData.setData(json);
		return returnData.toString();
	}

}