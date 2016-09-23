package com.myhome.mobile.controller;

import java.util.List;
import java.util.Map;

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
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.QQAuthentication;
import com.myhome.entity.Sponsors;
import com.myhome.entity.Teacher;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.VerificationCode;
import com.myhome.entity.WeiboAuthentication;
import com.myhome.entity.WeixinAuthentication;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SMSUtil;
import com.myhome.utils.ShareCodeUtil;
import com.myhome.utils.Tools;
import com.myhome.utils.UtilMD5Encoder;

@Controller(value = "MobileAuthenticationController")
@RequestMapping(value = "/mobile/mobileAuthentication", produces = "text/html;charset=UTF-8")
public class AuthenticationController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    /**
     * 用户注册 register
     * 
     * @author gwb
     * @param request
     * @param response
     * @param authentication
     * @return 2015年9月8日 上午10:36:48
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response,
                           MobileAuthentication authentication) {

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
            return returnData.toString();
        }
        if (null == password || "".equals(password)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入密码");
            return returnData.toString();
        }
        try {
            int salt = (int) (Math.random() * 1000000);
            String pwdSalt = UtilMD5Encoder.encodePassword(password, salt + "");

            authentication.setLogin(mobile);
            authentication.setSalt(salt + "");
            authentication.setPassword(pwdSalt);

            Authentication auth = super.getAuthenticationService().getMobileAuthenticationByName(
                authentication);
            authentication.setLogin(userName);
            Authentication auth1 = super.getAuthenticationService().getMobileAuthenticationByName(
                authentication);
            Long id = 0l;
            if ((null != auth && null != auth.getId()) || (null != auth1 && null != auth1.getId())) {
                returnData.setReturnCode("1009");
                returnData.setMsg("用户名已注册");
                return returnData.toString();
            }
            List<VerificationCode> listVer = super.getVerificationCodeService().findByMobile(
                mobile, code);
            if (listVer.size() == 0) {
                returnData.setReturnCode("1002");
                returnData.setMsg("验证码不正确");
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
        }
        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 判断token值存在 
     * 如果有token直接登录isHaveToken
     * @author ywz
     * @param request
     * @param response
     * @param authentication
     * @return 2015年9月8日 上午10:36:48
     */
    @RequestMapping(value = "/isHaveToken")
    @ResponseBody
    public String isHaveToken(HttpServletRequest request) {

        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        String token = request.getParameter("token");
        String type = request.getParameter("type");
        if (null == token || "".equals(token)) {
            // 系统错误
            returnData.setReturnCode("9999");
            returnData.setMsg("无token值");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == type || "".equals(type)) {
            returnData.setReturnCode("9999");
            returnData.setMsg("无type值");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        try {
            List<Authentication> list = super.getAuthenticationService()
                .getMobileAuthenticationByToken(token, type);
            // 根据token值来查询是否有值
            if (list.size() != 0) {
                Authentication auth1 = list.get(0);
                Authentication loginMobile = super.getAuthenticationService()
                        .getByUseridAndType(auth1.getUser().getId(), "2");
                // 普通用户
                if (auth1.getUser().getUserInfo().getType().equals("1")) {
                    User user = super.getUserService().getMobileUser(auth1.getUser());
                    if (user == null) {
                        returnData.setMsg("user不存在");
                        returnData.setReturnCode("1009");
                        return returnData.toString();
                    }

                    if (user.getUserInfo().getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) user.getUserInfo().getRegion().getRegionCode(),
                            user.getUserInfo().getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }

                    json.put("userInfo", user.getUserInfo());
                    json.put("type", user.getUserInfo().getType());
                    json.put("loginMobile", loginMobile.getLogin());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("2")) {// 画家

                    ArtistInfo artistInfo = new ArtistInfo();
                    artistInfo.setUser(auth1.getUser());
                    ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);

                    // Map<String, Object>
                    // map=super.getArtistService().getArtistMobile(at.getId());
                    // at.setStatus(Integer.parseInt(map.get("status")+""));

                    Artist artist = super.getArtistService().get(at.getId());
                    at.setStatus(artist.getStatus());
                    //
                    // Map<String, Object> map =
                    // super.getAuthenticationService().getUserArtistInfoMobile(
                    // auth1.getUser().getId());

                    if (at.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) at.getRegion().getId(), at.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }

                    json.put("artist", at);
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    // json.put("artist", map);
                    // at.getr
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    json.put("loginMobile", loginMobile.getLogin());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("3")) {// 受捐者
                    HelpChildren children = new HelpChildren();
                    children.setUser(auth1.getUser());
                    HelpChildren ch = super.getHelpChildrenService()
                        .getMobileHelpChildren(children);
                    if (ch == null) {
                        returnData.setMsg("受帮助的小朋友信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }
                    ch.setUser(null);

                    if (ch.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) ch.getRegion().getId(), ch.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("helpChildren", ch);
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    json.put("loginMobile", loginMobile.getLogin());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("4")) {// 老师
                    Teacher teacher = new Teacher();
                    teacher.setUser(auth1.getUser());
                    Teacher te = super.getTeacherService().getMobileTeacher(teacher);

                    if (te == null) {
                        returnData.setMsg("老师信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }

                    if (te.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) te.getRegion().getId(), te.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }

                    te.setUser(null);
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("teacher", te);
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    json.put("loginMobile", loginMobile.getLogin());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("5")) {// 赞助商

                    Sponsors sponsors = new Sponsors();
                    sponsors.setUser(auth1.getUser());
                    Sponsors sp = super.getSponsorsService().getMobileSponsors(sponsors);
                    if (sp == null) {
                        returnData.setMsg("赞助商信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }

                    if (sponsors.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) sponsors.getRegion().getId(), sponsors.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }

                    sp.setUser(null);
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    json.put("sponsors", sp);
                    json.put("loginMobile", loginMobile.getLogin());
                    returnData.setData(json);

                } else if (auth1.getUser().getUserInfo().getType().equals("6")) {// 同时是小画家和受帮助的小朋友
                    // Map<String, Object> map =
                    // super.getAuthenticationService().getUserArtistInfoMobile(
                    // auth1.getUser().getId());
                    ArtistInfo artistInfo = new ArtistInfo();
                    artistInfo.setUser(auth1.getUser());
                    ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);

                    Artist artist = super.getArtistService().get(at.getId());
                    at.setStatus(artist.getStatus());

                    if (at == null) {
                        returnData.setMsg("画家信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }

                    HelpChildren children = new HelpChildren();
                    children.setUser(auth1.getUser());
                    HelpChildren ch = super.getHelpChildrenService()
                        .getMobileHelpChildren(children);
                    if (ch == null) {
                        returnData.setMsg("受帮助的小朋友信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }
                    if (at.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) at.getRegion().getId(), at.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }
                    ch.setUser(null);
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    ch.setUser(null);
                    json.put("helpChildren", ch);
                    json.put("artist", at);
                    json.put("loginMobile", loginMobile.getLogin());
                    returnData.setData(json);
                }
                return returnData.toJSon(returnData);
            } else {
                returnData.setReturnCode(PublicConstants.SUCCESS);
                returnData.setMsg("没有token值");
                returnData.setSuccess(false);
                returnData.setData("false");
                return returnData.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
            return returnData.toString();
        }
    }

    /**
     * 第三方用户注册或绑定 theThirdPartyRegister
     * 
     * @author ywz
     * @param request
     * @param response
     * @param authentication
     * @return 2015年9月8日 上午10:36:48
     */
    @RequestMapping(value = "/theThirdPartyRegister")
    @ResponseBody
    public String theThirdPartyRegister(HttpServletRequest request, HttpServletResponse response) {

        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        // 参数验证
        String token = request.getParameter("token");
        String type = request.getParameter("type");
        String code = request.getParameter("code");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        if (null == mobile || "".equals(mobile)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入账号");
            returnData.setSuccess(false);
            return returnData.toString();
        }

        if (null == code || "".equals(code)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入验证码");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == token || "".equals(token)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("无token");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == type || "".equals(type)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("无type");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        try {
            Authentication authentication = new Authentication();
            authentication.setLogin(mobile);
            Authentication auth = super.getAuthenticationService().getMobileAuthenticationByName(
                authentication);
            List<VerificationCode> listVer = super.getVerificationCodeService().findByMobile(
                mobile, code);
            if (listVer.size() == 0) {
                returnData.setReturnCode("1002");
                returnData.setMsg("验证码不正确");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            Authentication authThird = null;
            if ("3".equals(type)) {
                authThird = new QQAuthentication();
            } else if ("4".equals(type)) {
                authThird = new WeiboAuthentication();
            } else if ("5".equals(type)) {
                authThird = new WeixinAuthentication();
            }

            // 手机账号已经存在
            //            if(Tools.isEmpty(userName)||(null != auth && null != auth.getId())){
            if (Tools.isEmpty(userName)) {
                //绑定手机
                authThird.setUser(auth.getUser());
                authThird.setLogin(token);
                super.getAuthenticationService().addMobileAuthentication(authThird);

            } else {
                int salt = (int) (Math.random() * 1000000);
                String pwdSalt = UtilMD5Encoder.encodePassword(password, salt + "");
                // 将用户信息保存在User t_user_info表中
                User user = new User();
                UserInfo userInfo = new UserInfo();
                userInfo.setType("1");
                userInfo.setRegion(null);
                userInfo.setName(userName);
                user.setUserInfo(userInfo);
                userInfo.setUser(user);
                /**
                 * 新增用户
                 */
                super.getUserService().add(user);
                //插入邀请码
                super.getUserService().updateUserMobileInvitationCode(user.getUserInfo().getId(),
                    ShareCodeUtil.toSerialCode(user.getId()));
                //插入手机账户
                MobileAuthentication aMobile = new MobileAuthentication();
                aMobile.setUser(user);
                aMobile.setPassword(pwdSalt);
                aMobile.setSalt(salt + "");
                aMobile.setLogin(mobile);
                super.getAuthenticationService().addMobileAuthentication(aMobile);
                //插入userName账户
                LoginAuthentication aUserName = new LoginAuthentication();
                aUserName.setUser(user);
                aUserName.setPassword(pwdSalt);
                aUserName.setSalt(salt + "");
                aUserName.setLogin(userName);
                super.getAuthenticationService().addMobileAuthentication(aUserName);
                //插入第三方登录账户QQ
                authThird.setUser(user);
                authThird.setLogin(token);
                super.getAuthenticationService().addMobileAuthentication(authThird);
            }
            List<Authentication> list = super.getAuthenticationService()
                .getMobileAuthenticationByToken(token, type);
            // 根据token值来查询是否有值
            if (list.size() != 0) {
                Authentication auth1 = list.get(0);
                Authentication loginMobile = super.getAuthenticationService()
                        .getByUseridAndType(auth1.getUser().getId(), "2");
                // 普通用户
                if (auth1.getUser().getUserInfo().getType().equals("1")) {
                    User user = super.getUserService().getMobileUser(auth1.getUser());
                    if (user == null) {
                        returnData.setMsg("user不存在");
                        returnData.setReturnCode("1009");
                        return returnData.toString();
                    }

                    if (user.getUserInfo().getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) user.getUserInfo().getRegion().getRegionCode(),
                            user.getUserInfo().getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }
                    json.put("loginMobile", loginMobile.getLogin());
                    json.put("userInfo", user.getUserInfo());
                    json.put("type", user.getUserInfo().getType());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("2")) {// 画家

                    ArtistInfo artistInfo = new ArtistInfo();
                    artistInfo.setUser(auth1.getUser());
                    ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);

                    // Map<String, Object>
                    // map=super.getArtistService().getArtistMobile(at.getId());
                    // at.setStatus(Integer.parseInt(map.get("status")+""));

                    Artist artist = super.getArtistService().get(at.getId());
                    at.setStatus(artist.getStatus());
                    //
                    // Map<String, Object> map =
                    // super.getAuthenticationService().getUserArtistInfoMobile(
                    // auth1.getUser().getId());

                    if (at.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) at.getRegion().getId(), at.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }
                    json.put("loginMobile", loginMobile.getLogin());
                    json.put("artist", at);
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    // json.put("artist", map);
                    // at.getr
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("3")) {// 受捐者
                    HelpChildren children = new HelpChildren();
                    children.setUser(auth1.getUser());
                    HelpChildren ch = super.getHelpChildrenService()
                        .getMobileHelpChildren(children);
                    if (ch == null) {
                        returnData.setMsg("受帮助的小朋友信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }
                    ch.setUser(null);

                    if (ch.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) ch.getRegion().getId(), ch.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }
                    json.put("loginMobile", loginMobile.getLogin());
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("helpChildren", ch);
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("4")) {// 老师
                    Teacher teacher = new Teacher();
                    teacher.setUser(auth1.getUser());
                    Teacher te = super.getTeacherService().getMobileTeacher(teacher);

                    if (te == null) {
                        returnData.setMsg("老师信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }

                    if (te.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) te.getRegion().getId(), te.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }

                    te.setUser(null);
                    json.put("loginMobile", loginMobile.getLogin());
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("teacher", te);
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    returnData.setData(json);
                } else if (auth1.getUser().getUserInfo().getType().equals("5")) {// 赞助商

                    Sponsors sponsors = new Sponsors();
                    sponsors.setUser(auth1.getUser());
                    Sponsors sp = super.getSponsorsService().getMobileSponsors(sponsors);
                    if (sp == null) {
                        returnData.setMsg("赞助商信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }

                    if (sponsors.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) sponsors.getRegion().getId(), sponsors.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }

                    sp.setUser(null);
                    json.put("loginMobile", loginMobile.getLogin());
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    json.put("sponsors", sp);
                    returnData.setData(json);

                } else if (auth1.getUser().getUserInfo().getType().equals("6")) {// 同时是小画家和受帮助的小朋友
                    // Map<String, Object> map =
                    // super.getAuthenticationService().getUserArtistInfoMobile(
                    // auth1.getUser().getId());
                    ArtistInfo artistInfo = new ArtistInfo();
                    artistInfo.setUser(auth1.getUser());
                    ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);

                    Artist artist = super.getArtistService().get(at.getId());
                    at.setStatus(artist.getStatus());

                    if (at == null) {
                        returnData.setMsg("画家信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }

                    HelpChildren children = new HelpChildren();
                    children.setUser(auth1.getUser());
                    HelpChildren ch = super.getHelpChildrenService()
                        .getMobileHelpChildren(children);
                    if (ch == null) {
                        returnData.setMsg("受帮助的小朋友信息不存在");
                        returnData.setReturnCode(PublicConstants.GET_ERROR);
                        return returnData.toString();
                    }
                    if (at.getRegion() != null) {
                        List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                            (long) at.getRegion().getId(), at.getRegion().getLevel());
                        json.put("region", lsMap);
                    } else {
                        json.put("region", null);
                    }
                    ch.setUser(null);
                    json.put("loginMobile", loginMobile.getLogin());
                    json.put("userInfo", auth1.getUser().getUserInfo());
                    json.put("type", auth1.getUser().getUserInfo().getType());
                    ch.setUser(null);
                    json.put("helpChildren", ch);
                    json.put("artist", at);
                    returnData.setData(json);
                }
                return returnData.toJSon(returnData);
            } else {
                returnData.setReturnCode(PublicConstants.SUCCESS);
                returnData.setMsg("没有token值");
                returnData.setSuccess(false);
                returnData.setData("false");
                return returnData.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
            return returnData.toJSon(returnData);
        }
    }

    /**
     * 第三方登录获取手机验证码 getThirdVerificationCode
     * 
     * @author ywz
     * @param request
     * @param response
     * @return 2015年9月10日 上午9:18:54
     */
    @RequestMapping("getThirdVerificationCode")
    @ResponseBody
    public String getThirdVerificationCode(HttpServletRequest request, HttpServletResponse response) {
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
                // 根据手机号查询该用户是否已经注册
                Authentication auth = super.getAuthenticationService()
                    .getMobileAuthenticationByName(authentication);
                if (auth != null) {
                    json.put("isExist", 1);
                } else {
                    json.put("isExist", 0);
                }
                // 保存手机验证码
                super.getVerificationCodeService().saveMobile(mobile, code);
                boolean smsFalg = SMSUtil.send(mobile, SMSUtil.getValidateMessage(code), "我的家");
                //                boolean smsFalg = true;
                if (smsFalg) {
                    json.put("code", code);
                    returnData.setData(json);
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
     * 获取手机验证码 getMobileVerificationCode
     * 
     * @author gwb
     * @param request
     * @param response
     * @return 2015年9月10日 上午9:18:54
     */
    @RequestMapping("getMobileVerificationCode")
    @ResponseBody
    public String getMobileVerificationCode(Authentication uuthentication,
                                            HttpServletRequest request, HttpServletResponse response) {
        ReturnData returnData = new ReturnData();

        JSONObject json = new JSONObject();
        json.put("status", false);
        String mobile = request.getParameter("mobile");
        String type = request.getParameter("type");// 0获取手机验证码，1是修改密码
        Authentication authentication = new Authentication();
        authentication.setLogin(mobile);
        int code = 111111;
        // RandomDataUtil.SixNumRadom();
        if (StringUtils.isEmpty(mobile)) {
            returnData.setReturnCode("1001");
            returnData.setData(json);
            returnData.setMsg("请输入手机号");
        } else {

            try {
                // 根据手机号查询该用户是否已经注册
                Authentication auth = super.getAuthenticationService()
                    .getMobileAuthenticationByName(authentication);
                // 0用户注册 1修改密码
                if (type.equals("0") && auth != null) {
                    returnData.setMsg("该手机号已存在！");
                    returnData.setReturnCode("1002");
                    return returnData.toString();

                } else if ((type.equals("1") && auth == null)) {
                    returnData.setMsg("该手机号不存在！");
                    returnData.setReturnCode("1003");
                    return returnData.toString();
                }
                // 保存手机验证码
                super.getVerificationCodeService().saveMobile(mobile, code);
                //boolean smsFalg = SMSUtil.send(mobile, SMSUtil.getValidateMessage(code), "我的家");
                boolean smsFalg = true;
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
     * 修改密码 getMobileVerificationCode
     * 
     * @author gwb
     * @param request
     * @param response
     * @return 2015年9月10日 上午9:18:54
     */
    @RequestMapping("getMobileUpdatePwd")
    @ResponseBody
    public String getMobilePwd(HttpServletRequest request, HttpServletResponse response,
                               Authentication authentication) {
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
        } else {
            try {

                String str = super.getAuthenticationService().getMobilePwd(authentication, code);
                return str;
            } catch (Exception e) {
                returnData.setMsg("修改密码失败");
                returnData.setReturnCode(PublicConstants.UPDATE_ERROR);
                e.printStackTrace();
                logger.error("修改密码失败", e.getMessage());
            }
        }
        return returnData.toString();

    }

}
