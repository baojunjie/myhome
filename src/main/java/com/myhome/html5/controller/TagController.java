package com.myhome.html5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.entity.Tag;
import com.myhome.utils.ReturnData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "H5TagController")
@RequestMapping(value = "/H5/tag", produces = "text/html;charset=UTF-8")
public class TagController extends AbstractController {

	/**
	 * 作品类别 getTagList
	 * 
	 * @author gwb
	 * @return 2015年9月11日 上午10:01:30
	 */
	@RequestMapping("/getTagList")
	@ResponseBody
	public String getTagList(HttpServletRequest request,HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
        	List<Tag> listTag = super.getTagService().getTagList();
        	returnData.setData(listTag);
        } catch (Exception e) {
	        e.printStackTrace();
	        returnData.setMsg("获取作品类别失败");
	        returnData.setReturnCode("1002");
            returnData.setSuccess(false);
        }
		return returnData.toString();
	}

}
