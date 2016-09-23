package com.myhome.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.entity.UserInfo;
import com.myhome.entity.vo.ReturnQuestionnaire;
import com.myhome.entity.vo.ReturnQuestionnaireState;
import com.myhome.entity.vo.ReturnUser;
import com.myhome.utils.SysConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/user/info")
public class UserInfoController extends AbstractController {

    

    /**
     * 获取当前用户信息
     * ywz
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo1(HttpServletRequest request,HttpServletResponse response) {
        ReturnUser data = new ReturnUser();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try{
        	if(request.getSession().getAttribute(SysConstants.USER_ID)==null){
        		 data.setResult(null);	
        	}else{
                Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
                UserInfo userInfo = getUserInfoService().get(Long.valueOf(userid));
//                Integer.parseInt(userInfo.getType());
                data.setResult(userInfo);	
        	}
        }catch(Exception e){
            e.printStackTrace();
            data.setSuccess(false);
        }
        return data.toJSon(data);
    }

    /**
     * 获取当前用户问卷是否已经填写
     * ywz
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/getQuestionnaireState")
    @ResponseBody
    public String getQuestionnaireState(HttpServletRequest request,HttpServletResponse response) {
        ReturnQuestionnaireState data = new ReturnQuestionnaireState();
        ReturnQuestionnaire result = new ReturnQuestionnaire();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try{

            Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
            result.setHaveToDecorate(getHaveToDecorateService().getMyBalconyByUser(Long.valueOf(userid)));
            result.setWantBuyHouse(getWantBuyHouseService().getWantBuyHouse(Long.valueOf(userid)));
            result.setWantToDecorate(getWantToDecorateService().getMobileWantToDecorate(Long.valueOf(userid)));
            result.setMyBalcony(getMyBalconyService().getMyBalcony(Long.valueOf(userid)));
            data.setResult(result);

        }catch(Exception e){
            e.printStackTrace();
            data.setSuccess(false);
        }
        return data.toJSon(data);
    }

    @RequestMapping("/get")
    public UserInfo get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/add")
    public void add(@ModelAttribute("userInfo") UserInfo userInfo) {
        // TODO 待完成
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("userInfo") UserInfo userInfo) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/remove")
    public int remove(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/delete")
    public int delete(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    

}
