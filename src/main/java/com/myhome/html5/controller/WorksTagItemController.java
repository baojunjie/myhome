package com.myhome.html5.controller;

import com.myhome.entity.WorksTagItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller(value = "H5WorksTagItemController")
@RequestMapping("/H5/works/tag/item")
public class WorksTagItemController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(WorksTagItemController.class);

	@RequestMapping("/get")
	public WorksTagItem get(@RequestParam("id") Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	@RequestMapping("/add")
	public void add(@ModelAttribute("worksTagItem") WorksTagItem worksTagItem) {
		// TODO 待完成
	}

	@RequestMapping("/update")
	public int update(@ModelAttribute("worksTagItem") WorksTagItem worksTagItem) {
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