package com.myhome.html5.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Artist;
import com.myhome.entity.Authentication;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.VerificationCode;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SMSUtil;
import com.myhome.utils.ShareCodeUtil;
import com.myhome.utils.Tools;
import com.myhome.utils.UtilMD5Encoder;

@Controller(value = "H5AuthenticationController")
@RequestMapping(value = "/H5/H5Authentication", produces = "text/html;charset=UTF-8")
public class AuthenticationController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    /**
     * 用户注册 register
     * 
     * @author ywz
     * @param request
     * @param response
     * @param authentication
     * @return 2015年9月8日 上午10:36:48
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response,
                           MobileAuthentication authentication) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        // 参数验证
        String userName = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        // 手机验证码
        String code = request.getParameter("code");
        if (null == mobile || "".equals(mobile)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入账号");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == userName || "".equals(userName)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入用户名");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == password || "".equals(password)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入密码");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (userName.equals(mobile)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("手机号码和用户名不能一致");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        int auth = 0;
        int auth1 = 0;
        try {
            // 正则校验用户名
            Pattern pattern = Pattern.compile("^([a-z_\\u4e00-\\u9fa5]+([a-z_\\u4e00-\\u9fa50-9])*)$");
            Matcher matcher = pattern.matcher(userName);
            boolean nameKey = matcher.matches();  
            if(nameKey==false){
                returnData.setReturnCode("1008");
                returnData.setMsg("用户名只能由汉字、字母、数字、下划线组成并且不能以数字开头");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            // 正则手机号
            pattern = Pattern.compile("^\\d{11}$");
            matcher = pattern.matcher(mobile);
            boolean mobileKey = matcher.matches();  
            if(mobileKey==false){
                returnData.setReturnCode("1008");
                returnData.setMsg("手机号码不正确");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            int salt = (int) (Math.random() * 1000000);
            String pwdSalt = UtilMD5Encoder.encodePassword(password, salt + "");

            authentication.setLogin(mobile);
            authentication.setSalt(salt + "");
            authentication.setPassword(pwdSalt);

            auth = super.getAuthenticationService().getMobileAuthenticationByNameCount(
                authentication);
            if (auth > 0) {
                returnData.setReturnCode("1009");
                returnData.setMsg("手机号已注册");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            authentication.setLogin(userName);
            auth1 = super.getAuthenticationService().getMobileAuthenticationByNameCount(
                authentication);
            Long id = 0l;
            if (auth1 > 0) {
                returnData.setReturnCode("1009");
                returnData.setMsg("用户名已注册");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            List<VerificationCode> listVer = super.getVerificationCodeService().findByMobile(
                mobile, code);
            if (listVer.size() == 0) {
                returnData.setReturnCode("1002");
                returnData.setMsg("验证码不正确");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            // 将用户信息保存在User t_user_info表中
            User user = new User();
            UserInfo userInfo = new UserInfo();
            userInfo.setType("1");
            userInfo.setRegion(null);
            // userInfo.setNickName(userName);
            userInfo.setName(userName);
            user.setUserInfo(userInfo);
            userInfo.setUser(user);
            /**
             * 新增用户
             */
            super.getUserService().add(user);
            // 根据用户id生成随机邀请码
            // user.getUserInfo().setInvitationCode(ShareCodeUtil.toSerialCode(user.getId()));
            super.getUserService().updateUserMobileInvitationCode(user.getUserInfo().getId(),
                ShareCodeUtil.toSerialCode(user.getId()));

            authentication.setUser(user);
            authentication.setLogin(mobile);
            // 手机登陆
            id = super.getAuthenticationService().addMobileAuthentication(authentication);
            // 用户名登陆
            if (Tools.notEmpty(userName)) {
                LoginAuthentication loginAuth = new LoginAuthentication();
                loginAuth.setLogin(userName);
                loginAuth.setSalt(salt + "");
                loginAuth.setPassword(pwdSalt);
                loginAuth.setUser(user);
                super.getAuthenticationService().addMobileAuthentication(loginAuth);
            }
            if (id > 0) {
                returnData.setData(true);
                returnData.setMsg("注册成功");
            } else {
                returnData.setData(false);
                returnData.setMsg("注册失败");
                returnData.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setSuccess(false);
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 获取H5验证码 getH5VerificationCode
     * 
     * @author ywz
     * @param request
     * @param response
     * @return 2015年9月10日 上午9:18:54
     */
    @RequestMapping("getH5VerificationCode")
    @ResponseBody
    public String getH5VerificationCode(Authentication uuthentication, HttpServletRequest request,
                                        HttpServletResponse response) {
        ReturnData returnData = new ReturnData();
        response.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject json = new JSONObject();
        json.put("status", false);
        String mobile = request.getParameter("mobile");
        String type = request.getParameter("type");// 0获取手机验证码，1是修改密码
        Authentication authentication = new Authentication();
        authentication.setLogin(mobile);
        //		int code =111111;
        int code = RandomDataUtil.SixNumRadom();
        if (StringUtils.isEmpty(mobile)) {
            returnData.setReturnCode("1001");
            returnData.setData(json);
            returnData.setMsg("请输入手机号");
        } else {
            try {
                // 根据手机号查询该用户是否已经注册
                int authCount = super.getAuthenticationService().getMobileAuthenticationByNameCount(authentication);
                // 0用户注册 1修改密码
                if (type.equals("0") && authCount != 0) {
                    returnData.setMsg("该手机号已存在！");
                    returnData.setReturnCode("1002");
                    returnData.setSuccess(false);
                    return returnData.toString();

                } else if ((type.equals("1") && authCount == 0)) {
                    returnData.setMsg("该手机号不存在！");
                    returnData.setReturnCode("1003");
                    returnData.setSuccess(false);
                    return returnData.toString();
                }
                // 保存手机验证码
                super.getVerificationCodeService().saveMobile(mobile, code);
                boolean smsFalg = SMSUtil.send(mobile, SMSUtil.getValidateMessage(code), "我的家");
                //				 boolean smsFalg = true;
                if (smsFalg) {
                    //                    returnData.setData(code);
                    returnData.setMsg("验证码发送成功！");
                } else {
                    returnData.setMsg("验证码发送失败！");
                    returnData.setReturnCode("1001");
                    returnData.setSuccess(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                returnData.setMsg("获取手机号验证");
                returnData.setReturnCode("1002");
                returnData.setSuccess(false);
            }
        }
        return returnData.toString();
    }

    /**
     * 修改密码 getH5UpdatePwd
     * 
     * @author ywz
     * @param request
     * @param response
     * @return 2015年9月10日 上午9:18:54
     */
    @RequestMapping("getH5UpdatePwd")
    @ResponseBody
    public String getH5UpdatePwd(HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        String mobile = request.getParameter("mobile");
        String code = request.getParameter("code");
        String password = request.getParameter("password");
        authentication.setLogin(mobile);
        json.put("status", false);
        if (StringUtils.isEmpty(mobile)) {
            returnData.setReturnCode("1001");
            returnData.setMsg("请输入手机号");
            returnData.setSuccess(false);
        } else {
            try {

                String str = super.getAuthenticationService().getMobilePwd(authentication, code);
                return str;
            } catch (Exception e) {
                returnData.setMsg("修改密码失败");
                returnData.setReturnCode(PublicConstants.UPDATE_ERROR);
                returnData.setSuccess(false);
                e.printStackTrace();
                logger.error("修改密码失败", e.getMessage());
            }
        }
        return returnData.toString();

    }

    /**
     * 2.15激活帐号用获得验证密码
     * 
     * @author ywz
     * @param id
     * @return 2016年1月11日 下午1:46:49
     */
    @RequestMapping("/getActivateCode")
    @ResponseBody
    public String getActivateCode(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        json.put("status", false);
        String mobile = request.getParameter("mobile");
        Authentication authentication = new Authentication();
        authentication.setLogin(mobile);
        //        int code = 111111;
        int code = RandomDataUtil.SixNumRadom();
        if (StringUtils.isEmpty(mobile)) {
            returnData.setReturnCode("1001");
            returnData.setData(json);
            returnData.setMsg("请输入手机号");
        } else {
            try {
                // 保存手机验证码
                super.getVerificationCodeService().saveMobile(mobile, code);
                boolean smsFalg = SMSUtil.send(mobile, SMSUtil.getValidateMessage(code), "我的家");
                //                boolean smsFalg = true;
                if (smsFalg) {
                    returnData.setData(code);
                    returnData.setMsg("验证码发送成功！");
                } else {
                    returnData.setMsg("验证码发送失败！");
                    returnData.setReturnCode("1001");
                }
            } catch (Exception e) {
                e.printStackTrace();
                returnData.setMsg("获取手机号验证");
                returnData.setReturnCode("1002");
            }
        }
        return returnData.toString();
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
