package com.myhome.mobile.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Comment;
import com.myhome.entity.Tag;
import com.myhome.entity.User;
import com.myhome.entity.Works;
import com.myhome.utils.ReturnData;

/**
 * app 评论 ClassName: CommentController <br/>
 * date: 2015年9月15日 下午4:00:51 <br/>
 * 
 */
@Controller(value = "MobileCommentController")
@RequestMapping(value = "/mobile/comment", produces = "text/html;charset=UTF-8")
public class CommentController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);

	/**
	 * 对作品添加评论 添加评论
	 * 
	 * @param
	 */
	@ResponseBody
	@RequestMapping("/addComment")
	public String addComment(@RequestParam("worksId") Long works_id, @RequestParam("userId") Long user_id,
	        @RequestParam("words") String words) {
		ReturnData returnData = new ReturnData();
		User user = new User();
		user.setId(user_id);
		Works works = new Works();
		works.setId(works_id);
		Comment comment = new Comment();
		comment.setUser(user);
		comment.setWorks(works);
		comment.setWords(words);
		try {
			getCommentService().add(comment);
			returnData.setMsg("添加成功");
		} catch (Exception e) {
			returnData.setMsg("添加评论失败");
			returnData.setReturnCode("1002");
			logger.error(e.getMessage());
			e.printStackTrace();

		}

		return returnData.toString();
	}

	// /**
	// * 查询作品评论
	// * getComment
	// * @author gwb
	// * @param works_id
	// * @param user_id
	// * @param words
	// * @return
	// * 2015年9月17日 上午10:36:45
	// */
	// @ResponseBody
	// @RequestMapping("/getComments")
	// public String getComments(@RequestParam("worksId") Long worksId,int
	// pageNo,int pageSize) {
	// ReturnData returnData = new ReturnData();
	// JSONObject json = new JSONObject();
	// List<Comment> list =new ArrayList<Comment>();
	// Works works=new Works();
	// try {
	// list=super.getCommentService().getCommentListMobile(worksId, pageNo,
	// pageSize);
	//
	// for(Comment comment:list){
	// comment.setWorks(works);
	// comment.getUser().getUserInfo().setUser(null);
	// }
	// // returnData.setData(list);
	// json.put("list", list);
	// returnData.setData(json);
	// returnData.setMsg("查询成功");
	// } catch (Exception e) {
	//
	// returnData.setMsg("查询失败");
	// returnData.setReturnCode("1003");
	// e.printStackTrace();
	//
	// }
	//
	// return returnData.toString();
	// }

	/**
	 * 查询作品评论 getComment
	 * 
	 * @author gwb
	 * @param works_id
	 * @param user_id
	 * @param words
	 * @return 2015年9月17日 上午10:36:45
	 */
	@ResponseBody
	@RequestMapping("/getComments")
	public String gettestComments(@RequestParam("worksId") Long worksId, int pageNo, int pageSize) throws IOException {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		Tag tagh = new Tag();
		tagh.setName("name");
		List<Comment> list = new ArrayList<Comment>();
		try {
			list = super.getCommentService().getCommentListMobile(worksId, pageNo, pageSize);
			json.put("list", list);
			returnData.setData(json);
			returnData.setMsg("查询成功");
		} catch (Exception e) {
			returnData.setMsg("查询失败");
			returnData.setReturnCode("1003");
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		return returnData.toJSon(returnData);
	}

}
