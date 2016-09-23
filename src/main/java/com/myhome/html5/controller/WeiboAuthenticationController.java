package com.myhome.html5.controller;

import com.myhome.entity.WeiboAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "H5PageWeiboAuthenticationController")
@RequestMapping("/H5/weibo/authentication")
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

}
