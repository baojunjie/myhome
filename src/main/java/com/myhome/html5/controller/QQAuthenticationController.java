package com.myhome.html5.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.entity.QQAuthentication;

@Controller(value = "H5QQAuthenticationController")
@RequestMapping("/H5/qqauthentication")
public class QQAuthenticationController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(QQAuthenticationController.class);

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

	/* *//**
	 * qq第三方登陆接口
	 * 
	 * @param id
	 * @return
	 * @throws QQConnectException
	 * @throws IOException
	 */
	/*
	 * @RequestMapping("/callBack") public void callBack(HttpServletRequest
	 * request, HttpServletResponse response) throws IOException,
	 * QQConnectException { response.sendRedirect(new
	 * Oauth().getAuthorizeURL(request)); }
	 *//**
	 * qq第三方登陆回调接口
	 * 
	 * @param id
	 * @return
	 * @throws QQConnectException
	 * @throws IOException
	 */
	/*
	 * @RequestMapping("/callBackSuccess") public String
	 * callBackSuccess(HttpServletRequest request, HttpServletResponse response)
	 * throws IOException, QQConnectException {
	 * response.setContentType("text/html; charset=utf-8"); PrintWriter out =
	 * response.getWriter(); try { AccessToken accessTokenObj = (new
	 * Oauth()).getAccessTokenByRequest(request); String accessToken = null,
	 * openID = null; long tokenExpireIn = 0L; if
	 * (accessTokenObj.getAccessToken().equals("")) { // 我们的网站被CSRF攻击了或者用户取消了授权
	 * // 做一些数据统计工作 System.out.print("没有获取到响应参数"); } else { accessToken =
	 * accessTokenObj.getAccessToken(); tokenExpireIn =
	 * accessTokenObj.getExpireIn();
	 * logger.info("---------------------------------accessToken="+accessToken);
	 * logger
	 * .info("---------------------------------tokenExpireIn="+tokenExpireIn);
	 * request.getSession().setAttribute("demo_access_token", accessToken);
	 * request.getSession().setAttribute("demo_token_expirein",
	 * String.valueOf(tokenExpireIn));
	 * 
	 * // 利用获取到的accessToken 去获取当前用的openid -------- start OpenID openIDObj = new
	 * OpenID(accessToken); openID = openIDObj.getUserOpenID();
	 * 
	 * out.println("欢迎你，代号为 " + openID + " 的用户!");
	 * request.getSession().setAttribute("demo_openid", openID);
	 * out.println("<a href=" + "/shuoshuoDemo.html" +
	 * " target=\"_blank\">去看看发表说说的demo吧</a>"); // 利用获取到的accessToken
	 * 去获取当前用户的openid --------- end
	 * logger.info("---------------------------------openID="+openID);
	 * 
	 * out.println(
	 * "<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>"
	 * ); UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
	 * logger.info("---------------------------------openID="+qzoneUserInfo);
	 * UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
	 * out.println("<br/>"); if (userInfoBean.getRet() == 0) {
	 * out.println(userInfoBean.getNickname() + "<br/>");
	 * out.println(userInfoBean.getGender() + "<br/>"); out.println("黄钻等级： " +
	 * userInfoBean.getLevel() + "<br/>"); out.println("会员 : " +
	 * userInfoBean.isVip() + "<br/>"); out.println("黄钻会员： " +
	 * userInfoBean.isYellowYearVip() + "<br/>"); out.println("<image src=" +
	 * userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
	 * out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() +
	 * "/><br/>"); out.println("<image src=" +
	 * userInfoBean.getAvatar().getAvatarURL100() + "/><br/>"); } else {
	 * out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg()); }
	 * out.println(
	 * "<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>"
	 * );
	 * 
	 * 
	 * 
	 * out.println(
	 * "<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>"
	 * ); PageFans pageFansObj = new PageFans(accessToken, openID); PageFansBean
	 * pageFansBean = pageFansObj.checkPageFans("97700000"); if
	 * (pageFansBean.getRet() == 0) { out.println("<p>验证您" +
	 * (pageFansBean.isFans() ? "是" : "不是") + "QQ空间97700000官方认证空间的粉丝</p>"); }
	 * else { out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg()); }
	 * out.println(
	 * "<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>"
	 * );
	 * 
	 * 
	 * 
	 * out.println(
	 * "<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>"
	 * ); com.qq.connect.api.weibo.UserInfo weiboUserInfo = new
	 * com.qq.connect.api.weibo.UserInfo(accessToken, openID);
	 * com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean =
	 * weiboUserInfo.getUserInfo(); if (weiboUserInfoBean.getRet() == 0) {
	 * //获取用户的微博头像----------------------start out.println("<image src=" +
	 * weiboUserInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
	 * out.println("<image src=" +
	 * weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
	 * out.println("<image src=" +
	 * weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>"); //获取用户的微博头像
	 * ---------------------end
	 * 
	 * //获取用户的生日信息 --------------------start out.println("<p>尊敬的用户，你的生日是： " +
	 * weiboUserInfoBean.getBirthday().getYear() + "年" +
	 * weiboUserInfoBean.getBirthday().getMonth() + "月" +
	 * weiboUserInfoBean.getBirthday().getDay() + "日"); //获取用户的生日信息
	 * --------------------end
	 * 
	 * StringBuffer sb = new StringBuffer(); sb.append("<p>所在地:" +
	 * weiboUserInfoBean.getCountryCode() + "-" +
	 * weiboUserInfoBean.getProvinceCode() + "-" +
	 * weiboUserInfoBean.getCityCode() + weiboUserInfoBean.getLocation());
	 * 
	 * //获取用户的公司信息---------------------------start ArrayList<Company> companies
	 * = weiboUserInfoBean.getCompanies(); if (companies.size() > 0) { //有公司信息
	 * for (int i=0, j=companies.size(); i<j; i++) {
	 * sb.append("<p>曾服役过的公司：公司ID-" + companies.get(i).getID() + " 名称-" +
	 * companies.get(i).getCompanyName() + " 部门名称-" +
	 * companies.get(i).getDepartmentName() + " 开始工作年-" +
	 * companies.get(i).getBeginYear() + " 结束工作年-" +
	 * companies.get(i).getEndYear()); } } else { //没有公司信息 }
	 * //获取用户的公司信息---------------------------end
	 * 
	 * out.println(sb.toString());
	 * 
	 * } else { out.println("很抱歉，我们没能正确获取到您的信息，原因是： " +
	 * weiboUserInfoBean.getMsg()); }
	 * 
	 * out.println(
	 * "<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>"
	 * );
	 * 
	 * 
	 * 
	 * } } catch (QQConnectException e) { } return "/app/home/index"; }
	 */
}
