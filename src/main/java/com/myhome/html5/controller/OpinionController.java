package com.myhome.html5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Opinion;
import com.myhome.entity.User;
import com.myhome.utils.JsonUtil;
import com.myhome.utils.ReturnData;

/**
 * gwb 版本控制 ClassName: VersionController <br/>
 * date: 2015年9月21日 下午7:20:02 <br/>
 */
@Controller(value = "H5versionArtistController")
@RequestMapping(value = "/H5/opinion", produces = "text/html;charset=UTF-8")
public class OpinionController extends AbstractController {

	private static Logger LOG = LoggerFactory.getLogger(OpinionController.class);

	@RequestMapping("addOpinion")
	@ResponseBody
	public String addOpinion(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		Opinion op = new Opinion();

		String content = request.getParameter("content");
		String userId = request.getParameter("userId");

		User user = new User();
		user.setId(Long.parseLong(userId));
		op.setContent(content);
		op.setUser(user);

		try {
	        super.getOpinionService().addOpinion(op);
	        returnData.setMsg("添加成功");
        } catch (Exception e) {
	        
	        e.printStackTrace();
	        returnData.setMsg("添加失败");
	        returnData.setReturnCode("1002");
            returnData.setSuccess(false);
        }
		return returnData.toString();
	}

}
