package com.myhome.mobile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调查问卷——奖品
 * 
 * @author gwb
 *
 */
@Controller("MobileGenerousGiftsController")
@RequestMapping(value="mobile/generousGift", produces = "text/html;charset=UTF-8")
public class GenerousGiftsController  extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(GenerousGiftsController.class);
	
//	@RequestMapping("addMobileGenerousGifts")
//	public String addMobileGenerousGifts(GenerousGifts generousGifts){
//		ReturnData returnData = new ReturnData();
//		JSONObject json = new JSONObject();
//		
//		try {
//			super.getGenerousGiftsService().addMobileGenerousGifts(generousGifts);
//		} catch (Exception e) {
//			logger.error("调查问卷--新增礼物", e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return returnData.toString();
//	}
//	
//	@RequestMapping("getMobileGenerousGifts")
//	public String getMobileGenerousGifts(GenerousGifts generousGifts){
//		ReturnData returnData = new ReturnData();
//		JSONObject json = new JSONObject();
//		
//		try {
//		List<GenerousGifts> ls=	super.getGenerousGiftsService().getMobileGenerousGifts(generousGifts);
//		} catch (Exception e) {
//			logger.error("调查问卷--新增礼物", e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return returnData.toString();
//	}
}
