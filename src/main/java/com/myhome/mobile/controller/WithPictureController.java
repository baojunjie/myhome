package com.myhome.mobile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.WithPicture;
import com.myhome.utils.ReturnData;

/**
 * 生活照
 * 
 * @author gwb
 * @version $Id: WithPictureController.java, v 0.1 2015年10月17日 上午11:59:52 gwb Exp $
 */
@Controller("MobileWithPictureController")
@RequestMapping(value="/mobile/WithPicture",produces = "text/html;charset=UTF-8")
public class WithPictureController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(WithPictureController.class);
	
	/**
	 * 新增
	 * 
	 * @param withPicture
	 * @return
	 */
	@RequestMapping("/addMobileWithPicture")
	@ResponseBody
	public String addMobileWithPicture(WithPicture withPicture){
		 ReturnData returnData = new ReturnData();
		 JSONObject json=new JSONObject();
		try {
	        super.getWithPictureService().addMobileWithPicture(withPicture);
	        json.put("withPicture", withPicture);
	        returnData.setData(json);
        } catch (Exception e) {
	        logger.error("", e);
	        returnData.setMsg("新增WithPicture 信息失败");
	        returnData.setReturnCode(PublicConstants.ADD_ERROR);
        }
		return returnData.toJSon(returnData);
	}
	
	
	@RequestMapping("/getMobileWithPicture")
	@ResponseBody
	public String getMobileWithPicture(WithPicture withPicture){
		 ReturnData returnData = new ReturnData();
		 JSONObject json=new JSONObject();
		try {
			WithPicture wh  = super.getWithPictureService().getMobileWithPicture(withPicture);
	        json.put("withPicture", wh);
	        json.put("user", wh.getUser());
	        returnData.setData(json);
        } catch (Exception e) {
	        logger.error("", e);
	        returnData.setMsg("查询WithPicture 信息失败");
	        returnData.setReturnCode(PublicConstants.ADD_ERROR);
        }
		return returnData.toJSon(returnData);
	}
	
	
	
	@RequestMapping("/updateMobileWithPicture")
	@ResponseBody
	public String updateMobileWithPicture(WithPicture withPicture){
		 ReturnData returnData = new ReturnData();
		 JSONObject json=new JSONObject();
		try {
			WithPicture wh  = super.getWithPictureService().updateMobileWithPicture(withPicture);
	        json.put("withPicture", wh);
	        json.put("user", wh.getUser());
	        returnData.setData(json);
        } catch (Exception e) {
	        logger.error("", e);
	        returnData.setMsg("修改WithPicture 信息失败");
	        returnData.setReturnCode(PublicConstants.ADD_ERROR);
        }
		return returnData.toJSon(returnData);
	}
	
	@RequestMapping("/deleteMobileWithPicture")
	@ResponseBody
	public String deleteMobileWithPicture(WithPicture withPicture){
		 ReturnData returnData = new ReturnData();
		 JSONObject json=new JSONObject();
		try {
			 super.getWithPictureService().deleteMobileWithPicture(withPicture);
        } catch (Exception e) {
	        logger.error("", e);
	        returnData.setMsg("新增WithPicture 信息失败");
	        returnData.setReturnCode(PublicConstants.ADD_ERROR);
        }
		return returnData.toJSon(returnData);
	}
	
	
	@RequestMapping("/getMobileWithPictureList")
	@ResponseBody
	public String getMobileWithPictureList(WithPicture withPicture){
		 ReturnData returnData = new ReturnData();
		 JSONObject json=new JSONObject();
		try {
			List<WithPicture> whList  = super.getWithPictureService().getMobileWithPictureList(withPicture);
	        json.put("withPictureList", whList);
	        returnData.setData(json);
        } catch (Exception e) {
	        logger.error("", e);
	        returnData.setMsg("新增WithPicture 信息失败");
	        returnData.setReturnCode(PublicConstants.ADD_ERROR);
        }
		return returnData.toJSon(returnData);
	}
}
