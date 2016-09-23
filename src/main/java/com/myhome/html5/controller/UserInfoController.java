package com.myhome.html5.controller;

import com.myhome.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "H5UserInfoController")
@RequestMapping("/H5/user/info")
public class UserInfoController extends AbstractController {

	@RequestMapping("/get")
	public UserInfo get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("userInfo") UserInfo userInfo) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("userInfo") UserInfo userInfo) {
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
