package com.myhome.html5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.User;
import com.myhome.entity.Voting;
import com.myhome.entity.Works;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;

/**
 * 
 * app 投票 ClassName: VotingController <br/>
 * date: 2015年9月15日 下午4:00:17 <br/>
 * 
 * @version
 */

@Controller(value = "H5VotingController")
@RequestMapping(value = "/H5/voting", produces = "text/html;charset=UTF-8")
public class VotingController extends AbstractController {

    /**
     * 投票
     * 
     * @param voting
     */
    @ResponseBody
    @RequestMapping("/addVoting")
    public String interceptoraddVoting(HttpServletRequest request,HttpServletResponse response,@RequestParam("worksId") Long works_id,
                            @RequestParam("userId") Long user_id) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        //1.判断该用户有没有投过票
        int votingCount = getVotingService().getByUserAndWorks(works_id, user_id);
        if (votingCount < 5) {
            int votinglist10Count = getVotingService().getByUser(user_id);
            if ((votinglist10Count < 10) || (votinglist10Count == 10 && votingCount > 0)) {
                //2.没有投过
                User user = new User();
                user.setId(user_id);
                Works works = getWorksService().get(works_id);
                Voting voting = new Voting();
                voting.setUser(user);
                voting.setWorks(works);
                getVotingService().add(voting);
                works.setVotenum(works.getVotenum() == null ? 1 : works.getVotenum() + 1);
                getWorksService().update(works);
                returnData.setMsg("点赞成功");
                //作品的票数加1
            } else {
                returnData.setSuccess(false);
                returnData.setReturnCode("1003");
                returnData.setMsg("你每天只能给10组作品点赞哦，明天再来吧！");
                returnData.setSuccess(false);
            }
        } else {
            returnData.setSuccess(false);
            returnData.setReturnCode("1002");
            returnData.setMsg("你今天已经给该作品赞过5次啦，明天再来吧！");
        }

        //		// 判断该用户一天给该作品投了多少票
        //		int votingNum = super.getVotingService().getVotingNum(works_id, user_id);
        //		if (votingNum >= 5) {
        //			returnData.setMsg("你今天已经给该作品投过5票啦，明天再来吧！");
        //			returnData.setReturnCode("1002");
        //			return returnData.toString();
        //		}
        //
        //		// 判断该用户今天一共投了多少组票
        //		int votingNo = super.getVotingService().getVotingNumByUserId(works_id, user_id);
        //		
        //		if (votingNo >= 10) {
        //				returnData.setMsg("你每天只能给10组作品投票哦，明天再来吧！");
        //				returnData.setReturnCode("1003");
        //				return returnData.toString();
        //		}
        //		// 2.没有投过
        //		User user = new User();
        //		user.setId(user_id);
        //		Works works = getWorksService().get(works_id);
        //		if (works == null) {
        //			returnData.setMsg("该作品不存在");
        //			returnData.setReturnCode("1004");
        //			return returnData.toString();
        //		}
        //		Voting voting = new Voting();
        //		voting.setUser(user);
        //		voting.setWorks(works);
        //		getVotingService().add(voting);
        //		works.setVotenum(works.getVotenum() == null ? 1 : works.getVotenum() + 1);
        //		getWorksService().update(works);
        //		// 作品的票数加1
        //		returnData.setMsg("投票成功");
        return returnData.toString();
    }

}
