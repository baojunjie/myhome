package com.myhome.html5.controller;

import com.myhome.entity.UserPermissionItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "H5UserPermissionItemController")
@RequestMapping("/H5/user/permission/item")
public class UserPermissionItemController extends AbstractController {

	@RequestMapping("/get")
	public UserPermissionItem get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("userPermissionItem") UserPermissionItem userPermissionItem) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("userPermissionItem") UserPermissionItem userPermissionItem) {
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
