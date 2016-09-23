package com.myhome.mobile.controller;

import java.util.List;

import com.myhome.entity.Tag;
import com.myhome.utils.ReturnData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "MobileTagController")
@RequestMapping(value = "/mobile/tag", produces = "text/html;charset=UTF-8")
public class TagController extends AbstractController {

	/**
	 * 作品类别 getTagList
	 * 
	 * @author gwb
	 * @return 2015年9月11日 上午10:01:30
	 */
	@RequestMapping("/getTagList")
	@ResponseBody
	public String getTagList() {
		ReturnData returnData = new ReturnData();
		
        try {
        	List<Tag> listTag = super.getTagService().getTagList();
        	returnData.setData(listTag);
        } catch (Exception e) {
	        e.printStackTrace();
	        returnData.setMsg("获取作品类别失败");
	        returnData.setReturnCode("1002");
	        
        }
		return returnData.toString();
	}

}
