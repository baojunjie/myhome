package com.myhome.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Authentication;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.VerificationCode;
import com.myhome.entity.WeiboAuthentication;
import com.myhome.utils.ReturnData;
import com.myhome.utils.ShareCodeUtil;
import com.myhome.utils.Tools;
import com.myhome.utils.UtilMD5Encoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "MobilePageWeiboAuthenticationController")
@RequestMapping("/mobile/weibo/authentication")
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


    @RequestMapping("/addUserInfo")
    @ResponseBody
    public String addUserInfo(HttpServletRequest request, HttpServletResponse response, WeiboAuthentication weiboAuthentication) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        // 参数验证
        String userName = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String token = request.getParameter("token");
        // 手机验证码
        String code = request.getParameter("code");
        if (null == mobile || "".equals(mobile)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入账号");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == password || "".equals(password)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("请输入密码");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        if (null == token || "".equals(token)) {
            returnData.setReturnCode("1008");
            returnData.setMsg("无token值");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        Authentication authentication =new Authentication();
        try {
            
            int salt = (int) (Math.random() * 1000000);
            String pwdSalt = UtilMD5Encoder.encodePassword(password, salt + "");

            authentication.setLogin(mobile);
            authentication.setSalt(salt + "");
            authentication.setPassword(pwdSalt);

            Authentication auth = super.getAuthenticationService().getMobileAuthenticationByName(authentication);
            authentication.setLogin(userName);
            Authentication auth1 = super.getAuthenticationService().getMobileAuthenticationByName(authentication);
            Long id = 0l;
            if ((null != auth && null != auth.getId()) || (null != auth1 && null != auth1.getId())) {
                returnData.setReturnCode("1009");
                returnData.setMsg("用户名已注册");
                returnData.setSuccess(false);
                return returnData.toString();
            }
            List<VerificationCode> listVer = super.getVerificationCodeService().findByMobile(mobile, code);
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
//            authentication.setToken(token);
            // 手机登陆
            id = super.getAuthenticationService().addMobileAuthentication(authentication);
            // 用户名登陆
            if (Tools.notEmpty(userName)) {
                LoginAuthentication loginAuth = new LoginAuthentication();
                loginAuth.setLogin(userName);
                loginAuth.setSalt(salt + "");
                loginAuth.setPassword(pwdSalt);
//                loginAuth.setToken(token);
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
//            logger.error(e.getMessage());
            returnData.setReturnCode("1009");
            returnData.setMsg("程序错误");
            returnData.setSuccess(false);
        }
        returnData.setData(json);
        return returnData.toString();}
}
