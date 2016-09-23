package com.myhome.html5.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.entity.WeixinAuthentication;

@Controller(value = "H5WeixinAuthenticationController")
@RequestMapping("/H5/weixin/authentication")
public class WeixinAuthenticationController extends AbstractController {

	@RequestMapping("/get")
	public WeixinAuthentication get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("weixinAuthentication") WeixinAuthentication weixinAuthentication) {
		// TODO 待完成
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
	 * 微信第三方登陆
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws QQConnectException
	 */
	@RequestMapping("/callBack")
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// response.sendRedirect("https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code");

		// 微信
		response.sendRedirect("https://open.weixin.qq.com/connect/qrconnect?appid=wxbdc5610cc59c1631&redirect_uri=haaaaaa.com&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect");
	}

	/**
	 * 微信第三方登录回调接口
	 * 
	 * @param id
	 * @return
	 * @throws QQConnectException
	 * @throws IOException
	 */
	@RequestMapping("/callBackSuccess")
	public void callBackSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
	}
}
