package com.myhome.mobile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistPraise;
import com.myhome.entity.User;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPraise;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.ReturnData;

/**
 * app点赞 作品和人物 ClassName: PraiseController <br/>
 * date: 2015年9月15日 下午3:59:23 <br/>
 * 
 * @author 1
 * @version
 * @since JDK 1.6
 */
@Controller(value = "MobilePraiseController")
@RequestMapping(value = "/mobile/praise", produces = "text/html;charset=UTF-8")
public class PraiseController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(PraiseController.class);

	/**
	 * 作品点赞
	 * 
	 * @param voting
	 */

	@RequestMapping("/addPraise")
	@ResponseBody
	public String add(@RequestParam("worksId") Long works_id, @RequestParam("userId") Long user_id) {
		ReturnData returnData = new ReturnData();

		// 1.判断该用户有没有投过票
		List<WorksPraise> workspraiselist = getPraiseService().getByUserAndWorks(works_id, user_id);
		if (CommonUtils.isEmpty(workspraiselist)) {
			// 2.没有投过
			User user = new User();
			user.setId(user_id);
			Works works = getWorksService().get(works_id);
			WorksPraise workpraise = new WorksPraise();
			workpraise.setUser(user);
			workpraise.setWorks(works);
			getPraiseService().add(workpraise);
			works.setPraise(works.getVotenum() == null ? 1 : works.getVotenum() + 1); // 作品投票在作品
			                                                                          // 投票数上+1
			getWorksService().update(works);
			// 作品的票数加1
			returnData.setMsg("点赞成功");
		} else {
			returnData.setMsg("该用户已经给该作品点过赞");
			returnData.setReturnCode("1002");
		}

		return returnData.toString();
	}

	/**
	 * 人物点赞
	 * 
	 * @param voting
	 */
	@ResponseBody
	@RequestMapping("/addmanPraise")
	public String addman(@RequestParam("artistId") Long artist_id, @RequestParam("userId") Long user_id) {
		// 1.判断该用户有没有投过票
		ReturnData returnData = new ReturnData();
		List<ArtistPraise> artistpraiselist = getPraiseService().getByUserAndArtist(artist_id, user_id);
		if (CommonUtils.isEmpty(artistpraiselist)) {
			// 2.没有投过
			User user = new User();
			user.setId(user_id);
			// Works works = getWorksService().get(works_id);
			Artist artist = new Artist();
			artist.setId(artist_id);
			ArtistPraise artistpraise = new ArtistPraise();
			artistpraise.setUser(user);
			artistpraise.setArtist(artist);
			getPraiseService().add(artistpraise);
			// works.setPraise(works.getVotenum()==null?1:works.getVotenum()+1);
			// //需要在人物投票数量+1，目前没有
			// getWorksService().update(works);
			// 作品的票数加1
			returnData.setMsg("点赞成功");
		} else {
			returnData.setMsg("已点赞");
			returnData.setReturnCode("1002");
		}
		return returnData.toString();
	}

}
