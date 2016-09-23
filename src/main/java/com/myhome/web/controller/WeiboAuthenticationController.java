package com.myhome.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.Authentication;
import com.myhome.entity.WeiboAuthentication;
import com.myhome.utils.InterfaceUtil;
import com.myhome.utils.JsonUtil;
import com.myhome.utils.SysConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/web/weibo/authentication")
public class WeiboAuthenticationController extends AbstractController {

    @RequestMapping("/get")
    public WeiboAuthentication get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

    @RequestMapping("/add")
    public void add(@ModelAttribute("weiboAuthentication") WeiboAuthentication weiboAuthentication) {
        // TODO 待完成
    }

    @RequestMapping("/update")
    public int update(@ModelAttribute("weiboAuthentication") WeiboAuthentication weiboAuthentication) {
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
    public String apply(HttpServletRequest request) throws UnsupportedEncodingException {
        
        String url ="http://www.haaaaaa.com/web/weibo/authentication/authentication.do";
        url = java.net.URLEncoder.encode(url, "utf-8");
        String appid = "1394861122";
        String GET_URL = "https://api.weibo.com/oauth2/authorize?client_id=" + appid
                + "&redirect_uri=" + url;
        return "redirect:"+GET_URL;
        
    }

    /**
     * weibo认证
     * @param request
     * @return
     * @throws Exception 
     */
    @RequestMapping("/authentication")
    public String authentication(HttpServletRequest request, Model model) throws Exception {
//        String url = request.getContextPath() + "/web/weibo/authentication/authentication.do";
        String error_uri = request.getParameter("error_uri");
        if(error_uri!=null){
            return "/app/user/login";
        }
        String url ="http://www.haaaaaa.com/web/weibo/authentication/authentication.do";
        String code = request.getParameter("code");
        String appid = "1394861122";
        String secret = "d9e6cfba7b8819e48f3c8f16affa25dc";
        String GET_URL = "https://api.weibo.com/oauth2/access_token?client_id=" + appid
                         + "&client_secret=" + secret + "&grant_type=authorization_code" + "&code="
                         + code + "&redirect_uri=" + url;
        String getTaken_result = InterfaceUtil.httpURLConectionPost(GET_URL);
        if (getTaken_result != null) {
            Map<String, String> map = JsonUtil.getMapStr(getTaken_result);
            String uid = map.get("uid");
            List<Authentication> list = super.getAuthenticationService()
                    .getMobileAuthenticationByToken(uid, "4");
            if (list.size() != 0) {
                // 直接登录
                request.getSession().setAttribute(SysConstants.USER_ID,
                    list.get(0).getUser().getId());
                request.getSession().setAttribute(SysConstants.USER_NAME,
                    list.get(0).getUser().getUserInfo().getName());
                request.getSession().setAttribute(SysConstants.USER_TYPE,
                    list.get(0).getUser().getUserInfo().getType());
                return "redirect:/home/index.do";
            } else {
                model.addAttribute("login", uid);
                model.addAttribute("type", "4");
                return "/app/user/boundPhone";
            }
        }
        return "/app/site/500";
    }

}
