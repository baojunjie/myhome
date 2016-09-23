package com.myhome.html5.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;
import com.myhome.entity.User;
import com.myhome.entity.Works;

@Controller(value = "H5GameWorksItemController")
@RequestMapping("/H5/game/works/item")
public class GameWorksItemController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(GameWorksItemController.class);

	/**
	 * test add
	 * 
	 * @author gwb
	 * @param gameWorksItem
	 *            2015年9月16日 下午8:07:06
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
		GameWorksItem gameWorksItem = new GameWorksItem();
		Works works = new Works();
		works.setId(141l);
		User applicant = new User();
		applicant.setId(10l);
		Game game = new Game();
		game.setId(1l);
		gameWorksItem.setWorks(works);
		gameWorksItem.setApplicant(applicant);
		gameWorksItem.setGame(game);
		gameWorksItem.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		super.getGameWorksItemService().addGameWorksItem(gameWorksItem);
	}

	@RequestMapping("/get")
	@ResponseBody
	public void get(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
		GameWorksItem gameWorksItem = new GameWorksItem();
		Works works = new Works();
		works.setId(141l);
		User applicant = new User();
		applicant.setId(10l);
		Game game = new Game();
		game.setId(1l);
		gameWorksItem.setWorks(works);
		gameWorksItem.setApplicant(applicant);
		gameWorksItem.setGame(game);
		gameWorksItem.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		super.getGameWorksItemService().getGameWorksItem(gameWorksItem);
	}

}
