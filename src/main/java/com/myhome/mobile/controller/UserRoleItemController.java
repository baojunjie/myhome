package com.myhome.mobile.controller;

import com.myhome.entity.UserRoleItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "MobileUserRoleItemController")
@RequestMapping("/mobile/user/role/item")
public class UserRoleItemController extends AbstractController {

	@RequestMapping("/get")
	public UserRoleItem get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("userRoleItem") UserRoleItem userRoleItem) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("userRoleItem") UserRoleItem userRoleItem) {
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
