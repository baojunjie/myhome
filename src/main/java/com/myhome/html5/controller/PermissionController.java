package com.myhome.html5.controller;

import com.myhome.entity.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "H5PermissionController")
@RequestMapping("/H5/permission")
public class PermissionController extends AbstractController {

	@RequestMapping("/get")
	public Permission get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("permission") Permission permission) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("permission") Permission permission) {
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
