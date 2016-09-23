package com.myhome.web.controller;

import com.myhome.entity.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController extends AbstractController {


	@RequestMapping("/get")
	public Authentication get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("authentication") Authentication authentication) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("authentication") Authentication authentication) {
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
