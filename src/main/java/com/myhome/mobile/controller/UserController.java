package com.myhome.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Authentication;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.utils.ReturnData;
import com.myhome.utils.Tools;

@Controller(value = "MobileUserController")
@RequestMapping(value = "/mobile/user", produces = "text/html;charset=UTF-8")
public class UserController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 根据用户id获取用户信息
     * getMobileUser
     * @author gwb
     * @param user
     * @return
     * 2015年10月14日 下午4:52:15
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/getMobileUser")
    @ResponseBody
    public String getMobileUser(User user) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();

        try {
            User us = super.getUserService().getMobileUser(user);
            if(us.getUserInfo().getRegion()!=null){
            	 List<Map<String,String>> lsMap= super.getRegionService().getRegion((long)us.getUserInfo().getRegion().getRegionCode(), us.getUserInfo().getRegion().getLevel());
            	 json.put("region", lsMap);
            }else{
            	 json.put("region", null);
            }
            Authentication loginMobile = super.getAuthenticationService().getByUseridAndType(us.getId(), "2");
            json.put("loginMobile", loginMobile.getLogin());
            json.put("userInfo", us.getUserInfo());
            json.put("type", us.getUserInfo().getType());
            returnData.setData(json);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户失败", e.getMessage());
            returnData.setReturnCode(PublicConstants.GET_ERROR);
        }
        return returnData.toJSon(returnData);
    }

    /**
     * 普通用户    完善普通用户信息   包括修改和删除	
     * addUserMobiel
     * @author gwb
     * @param request
     * @param response
     * @param user
     * @return
     * 2015年10月14日 上午11:38:06
     */
    @RequestMapping("/addUserMobiel")
    @ResponseBody
    public String addUserMobiel(HttpServletRequest request, HttpServletResponse response,
                                UserInfo userInfo) {
        ReturnData returnData = new ReturnData();

        Long id = userInfo.getId();
        if (Tools.isEmpty(id + "")) {
            returnData.setMsg("用户名不能为空");
            returnData.setReturnCode(PublicConstants.USER_ID_NOT_NULL);
            return returnData.toString();
        }

        try {
            super.getUserInfoService().updateUserMobile(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("修改失败");
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
        }
        return returnData.toString();
    }
}
