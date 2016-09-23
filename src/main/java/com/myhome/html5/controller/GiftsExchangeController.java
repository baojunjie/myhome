package com.myhome.html5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.GiftsExchange;
import com.myhome.utils.ReturnData;

/**
 * 
 * 调查问卷 礼品兑换
 * 
 * @author gwb
 * @version $Id: GiftsExchangeController.java, v 0.1 2015年11月6日 上午9:31:16 gwb
 *          Exp $
 */
@Controller(value = "H5giftsExchangeController")
@RequestMapping(value = "/H5/giftsExchange", produces = "text/html;charset=UTF-8")
public class GiftsExchangeController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(GiftsExchangeController.class);

	@RequestMapping(value = "/addGiftsExchange")
	@ResponseBody
	public String addGiftsExchange(GiftsExchange giftsExchange, HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();

		try {
			super.getGiftsExchangeService().add(giftsExchange);
		} catch (Exception e) {
			logger.error("", e);
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
			returnData.setMsg("新增调查问卷失败");
            returnData.setSuccess(false);
		}
		return returnData.toString();

	}

	/**
	 * 获取礼物兑换列表
	 * 
	 * @param giftsExchange
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getGiftsExchange")
	@ResponseBody
	public String getGiftsExchange(GiftsExchange giftsExchange, HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();

		try {
			List<GiftsExchange> ls = super.getGiftsExchangeService().get(giftsExchange);
			json.put("giftsList", ls);
			returnData.setData(json);
			
		} catch (Exception e) {
			logger.error("", e);
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
			returnData.setMsg("新增调查问卷失败");
		}
		return returnData.toString();

	}
}
