package com.myhome.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.QQAuthentication;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.InterfaceUtil;
import com.myhome.utils.JsonUtil;
import com.myhome.utils.SysConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/qqauthentication")
public class QQAuthenticationController extends AbstractController {

    

    



    @RequestMapping("/get")
    public QQAuthentication get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/add")
    public void add(@ModelAttribute("qQAuthentication") QQAuthentication qQAuthentication) {
        // TODO 待完成
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("qQAuthentication") QQAuthentication qQAuthentication) {
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
    /**
     * 申请权限
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/apply")
    public String apply() throws UnsupportedEncodingException {
    	String url = BaseCodeParam.getObject("loginUrl")+"/qqauthentication/authentication.do";
//    	url =  java.net.URLEncoder.encode(url, "utf-8");
    	String appid = "";
    	if("1".equals(BaseCodeParam.getObject("environment"))){//线上环境
    		appid= "101270489";
    	}else if("2".equals(BaseCodeParam.getObject("environment"))){//线下环境
    		appid= "101270649";
    	}
    	String path = "https://graph.qq.com/oauth/show?which=Login&display=pc&response_type=code"
    			+ "&client_id="+appid+"&redirect_uri="+url+"&state=test";
    	return "redirect:"+path;
    }
    /**
     * QQ认证
     * @param request
     * @return
     */
    @RequestMapping("/authentication")
    public String authentication(HttpServletRequest request,Model model) {
    	String url = BaseCodeParam.getObject("loginUrl")+"/qqauthentication/authentication.do";
    	String code = request.getParameter("code");
    	String appid= "";
    	String secret = "";
    	if("1".equals(BaseCodeParam.getObject("environment"))){//线上环境
    		appid= "101270489";
    		secret = "ebb24967a1a763d4a7466cd384c7601c";
    	}else if("2".equals(BaseCodeParam.getObject("environment"))){//线下环境
    		appid= "101270649";
        	secret = "820b7150c78b5543f22aafd055ae127f";
    	}
    	String GET_URL = "https://graph.qq.com/oauth2.0/token?client_id="+appid
    					+"&client_secret="+secret+"&code="+code
    					+"&grant_type=authorization_code"+"&redirect_uri="+url;
    	String getTaken_result  = InterfaceUtil.httpURLConectionGET(GET_URL);
    	if(getTaken_result!=null){
        	GET_URL = "https://graph.qq.com/oauth2.0/me?"+getTaken_result.substring(0, getTaken_result.indexOf("&"));
        	String openIdStr  = InterfaceUtil.httpURLConectionGET(GET_URL);
        	Map<String, String> useinfo_map=  JsonUtil.getMapStr(openIdStr.substring(openIdStr.indexOf("{"), openIdStr.indexOf("}")+1));
        	String openid = useinfo_map.get("openid");
        	String getnicknameUrl = "https://graph.qq.com/user/get_user_info?"
        	+getTaken_result.substring(0, getTaken_result.indexOf("&"))+"&oauth_consumer_key="+appid+"D&openid="+openid;
        	String nicknameStr  = InterfaceUtil.httpURLConectionGET(getnicknameUrl);
        	System.out.print(nicknameStr);
        	Map<String, String> nicknameStr_map=  JsonUtil.getMapStr(nicknameStr);
        	String nickname = nicknameStr_map.get("nickname");
        	String headUrl = nicknameStr_map.get("figureurl_qq_2");
        	List<QQAuthentication>  wList = getQQAuthenticationService().QueryByLogin(openid);
        	//1.判断这个openid是否存在
        	if(wList!=null&&wList.size()>0){
        		//2.1如果openid存在 判断 user是否存在（存在跳登入，不存在跳绑定号码）
        		request.getSession().setAttribute(SysConstants.USER_ID, wList.get(0).getUser().getId());
    	        request.getSession().setAttribute(SysConstants.USER_NAME, wList.get(0).getUser().getUserInfo().getName());
    	        request.getSession().setAttribute(SysConstants.USER_TYPE, wList.get(0).getUser().getUserInfo().getType());
        		return "redirect:/home/index.do";
        	 }else{
     			model.addAttribute("login", openid);
     			model.addAttribute("type", "3");
     			model.addAttribute("name", nickname);
     			model.addAttribute("url", headUrl);
     			
     			return "/app/user/boundPhone";
     			}
        	//2.2如果openid不存在保存到认证uer先为空（跳转绑定）
    	}
    	return "/app/site/500";
    }
}
