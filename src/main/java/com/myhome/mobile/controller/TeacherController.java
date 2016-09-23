package com.myhome.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Authentication;
import com.myhome.entity.Teacher;
import com.myhome.utils.ReturnData;

/**
 * 
 * 我是老师
 * 
 * @author gwb
 * @version $Id: TeacherController.java, v 0.1 2015年10月10日 下午3:24:06 gwb Exp $
 */
@Controller("MobileTeacherController")
@RequestMapping(value = "/mobile/teacher", produces = "text/html;charset=UTF-8")
public class TeacherController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(TeacherController.class);

	/**
	 * 新增老师
	 * 
	 * @param teacher
	 */
	@RequestMapping("addMobileTeacher")
	@ResponseBody
	public String addMobileTeacher(Teacher teacher) {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		Long id;
		try {
			id = super.getTeacherService().addMobileTeacher(teacher);
			json.put("id", id);
			returnData.setData(json);
		} catch (Exception e) {
			logger.error("新增老师添加失败：", e);
			returnData.setMsg("新增老师添加失败");
			returnData.setReturnCode(PublicConstants.ADD_ERROR);
		}
		return returnData.toString();
	}

	/**
	 * 修改老师
	 * 
	 * @param teacher
	 */
	@RequestMapping("updateMobileTeacher")
	@ResponseBody
	public String updateMobileTeacher(Teacher teacher) {
		ReturnData returnData = new ReturnData();
		// JSONObject json = new JSONObject();
		try {
			super.getTeacherService().updateMobileTeacher(teacher);
		} catch (Exception e) {
			logger.error("修改老师添加失败：", e);
			returnData.setMsg("修改老师信息失败");
			returnData.setReturnCode(PublicConstants.UPDATE_ERROR);
		}

		return returnData.toString();
	}

	/**
	 * 
	 * 根据用户id查询老师 包括用户信息
	 * 
	 * @param teacher
	 */
	@RequestMapping("getMobileTeacher")
	@ResponseBody
	public String getMobileTeacher(Teacher teacher) {

		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		try {
			Teacher t = super.getTeacherService().getMobileTeacher(teacher);
			if(t!=null){
				if (t.getRegion() != null) {
					List<Map<String, String>> lsMap = super.getRegionService().getRegion((long) t.getRegion().getId(),
					                t.getRegion().getLevel());
					json.put("region", lsMap);
				} else {
					json.put("region", null);
				}
	            Authentication loginMobile = super.getAuthenticationService().getByUseridAndType(t.getUser().getId(), "2");
	            json.put("loginMobile", loginMobile.getLogin());
				json.put("userInfo", t.getUser().getUserInfo());
				json.put("type", t.getUser().getUserInfo().getType());
				t.setUser(null);
				json.put("teacher", t);
				returnData.setData(json);
			}else{
				returnData.setMsg("该老师数据不存在");
			}

		} catch (Exception e) {
			returnData.setMsg("获取老师失败");
			returnData.setReturnCode("1001");
			logger.error("获取老师失败：", e);
		}
		return returnData.toJSon(returnData);
	}

	/**
	 * 
	 * 提交申请认证
	 * 
	 * @param teacher
	 */
	@RequestMapping("updateAuthenticationCheck")
	@ResponseBody
	public String getAuthenticationCheck( Integer status,String type,HttpServletRequest request) {

		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		
		String id=request.getParameter("user.id");

		if (id != null && status != null) {
			try {
				super.getTeacherService().getAuthenticationCheck(Long.parseLong(id),status,type);
				returnData.setData(json);
			} catch (Exception e) {
				returnData.setMsg("获取老师失败");
				returnData.setReturnCode("1001");
				logger.error("获取老师失败：", e);
			}
		}else{
			returnData.setMsg("用户id");
			returnData.setReturnCode("1001");
		}
		return returnData.toJSon(returnData);
	}

}
