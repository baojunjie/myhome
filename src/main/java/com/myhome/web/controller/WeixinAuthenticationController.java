package com.myhome.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.entity.Authentication;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.QQAuthentication;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.VerificationCode;
import com.myhome.entity.WeiboAuthentication;
import com.myhome.entity.WeixinAuthentication;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.InterfaceUtil;
import com.myhome.utils.JsonUtil;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ResultData;
import com.myhome.utils.SysConstants;
import com.myhome.utils.UtilMD5;
import com.myhome.utils.UtilMD5Encoder;



@Controller
@RequestMapping("/weixin/authentication")
public class WeixinAuthenticationController extends AbstractController {

    

    



    @RequestMapping("/get")
    public WeixinAuthentication get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("weixinAuthentication") WeixinAuthentication weixinAuthentication) {
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
    	String url = BaseCodeParam.getObject("loginUrl")+"/weixin/authentication/authentication.do";
    	url =  java.net.URLEncoder.encode(url, "utf-8");
    	String appid = "wx4097fe276e62a6c1";
    	String path = "https://open.weixin.qq.com/connect/qrconnect?appid="+appid+"&redirect_uri="+url+"&scope=snsapi_login#wechat_redirect";
    	return "redirect:"+path;
    }
    /**
     * 微信认证
     * @param request
     * @return
     */
    @RequestMapping("/authentication")
    public String authentication(HttpServletRequest request,Model model) {
    	String code = request.getParameter("code");
//    	String code ="0012bcbf3520ad61b5ea01789adaaa2-";
    	String appid= "wx4097fe276e62a6c1";
    	String secret = "09e232f0c718a8965a2e6e111a9f6a82";
    	String GET_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid
    					+"&secret="+secret
    					+"&code="+code
    					+"&grant_type=authorization_code";
    	String getTaken_result  = InterfaceUtil.httpURLConectionGET(GET_URL);
    	if(getTaken_result!=null){
    		Map<String, String> map=  JsonUtil.getMapStr(getTaken_result);
//        	String access_token = map.get("access_token");
        	String openid = map.get("unionid");
//        	GET_URL = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
//        			  +"&openid="+openid;
//        	String useinfo  = InterfaceUtil.httpURLConectionGET(GET_URL);
//        	Map<String, String> useinfo_map=  JsonUtil.getMapStr(getTaken_result);
        	
        	List<WeixinAuthentication>  wList = getWeixinAuthenticationService().QueryByLogin(openid);
        	//1.判断这个openid是否存在
        	if(wList!=null&&wList.size()>0){
        		//2.1如果openid存在 判断 user是否存在（存在跳登入，不存在跳绑定号码）
        				request.getSession().setAttribute(SysConstants.USER_ID, wList.get(0).getUser().getId());
    	                request.getSession().setAttribute(SysConstants.USER_NAME, wList.get(0).getUser().getUserInfo().getName());
    	                request.getSession().setAttribute(SysConstants.USER_TYPE, wList.get(0).getUser().getUserInfo().getType());
        				return "redirect:/home/index.do";
        	 }else{
     			model.addAttribute("login", openid);
     			model.addAttribute("type", "5");
     			return "/app/user/boundPhone";
     			}
        	//2.2如果openid不存在保存到认证uer先为空（跳转绑定）
    	}
    	return "/app/site/500";
    }
    /**
     * 绑定（1.短信验证号码 2.号码账户是否存在（存在绑定，不存在跳转到注册页面）3.号码的user加到openid的user（必须进行openid都要传））
     * @param request
     * @return
     */
    @RequestMapping("/bingding")
    public String bingding(HttpServletRequest request,Model model){
    	try{
    	String type = request.getParameter("type");
    	String mobile = request.getParameter("mobile");
    	String name = request.getParameter("name");
    	String url = request.getParameter("url");
    	String login = request.getParameter("login");//openid/uid
    	String code = request.getParameter("code");//openid
    	Date createdDatetime = new Date(new Date().getTime() - 1800000);
		List<VerificationCode> verificationCodelist = getVerificationCodeService()
					.findByMobile(mobile, code);
		//后台再次判断验证码
    	if(CommonUtils.isNotEmpty(verificationCodelist)&&(createdDatetime.before(verificationCodelist.get(0).getCreatedDatetime()))){
    		// 一判断该手机是否已经注册
        	List<Authentication> authenticationlist = getAuthenticationService()
        			.findByLogin(mobile);
        	if(CommonUtils.isNotEmpty(authenticationlist)){
        	    // qq
        	    if("3".equals(type)){
                    QQAuthentication  qq =  new QQAuthentication();
                    qq.setLogin(login);
                    qq.setUser(authenticationlist.get(0).getUser());
                    getQQAuthenticationService().add(qq);
                    request.getSession().setAttribute(SysConstants.USER_ID, qq.getUser().getId());
                    request.getSession().setAttribute(SysConstants.USER_NAME, qq.getUser().getUserInfo().getName());
                    request.getSession().setAttribute(SysConstants.USER_TYPE, qq.getUser().getUserInfo().getType());
        	    }
//        	     微博
        	    if("4".equals(type)){
        	        WeiboAuthentication  weibo = new WeiboAuthentication();
        	        weibo.setLogin(login);
        	        weibo.setUser(authenticationlist.get(0).getUser());
        	        super.getAuthenticationService().addMobileAuthentication(weibo);
                    request.getSession().setAttribute(SysConstants.USER_ID, authenticationlist.get(0).getUser().getId());
                    request.getSession().setAttribute(SysConstants.USER_NAME, authenticationlist.get(0).getUser().getUserInfo().getName());
                    request.getSession().setAttribute(SysConstants.USER_TYPE, authenticationlist.get(0).getUser().getUserInfo().getType());
        	    }
        	    // 微信
        	    if("5".equals(type)){
                    WeixinAuthentication  weixin =  new WeixinAuthentication();
                    weixin.setLogin(login);
                    weixin.setUser(authenticationlist.get(0).getUser());
                    getWeixinAuthenticationService().add(weixin);
                    request.getSession().setAttribute(SysConstants.USER_ID, weixin.getUser().getId());
                    request.getSession().setAttribute(SysConstants.USER_NAME, weixin.getUser().getUserInfo().getName());
                    request.getSession().setAttribute(SysConstants.USER_TYPE, weixin.getUser().getUserInfo().getType());
        	    }
                return "redirect:/home/index.do";
        	}else{

                model.addAttribute("login", login);
                model.addAttribute("mobile", mobile);
                model.addAttribute("type", type);
                return "/app/user/boundAccount";
        	}
    	}else{
                model.addAttribute("result","验证码有误");
	            model.addAttribute("mobile",mobile);
	            model.addAttribute("type",type);
	            if("3".equals(type)){
	            	 model.addAttribute("name",name);
	            	 model.addAttribute("url",url);
	            }
	            return "/app/user/boundPhone";
    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		return "/app/site/500";
    	}
    }
    /**
     * 注册（1.短信验证号码 2.号码账户是否存在（存在绑定，不存在跳转到注册页面）3.号码的user加到openid的user（必须进行openid都要传））
     * @param request
     * @return
     */
    @RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response) {
		ResultData data = new ResultData();
		try {
			 String login = request.getParameter("login");//openid/uid
			 String type = request.getParameter("type");//openid
			 String name = request.getParameter("name").trim();//姓名
			 String password = request.getParameter("password");//密码
			 password= UtilMD5.getMD5ofStr(password).toLowerCase();
			 String mobile = request.getParameter("mobile").trim();//手机
//			 String code = request.getParameter("code").trim();//验证码
//			 Date createdDatetime = new Date(new Date().getTime() - 1800000);
//			 List<VerificationCode> verificationCodelist = getVerificationCodeService()
//						.findByMobile(mobile, code);
			 List<Authentication> authenticationlist = getAuthenticationService().findByLogin(mobile);
//			 if(name!=""&&mobile!=""&&code!=""){
			 List<UserInfo>  list  =  getUserInfoService().findByName(name);
	            if (CommonUtils.isEmpty(authenticationlist)&&CommonUtils.isEmpty(list)
//	            		&&CommonUtils.isNotEmpty(verificationCodelist)
//	            		&&(createdDatetime.before(verificationCodelist.get(0).getCreatedDatetime()))
	            		) {
                    String salt = RandomDataUtil.SixRadom();
                    User user = new User();
                    UserInfo userinfo = new UserInfo();
                    userinfo.setName(name);
                    userinfo.setType("1");
                    user.setUserInfo(userinfo);
                    userinfo.setUser(user);
                    userinfo.setRegion(null);
                    getUserService().add(user);// 注册成功后吧信息写入session
                    //保存手机认证
                    MobileAuthentication mobileAuthentication = new MobileAuthentication();
                    mobileAuthentication.setUser(user);
                    mobileAuthentication.setLogin(mobile);
                    mobileAuthentication.setSalt(salt);
                    mobileAuthentication.setPassword(UtilMD5Encoder.encodePassword(
                            password,salt));
                    getMobileAuthenticationService().add(mobileAuthentication);
                    //保存用户名认证
                    LoginAuthentication loginAuthentication = new LoginAuthentication();
                    loginAuthentication.setUser(user);
                    loginAuthentication.setLogin(name);
                    loginAuthentication.setSalt(salt);
                    loginAuthentication.setPassword(UtilMD5Encoder.encodePassword(
                            password,salt));
                    getLoginAuthenticationService().add(loginAuthentication);
//	                 qq
	                if("3".equals(type)){
                        //绑定qq
                        QQAuthentication  qq = new QQAuthentication();
                        qq.setLogin(login);
                        qq.setUser(user);
                        getQQAuthenticationService().add(qq);
                        data.setMsg("注册成功。");
	                }
	                // 微博
                    if("4".equals(type)){
                        //绑定微博
                        WeiboAuthentication  weibo = new WeiboAuthentication();
                        weibo.setLogin(login);
                        weibo.setUser(user);
                        super.getAuthenticationService().addMobileAuthentication(weibo);
                        data.setMsg("注册成功。");
                    }
                    // 微信
                    if("5".equals(type)){
                        //绑定微信
                        WeixinAuthentication  weixin = new  WeixinAuthentication();
                        weixin.setLogin(login);
                        weixin.setUser(user);
                        getWeixinAuthenticationService().add(weixin);
                        data.setMsg("注册成功。");
                    }
	    			 //添加session
	    			 authenticationlist = getAuthenticationService().findByLogin(mobile);
	                 request.getSession().setAttribute(SysConstants.USER_ID, authenticationlist.get(0).getUser().getId());
	                 request.getSession().setAttribute(SysConstants.USER_NAME, name);
	                 request.getSession().setAttribute(SysConstants.USER_TYPE, userinfo.getType());
	            }else{
		             data.setSuccess(false);
		    		 data.setResult(false);
		    		 return "/app/user/boundAccount";
	            }
//			 }else{
//				 data.setSuccess(false);
//	    		 data.setResult(false);
//	    		 return "/app/user/register";
//			 }
		} catch (Exception e) {
			e.printStackTrace();
			data.setMsg(e.toString());
			data.setSuccess(false);
			data.setResult(false);
			return "/app/user/boundAccount";
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		return "/app/user/registerSuccess";
	}
}
