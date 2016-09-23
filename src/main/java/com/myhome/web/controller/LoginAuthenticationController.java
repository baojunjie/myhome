package com.myhome.web.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Artist;
import com.myhome.entity.Authentication;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.VerificationCode;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ResultData;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SMSUtil;
import com.myhome.utils.SysConstants;
import com.myhome.utils.UtilMD5;
import com.myhome.utils.UtilMD5Encoder;

@Controller
@RequestMapping(value = "/login/authentication", produces = "text/html;charset=UTF-8")
public class LoginAuthenticationController extends AbstractController {

    @RequestMapping("/get")
    public LoginAuthentication get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

    /**
     * 判断号码存在
     * 
     * @param request
     * @return
     * @author lqf
     */
    @RequestMapping("/checknum")
    @ResponseBody
    public String checknum(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String mobile = request.getParameter("mobile").trim();//手机
            // 一判断该手机是否已经注册
            List<Authentication> authenticationlist = getAuthenticationService()
                .findByLogin(mobile);
            if (CommonUtils.isNotEmpty(authenticationlist)) {
                data.setResult(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return data.toString();
    }

    /**
     * 判断号码存在
     * 
     * @param request
     * @return
     * @author lqf
     */
    @RequestMapping("/checknumback")
    @ResponseBody
    public String checknumback(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String mobile = request.getParameter("mobile").trim();//手机
            // 一判断该手机是否已经注册
            List<Authentication> authenticationlist = getAuthenticationService()
                .findByLogin(mobile);
            if (CommonUtils.isNotEmpty(authenticationlist)) {
                data.setResult(true);
                data.setSuccess(true);
            } else {
                data.setSuccess(false);
                data.setResult(false);
                data.setMsg("该号码未注册");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return data.toString();
    }

    /**
     * 验证 验证码是否正确
     * 
     * @param request
     * @return
     * @author lqf
     */
    @RequestMapping("/checkcode")
    @ResponseBody
    public String checkcode(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String mobile = request.getParameter("mobile");//手机
            String verificationCode = request.getParameter("verificationCode");//验证码
            Date createdDatetime = new Date(new Date().getTime() - 1800000);
            List<VerificationCode> verificationCodelist = getVerificationCodeService()
                .findByMobile(mobile, verificationCode);
            if (CommonUtils.isEmpty(verificationCodelist)
                || createdDatetime.after(verificationCodelist.get(0).getCreatedDatetime())) {
                data.setMsg("验证码不正确。");
                data.setResult(false);
                data.setSuccess(true);
            }
            //			 if(!"111111".equals(verificationCode)){
            //			     data.setMsg("验证码不正确。");
            //	             data.setResult(false);
            //	             data.setSuccess(true);
            //			 }
        } catch (Exception e) {
            e.printStackTrace();
            data.setSuccess(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return data.toString();
    }

    /**
     * 注册用户
     * 
     * @param request
     * @return
     * @author lqf
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String name = request.getParameter("name").trim();//姓名
            String password = request.getParameter("password");//密码
            password = UtilMD5.getMD5ofStr(password).toLowerCase();
            String mobile = request.getParameter("mobile").trim();//手机
            String code = request.getParameter("code").trim();//验证码
            Date createdDatetime = new Date(new Date().getTime() - 1800000);

            if (name != "" && mobile != "" && code != "") {
                if (name.equals(mobile)) {
                    data.setSuccess(false);
                    data.setResult(false);
                    return "/app/user/register";
                }
                // 正则校验用户名
                Pattern pattern = Pattern
                    .compile("^([a-zA-Z_\u4e00-\u9fa5]+([a-zA-Z_\u4e00-\u9fa50-9])*)$");
                Matcher matcher = pattern.matcher(name);
                boolean nameKey = matcher.matches();
                if (nameKey == false) {
                    data.setSuccess(false);
                    data.setResult(false);
                    return "/app/user/register";
                }
                // 正则手机号
                pattern = Pattern.compile("^\\d{11}$");
                matcher = pattern.matcher(mobile);
                boolean mobileKey = matcher.matches();
                if (mobileKey == false) {
                    data.setSuccess(false);
                    data.setResult(false);
                    return "/app/user/register";
                }
                List<VerificationCode> verificationCodelist = getVerificationCodeService()
                    .findByMobile(mobile, code);
                List<Authentication> authenticationlist = getAuthenticationService().findByLogin(
                    mobile);
                List<Authentication> authenticationLogin = getAuthenticationService().findByLogin(
                    name);
                List<UserInfo> list = getUserInfoService().findByName(name);
                if (CommonUtils.isEmpty(authenticationLogin)
                    && CommonUtils.isEmpty(authenticationlist) && CommonUtils.isEmpty(list)
                    && CommonUtils.isNotEmpty(verificationCodelist)
                    && (createdDatetime.before(verificationCodelist.get(0).getCreatedDatetime()))) {
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
                    mobileAuthentication.setPassword(UtilMD5Encoder.encodePassword(password, salt));
                    getMobileAuthenticationService().add(mobileAuthentication);
                    //保存用户名认证
                    LoginAuthentication loginAuthentication = new LoginAuthentication();
                    loginAuthentication.setUser(user);
                    loginAuthentication.setLogin(name);
                    loginAuthentication.setSalt(salt);
                    loginAuthentication.setPassword(UtilMD5Encoder.encodePassword(password, salt));
                    getLoginAuthenticationService().add(loginAuthentication);
                    data.setMsg("注册成功。");
                    //添加session
                    authenticationlist = getAuthenticationService().findByLogin(mobile);
                    request.getSession().setAttribute(SysConstants.USER_ID,
                        authenticationlist.get(0).getUser().getId());
                    request.getSession().setAttribute(SysConstants.USER_NAME, name);
                    request.getSession().setAttribute(SysConstants.USER_TYPE, userinfo.getType());
                } else {
                    data.setSuccess(false);
                    data.setResult(false);
                    return "/app/user/register";
                }
            } else {
                data.setSuccess(false);
                data.setResult(false);
                return "/app/user/register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
            return "/app/user/register";
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return "/app/user/registerSuccess";
    }

    /**
     * 修改密码
     * 
     * @param id
     * @return
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String password = request.getParameter("password");//密码
            password = UtilMD5.getMD5ofStr(password).toLowerCase();
            String mobile = request.getParameter("mobile");//手机
            // 判断该手机是否被注册
            String salt = RandomDataUtil.SixRadom();
            List<Authentication> authenticationlist = getAuthenticationService()
                .findByLogin(mobile);
            Authentication model = authenticationlist.get(0);
            model.setSalt(salt);
            model.setPassword(UtilMD5Encoder.encodePassword(password, salt));
            getAuthenticationService().update(model);
            data.setMsg("修改成功。");
            //		//添加session
            //         request.getSession().setAttribute(SysConstants.USER_ID, authenticationlist.get(0).getUser().getId());
            //         request.getSession().setAttribute(SysConstants.USER_NAME, authenticationlist.get(0).getUser().getUserInfo().getName());
            //         request.getSession().setAttribute(SysConstants.USER_TYPE, authenticationlist.get(0).getUser().getUserInfo().getType());
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return "/app/user/passwordReset/complete";
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
     * 生成验证码并发送短信
     * 
     * @param id
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public String check(@RequestParam("mobile") String mobile, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String msg = "";
            int count = getVerificationCodeService().findCount(mobile, new Date());
            if (count >= 5) {
                msg = "今日发送次数已上限.";
            } else {
                int code = RandomDataUtil.SixNumRadom();
                boolean flag = getVerificationCodeService().save(mobile, code);
                // 发送信息
                if (flag) {
                    boolean isSuccessed = SMSUtil.sendValidate(mobile, code, "我的家");
                    if (isSuccessed) {
                        msg = "成功发送验证码.";
                    } else {
                        msg = "发送验证码失败.";
                    }
                }
            }
            data.setMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return data.toString();
    }

    /**
     * 登入
     * 
     * @param id
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultData data = new ResultData();
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            String url = request.getParameter("url");
            String mobile = request.getParameter("mobile").trim();
            String password = request.getParameter("password");
            password = UtilMD5.getMD5ofStr(password).toLowerCase();
            List<Authentication> authenticationlist = getAuthenticationService().findByLoginOrName(
                mobile);
            if (CommonUtils.isEmpty(authenticationlist)) {
                model.addAttribute("result", "该用户名或手机号码未注册");
                model.addAttribute("mobile", mobile);
                return "/app/user/login";
            } else {
                if (UtilMD5Encoder.isPasswordValid(authenticationlist.get(0).getPassword(),
                    password, authenticationlist.get(0).getSalt())) {
                    if (authenticationlist.get(0).getInvalid()) {

                        Authentication auth = super.getAuthenticationService().getByUseridAndType(
                            authenticationlist.get(0).getUser().getId(), "2");
                        if (mobile.equals(auth.getLogin())) {
                            model.addAttribute("mobile", mobile);
                        } else {
                            model.addAttribute("mobile", auth.getLogin());
                        }
                        return "/app/user/activation";
                    }
                    request.getSession().setAttribute(SysConstants.USER_ID,
                        authenticationlist.get(0).getUser().getId());
                    User user = getUserService().get(authenticationlist.get(0).getUser().getId());
                    request.getSession().setAttribute(SysConstants.USER_NAME,
                        user.getUserInfo().getName());
                    request.getSession().setAttribute(SysConstants.USER_TYPE,
                        user.getUserInfo().getType());
                    request.getSession().setAttribute(SysConstants.USER_INVITATIONCODE,
                        user.getUserInfo().getInvitationCode());
                    request.getSession().setAttribute(SysConstants.USER_PATH,
                        authenticationlist.get(0).getUser().getUserInfo().getImg());
                    data.setMsg("登入成功");
                    return url == null ? "redirect:/home/index.do" : ("redirect:" + url);
                } else {
                    model.addAttribute("result", "密码输入有误");
                    model.addAttribute("mobile", mobile);
                    return "/app/user/login";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
            return "/app/user/login";
        }
    }

    /**
     * 窗口登入
     * 
     * @param id
     * @return
     */
    @RequestMapping("/fastLogin")
    @ResponseBody
    public String fastLogin(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            String mobile = request.getParameter("mobile").trim();
            String password = request.getParameter("password");
            password = UtilMD5.getMD5ofStr(password).toLowerCase();
            List<Authentication> authenticationlist = getAuthenticationService().findByLoginOrName(
                mobile);
            if (CommonUtils.isEmpty(authenticationlist)) {
                data.setMsg("该用户名或手机号码未注册");
                data.setSuccess(false);
            } else {
                if (UtilMD5Encoder.isPasswordValid(authenticationlist.get(0).getPassword(),
                    password, authenticationlist.get(0).getSalt())) {
                    request.getSession().setAttribute(SysConstants.USER_ID,
                        authenticationlist.get(0).getUser().getId());
                    User user = getUserService().get(authenticationlist.get(0).getUser().getId());
                    request.getSession().setAttribute(SysConstants.USER_NAME,
                        user.getUserInfo().getName());
                    request.getSession().setAttribute(SysConstants.USER_TYPE,
                        user.getUserInfo().getType());
                    data.setMsg("登入成功");
                } else {
                    data.setMsg("密码输入有误");
                    data.setSuccess(false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();
    }

    /**
     * 退出登入
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginout")
    public String loginOut(HttpServletRequest request) {
        //     // 清除session
        //     Enumeration<String> em = request.getSession().getAttributeNames();
        //     while (em.hasMoreElements()) {
        //        request.getSession().removeAttribute(em.nextElement().toString());//无法取出第一个
        //     }
        request.getSession().invalidate();
        return "redirect:/home/index.do";

    }

    /**
     * 进入注册页面
     * @return
     */
    @RequestMapping("/registerindex")
    public String registerindex() {
        return "/app/user/register";
    }

    /**
     * 进入登入页面
     * @return
     */
    @RequestMapping("/loginindex")
    public String loginindex(HttpServletRequest request, HttpServletResponse response) {
        return "/app/user/login";
    }

    /**
     * 进入密码重置页面1
     * @return
     */
    @RequestMapping("/resetindex")
    public String resetindex() {
        return "/app/user/passwordReset/index";
    }

    /**
     * 进入密码重置页面2
     * @return
     */
    @RequestMapping("/reset")
    public String reset(Model model, HttpServletRequest request) {
        model.addAttribute("mobile", request.getParameter("mobile"));
        return "/app/user/passwordReset/reset";
    }

    /**
     * 完成页面
     * @return
     */
    @RequestMapping("/complete")
    public String complete() {
        return "/app/user/passwordReset/complete";
    }

    /**
     * 判断用户名的唯一性
     * 
     * @param request
     * @return
     * @author lqf
     */
    @RequestMapping("/checkname")
    @ResponseBody
    public String checkname(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        try {
            String usename = request.getParameter("usename").trim();//手机
            // 一判断该手机是否已经注册
            List<UserInfo> list = getUserInfoService().findByName(usename);
            if (CommonUtils.isNotEmpty(list)) {
                data.setResult(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return data.toString();
    }

    /**
     * 判断密码是否存在
     */
    @RequestMapping("/checkpassword")
    @ResponseBody
    public String checkpassword(HttpServletRequest request, HttpServletResponse response) {
        ResultData data = new ResultData();
        Integer userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID)
            .toString());
        String opassword = request.getParameter("opassword");
        opassword = UtilMD5.getMD5ofStr(opassword).toLowerCase();
        try {
            Authentication authentication = getAuthenticationService().getByUserid(
                Long.valueOf(userid));
            if (UtilMD5Encoder.isPasswordValid(authentication.getPassword(), opassword,
                authentication.getSalt())) {
            } else {
                data.setMsg("原密码输入有误");
                data.setResult(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return data.toString();
    }

    /**
     * 进入登入页面
     * @return
     */
    @RequestMapping("/changePassword")
    public String changePassword(HttpServletRequest request, HttpServletResponse response,
                                 Model model) {
        String path = request.getParameter("path");
        Integer userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID)
            .toString());
        String opassword = request.getParameter("opassword");
        opassword = UtilMD5.getMD5ofStr(opassword).toLowerCase();
        String npassword1 = request.getParameter("npassword1");
        npassword1 = UtilMD5.getMD5ofStr(npassword1).toLowerCase();
        Authentication authentication = getAuthenticationService()
            .getByUserid(Long.valueOf(userid));
        String salt = RandomDataUtil.SixRadom();
        authentication.setSalt(salt);
        authentication.setPassword(UtilMD5Encoder.encodePassword(npassword1, salt));
        getAuthenticationService().update(authentication);
        model.addAttribute("result", "修改成功");
        return "redirect:" + path;
    }

    /**
     * 进入注册协议
     * lqf
     */
    @RequestMapping("/agreement")
    public String agreement() {
        return "/app/user/termsOfService";
    }

    /**
     * 2.15激活帐号
     * 
     * @author ywz
     * @param id
     * @return 2016年1月11日 下午1:46:49
     */
    @RequestMapping("/activate")
    @ResponseBody
    public String activate(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        String mobile = request.getParameter("mobile");
        String code = request.getParameter("code");
        if (null == mobile || "".equals(mobile)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入账号");
            return returnData.toString();
        }
        if (null == code || "".equals(code)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入验证码");
            return returnData.toString();
        }
        try {
            List<VerificationCode> listVer = super.getVerificationCodeService().findByMobile(
                mobile, code);
            if (listVer.size() == 0) {
                returnData.setReturnCode("1002");
                returnData.setMsg("验证码不正确");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            Authentication authentication = new Authentication();
            authentication.setLogin(mobile);
            // 激活用户帐号
            Authentication authMobile = super.getAuthenticationService()
                .getMobileAuthenticationByName(authentication);
            authMobile.setInvalid(false);
            super.getAuthenticationService().update(authMobile);
            Authentication authUserName = super.getAuthenticationService().getByUseridAndType(
                authMobile.getUser().getId(), "0");
            authUserName.setInvalid(false);
            super.getAuthenticationService().update(authUserName);
            Artist artist = super.getArtistService().getByUserid(
                authMobile.getUser().getId().toString());
            artist.setInvalid(false);
            artist.getArtistInfo().setInvalid(false);
            super.getArtistService().update(artist);
            Works work = new Works();
            Artist a = new Artist();
            a.setId(artist.getId());
            work.setArtist(a);
            WorksTagItem wti = super.getWorksTagItemService().getByWorksid(
                super.getWorksService().getWorksByArtistId(work).getId());
            //            wti.getWorks().setInvalid(false);
            wti.setInvalid(false);
            super.getWorksTagItemService().update(wti);
            Works w = super.getWorksService().get(wti.getWorks().getId());
            w.setInvalid(false);
            super.getWorksService().update(w);
            User user = super.getUserService().get(Long.valueOf(authMobile.getUser().getId()));
            request.getSession().setAttribute(SysConstants.USER_ID, user.getId());
            request.getSession().setAttribute(SysConstants.USER_NAME, user.getUserInfo().getName());
            request.getSession().setAttribute(SysConstants.USER_TYPE, user.getUserInfo().getType());
            json.put("url", request.getContextPath() + "/home/index.do");
            returnData.setMsg("激活成功");
            returnData.setSuccess(true);
        } catch (Exception e) {
            returnData.setSuccess(false);
            returnData.setMsg("激活出错");
            return returnData.toString();
        }
        returnData.setData(json);
        return returnData.toJSon(returnData);
    }
}
