package com.myhome.html5.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Version;
import com.myhome.utils.ReturnData;

/**
 * gwb 版本控制 ClassName: VersionController <br/>
 * date: 2015年9月21日 下午7:20:02 <br/>
 */
@Controller("H5versionController")
@RequestMapping(value = "/H5/version", produces = "text/html;charset=UTF-8")
public class VersionController extends AbstractController {

	private static Logger LOG = LoggerFactory.getLogger(VersionController.class);

	@RequestMapping("getVersion")
	@ResponseBody
	public String getVersion(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
        response.addHeader("Access-Control-Allow-Origin", "*");
	
        try {
        	Map<String, String> version = super.getVersionService().getVersionMobile();
        	json.put("version", version);
        	returnData.setMsg("查询成功");
        } catch (Exception e) {
	        e.printStackTrace();
	        returnData.setMsg("查询失败");
	        returnData.setReturnCode("1002");
            returnData.setSuccess(false);
        }
		returnData.setData(json);
		return returnData.toString();
	}

}
