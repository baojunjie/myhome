package com.myhome.mobile.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;
import com.myhome.entity.Picture;
import com.myhome.entity.Region;
import com.myhome.entity.Tag;
import com.myhome.entity.User;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.utils.DateUtil;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.ReturnData;

/**
 * 我要参赛 1、用户必须已经注册过之后才可以报名参赛（ps：相当于用户已经在artist中拥有信息） 2、参赛者个人信息保存在artist——info表中
 * 3、同时在works中新增一条记录 4、作品上传到t_picture中 5、通过
 * t_works_picture_item建立works和t_picture的关系 6、通过t_works_tag_item建立t_tag和works的关系
 * ClassName: ArtistInfoController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015年9月10日 下午3:09:03 <br/>
 */

@Controller(value = "MobileGameController")
@RequestMapping(value = "/mobile/game", produces = "text/html;charset=UTF-8")
public class GameController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(GameController.class);

	/**
	 * app 我要参赛 add
	 * 
	 * @author gwb
	 * @param artistInfo
	 * @return 2015年9月10日 下午3:27:00
	 */
//	@SuppressWarnings("unused")
	@RequestMapping("/addCompetition")
	@ResponseBody
	public String addMobielCompetition(@ModelAttribute("artistInfo") ArtistInfo artistInfo, HttpServletRequest request,
	        HttpServletResponse response) {
		String returnStr="";
		ReturnData returnData = new ReturnData();
		try {
			returnStr= super.getGameService().addMobielCompetition(artistInfo,request);
			return returnStr;
        } catch (Exception e) {
        	returnData.setMsg("提交失败");
        	returnData.setReturnCode("1001");
	        e.printStackTrace();
	        return returnData.toString();
        }
	}

	/**
	 * app 点击提交审核 提交作品参赛 add
	 * 
	 * @author gwb
	 * @param artistInfo
	 * @return 2015年9月10日 下午3:27:00
	 */
	@RequestMapping("/addGameWorks")
	@ResponseBody
	public String addGame(String ids, int status, HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		String[] idsStr = ids.split(",");
		// 当前用户信息
		String userId = request.getParameter("userId");
		// game
		Game game = new Game();
		game.setId(1l);// TODO 哪一个赛事
		Works works = new Works();
		User user = new User();
		user.setId(Long.parseLong(userId));
		GameWorksItem gameWorksItem = new GameWorksItem();
		gameWorksItem.setApplicant(user);
		gameWorksItem.setGame(game);
		gameWorksItem.setWorks(works);
		try {
			boolean falg = super.getGameWorksItemService().addGameWorksItemMobile(gameWorksItem, idsStr, status);
			if (falg) {
				returnData.setMsg("提交成功");
			} else {
				returnData.setMsg("提交失败");
				returnData.setReturnCode("1002");
			}
		} catch (Exception e) {
			returnData.setMsg("提交失败");
			returnData.setReturnCode("1002");
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		return returnData.toString();
	}

}

