package com.myhome.html5.controller;

import com.myhome.entity.WorksPictureItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "H5WorksPictureItemController")
@RequestMapping("/H5/works/picture/item")
public class WorksPictureItemController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(WorksPictureItemController.class);

	@RequestMapping("/get")
	public WorksPictureItem get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("worksPictureItem") WorksPictureItem worksPictureItem) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("worksPictureItem") WorksPictureItem worksPictureItem) {
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
