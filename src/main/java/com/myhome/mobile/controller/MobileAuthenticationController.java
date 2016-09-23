package com.myhome.mobile.controller;

import com.myhome.entity.MobileAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "MobileMobileAuthenticationController")
@RequestMapping("/mobile/mobile/authentication")
public class MobileAuthenticationController extends AbstractController {

	
	
	@RequestMapping("/get")
	public MobileAuthentication get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("mobileAuthentication") MobileAuthentication mobileAuthentication) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("mobileAuthentication") MobileAuthentication mobileAuthentication) {
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
