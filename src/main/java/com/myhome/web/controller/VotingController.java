package com.myhome.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.User;
import com.myhome.entity.Voting;
import com.myhome.entity.Works;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.ResultData;
import com.myhome.utils.SysConstants;



@Controller
@RequestMapping("/voting")
public class VotingController extends AbstractController {

    

    



    @RequestMapping("/get")
    public Voting get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    /**
     * 投票
     * @param voting
     */
    @ResponseBody
    @RequestMapping(value = "/interceptorAdd", produces = "text/html;charset=UTF-8")
    public String Add(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultData data = new ResultData();
        try{
        long works_id = Long.valueOf(request.getParameter("worksid"));
        if (request.getSession().getAttribute(SysConstants.USER_ID) != null) {
            long user_id = Long.valueOf(request.getSession().getAttribute(SysConstants.USER_ID)
                .toString());
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
                    //作品的票数加1
                } else {
                    data.setSuccess(false);
                    data.setMsg("你每天只能给10组作品点赞哦，明天再来吧！");
                }
            } else {
                data.setSuccess(false);
                data.setMsg("你今天已经给该作品点过5次赞，明天再来吧！");
            }
        } else {
            data.setSuccess(false);
            data.setResult(false);
            data.setMsg("您还没有登录哦！");
            data.setCode(1);
        }
        }catch(Exception e){
        	e.printStackTrace();
        	data.setSuccess(false);
            data.setResult(false);
            data.setMsg("点赞失败");
        }
        return data.toJSon(data);
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("voting") Voting voting) {
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
