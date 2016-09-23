package com.myhome.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.RecommendHelpPlayTour;
import com.myhome.entity.User;
import com.myhome.entity.vo.RecommendHelpPlayTourVo;
import com.myhome.utils.ReturnData;

/**
 * // 推荐/打赏/帮助人
 * 
 * @author ywz
 */
@Controller(value = "RecommendHelpPlayTourController")
@RequestMapping(value = "/mobile/recommendChildren", produces = "text/html;charset=UTF-8")
public class RecommendHelpPlayTourController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(RecommendHelpPlayTourController.class);

    /**
     * 打赏别人,帮助别人
     * 
     * @author ywz
     */
    @RequestMapping(value = "/addRecommendChildren")
    @ResponseBody
    public String addRecommendChildren(RecommendHelpPlayTour recommendChildren,
                                       HttpServletRequest request) {
        ReturnData returnData = new ReturnData();

        try {
            User userFrom = getUserService().get(Long.valueOf(recommendChildren.getUserFrom().getId()));
            User userTo = getUserService().get(Long.valueOf(recommendChildren.getUserTo().getId()));
            RecommendHelpPlayTour rhpt = new RecommendHelpPlayTour();
            rhpt.setType(recommendChildren.getType());
            rhpt.setUserFrom(userFrom);
            rhpt.setUserTo(userTo);
            rhpt.setMoney(recommendChildren.getMoney());
            rhpt.setMessage(recommendChildren.getMessage());
            super.getRecommendChildrenService().add(rhpt);
        } catch (Exception e) {
            logger.error("打赏/帮助 失败", e);
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setMsg("");
            returnData.setSuccess(false);
        }
        return returnData.toString();
    }

    /**
     * 查询打赏我的人,查询帮助我的人
     * 
     * @author ywz
     */
    @RequestMapping(value = "/searchThePlaysforme")
    @ResponseBody
    public String searchThePlaysforme(RecommendHelpPlayTourVo recommendChildrenVo,int pageSize,int pageNo) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = getRecommendChildrenService().searchRecommendChildren(recommendChildrenVo,0,pageSize,pageNo);
            json.put("list", list);
            returnData.setData(json);
            returnData.setReturnCode(PublicConstants.SUCCESS);
            returnData.setMsg("成功");
        } catch (Exception e) {
            logger.error("查询打赏/帮助我的人 失败", e);
            returnData.setMsg("失败");
            returnData.setReturnCode("1002");
            returnData.setSuccess(false);
        }
        return returnData.toString();

    }

    /**
     * 查询我打赏的人,查询我帮助的人
     * 
     * @author ywz
     */
    @RequestMapping(value = "/searchTheMyPays")
    @ResponseBody
    public String searchTheMyPays(RecommendHelpPlayTourVo recommendChildrenVo,int pageSize,int pageNo) {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = getRecommendChildrenService().searchRecommendChildren(recommendChildrenVo,1,pageSize,pageNo);
            json.put("list", list);
            returnData.setData(json);
            returnData.setReturnCode(PublicConstants.SUCCESS);
            returnData.setMsg("成功");
        } catch (Exception e) {
            logger.error("查询我打赏/帮助的人 失败", e);
            returnData.setMsg("失败");
            returnData.setReturnCode("1002");
            returnData.setSuccess(false);
        }
        return returnData.toString();

    }
}
