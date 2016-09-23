package com.myhome.mobile.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.utils.ReturnData;

@Controller("MobileHelpChildrenController")
@RequestMapping(value = "/mobile/helpChildren", produces = "text/html;charset=UTF-8")
public class HelpChildrenController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(SponsorsController.class);

    /**
     * 添加受帮助的小朋友信息
     * 
     * @param children
     * @return
     */
    @RequestMapping("addMobileHelpChildren")
    @ResponseBody
    public String addMobileHelpChildren(HelpChildren children,HttpServletRequest request) {
    	
        ReturnData returnData = new ReturnData();
        try {
            super.getHelpChildrenService().addMobileHelpChildren(children,request);
            returnData.setData(children.getId());
        } catch (Exception e) {
            logger.error("认证受帮助小朋友信息失败", e);
            returnData.setMsg("认证受帮助小朋友信息失败");
            returnData.setReturnCode("1001");
        }
        return returnData.toString();
    }

    /**
     * 修改受帮助的小朋友信息
     * 
     * @param children
     * @return
     */
    @SuppressWarnings("static-access")
    @RequestMapping("updateMobileHelpChildren")
    @ResponseBody
    public String updateMobileHelpChildren(HelpChildren children) {
        ReturnData returnData = new ReturnData();
        try {
            super.getHelpChildrenService().updateMobileHelpChildren(children);
            returnData.setData(children);
        } catch (Exception e) {
            logger.error("修改受帮助小朋友信息失败", e);
            returnData.setMsg("修改受帮助小朋友信息失败");
            returnData.setReturnCode("1001");
        }
        return returnData.toJSon(returnData);
    }

    public String deleteMobileHelpChildren(HelpChildren children) {
        ReturnData returnData = new ReturnData();

        return returnData.toString();
    }

    /**
     * 根据用id或者查询
     * 受帮助的小朋友id
     * 
     * @param children
     * @return
     */
    @SuppressWarnings("static-access")
    @RequestMapping("getMobileHelpChildren")
    @ResponseBody
    public String getMobileHelpChildren(HelpChildren children) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
        	HelpChildren ch =   super.getHelpChildrenService().getMobileHelpChildren(children);
        	
        	
            if(children.getUser().getUserInfo()!=null&&children.getUser().getUserInfo().getType().equals("6")){
            	ArtistInfo artistInfo = new ArtistInfo();
				artistInfo.setUser(children.getUser());
				ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);
				json.put("artist", at);
            }
            if(ch.getRegion()!=null){
            	 List<Map<String,String>> lsMap= super.getRegionService().getRegion((long) ch.getRegion().getId(), ch.getRegion().getLevel());
                 
             	json.put("region", lsMap);
            }else{
            	  json.put("region", null);
            }
            Authentication loginMobile = super.getAuthenticationService()
                    .getByUseridAndType(ch.getUser().getId(), "2");
            json.put("loginMobile", loginMobile.getLogin());
            json.put("userInfo", ch.getUser().getUserInfo());
            json.put("type", ch.getUser().getUserInfo().getType());
            ch.setUser(null);
            json.put("helpChildren", ch);
            returnData.setData(json);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setReturnCode("1001");
            returnData.setMsg("根据用户id查询受帮助者id失败");
        }
        return returnData.toJSon(returnData);
    }

    /**
     * 查询受帮助的小朋友列表
     * 
     * @param children
     * @return
     */
    @SuppressWarnings("static-access")
    @RequestMapping("getMobileHelpChildrenList")
    @ResponseBody
    public String getMobileHelpChildrenList(HelpChildren children) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        List<HelpChildren> ls = new ArrayList<HelpChildren>();
        try {
            ls = super.getHelpChildrenService().getMobileHelpChildrenList(children);
            json.put("HelpChildren", ls);
            returnData.setData(ls);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setReturnCode("1001");
        }
        return returnData.toJSon(returnData);

    }
}
    //	/**
    //	 * 查询受帮助的小朋友列表
    //	 * 
    //	 * @param children
    //	 * @return
    //	 */
    //	@SuppressWarnings("static-access")
    //	@RequestMapping("getMobileHelpChildrenList")
    //	@ResponseBody
    //    public String addMobileHelpChildrenPhotos(HelpChildren children) {
    //		ReturnData returnData = new ReturnData();
    //		JSONObject json = new JSONObject();
    //		List<HelpChildren> ls=new ArrayList<HelpChildren>();
    //		try {
    //			ls=  super.getHelpChildrenService().getMobileHelpChildrenList(children);
    //			json.put("HelpChildren", ls);
    //			returnData.setData(ls);
    //        } catch (Exception e) {
    //	        logger.error("", e);
    //	        returnData.setReturnCode("1001");
    //        }
    //		return returnData.toJSon(returnData);
    //		
    //	}

