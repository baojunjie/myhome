package com.myhome.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Artist;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.RecommendHelpPlayTour;
import com.myhome.entity.User;
import com.myhome.entity.vo.RecommendHelpPlayTourVo;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;

/**
 * // 推荐/打赏/帮助人
 * 
 * @author ywz
 */
@Controller
@RequestMapping(value = "recommendChildren", produces = "text/html;charset=UTF-8")
public class RecommendHelpPlayTourController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(RecommendHelpPlayTourController.class);

    /**
     * 获取账户
     * 
     * @author ywz
     */
    @RequestMapping(value = "/getAccount")
    @ResponseBody
    public String getAccount(RecommendHelpPlayTour recommendChildren,
                                       HttpServletRequest request, HttpServletResponse response, Model model) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            // 查询被打赏/帮助者的账户信息
            if(recommendChildren.getType()==1){
                Artist art = getArtistService().getByUserid(recommendChildren.getUserTo().getId().toString());
                json.put("alipayAccount", art.getArtistInfo().getAlipayAccount());
                json.put("weChatAccount", art.getArtistInfo().getWeChatAccount());
                json.put("bankAccount", art.getArtistInfo().getBankAccount());
                json.put("accountName", art.getArtistInfo().getAccountName());
            }else{
                HelpChildren helpChildren = getHelpChildrenService().getByUserid(Long.valueOf(recommendChildren.getUserTo().getId()));
                json.put("alipayAccount", helpChildren.getAlipayAccount());
                json.put("weChatAccount", helpChildren.getWeChatAccount());
                json.put("bankAccount", helpChildren.getBankAccount());
                json.put("accountName", helpChildren.getAccountName());
            }
            returnData.setData(json);
        } catch (Exception e) {
            logger.error("查询 失败", e);
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setMsg("");
            returnData.setSuccess(false);
        }
        return returnData.toString();
    }

    /**
     * 打赏别人,帮助别人
     * 
     * @author ywz
     */
    @RequestMapping(value = "/addRecommendChildren")
    @ResponseBody
    public String addRecommendChildren(RecommendHelpPlayTour recommendChildren,
                                       HttpServletRequest request, HttpServletResponse response, Model model) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
//        String userId = request.getSession().getAttribute(SysConstants.USER_ID) == null ? null
//            : request.getSession().getAttribute(SysConstants.USER_ID).toString();

        try {
            User userFrom = getUserService().get(Long.valueOf(recommendChildren.getUserFrom().getId()));
            User userTo = getUserService().get(Long.valueOf(recommendChildren.getUserTo().getId()));
            RecommendHelpPlayTour rhpt = new RecommendHelpPlayTour();
            Artist art = getArtistService().getByUserid(recommendChildren.getUserTo().getId().toString());
            if(art.getArtistInfo().getAlipayAccount() != null || art.getArtistInfo().getBankAccount()!= null || art.getArtistInfo().getWeChatAccount()!=null){
            		rhpt.setType(recommendChildren.getType());
            		rhpt.setUserFrom(userFrom);
            		rhpt.setUserTo(userTo);
            		rhpt.setMoney(recommendChildren.getMoney());
            		rhpt.setMessage(recommendChildren.getMessage());
            	super.getRecommendChildrenService().add(rhpt);
            }else{
            	returnData.setReturnCode(PublicConstants.ADD_ERROR);
            	returnData.setMsg("帮助失败，该小朋友没有绑定帐号");
            	returnData.setSuccess(false);
            }
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
    public String searchThePlaysforme(RecommendHelpPlayTourVo recommendChildrenVo,  HttpServletResponse response,int size,
                                      int index) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        int pageSize=size;
        int pageNo=index+1;
        try {
            List<Map<String, Object>> list = getRecommendChildrenService().searchRecommendChildren(
                recommendChildrenVo, 0, pageSize, pageNo);
            int total =getRecommendChildrenService().searchRecommendChildren(recommendChildrenVo, 0);
            json.put("list", list);
            json.put("total", total);
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
    public String searchTheMyPays(RecommendHelpPlayTourVo recommendChildrenVo, HttpServletResponse response, int size,
                                  int index) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        int pageSize=size;
        int pageNo=index+1;
        try {
            List<Map<String, Object>> list = getRecommendChildrenService().searchRecommendChildren(
                recommendChildrenVo, 1, pageSize, pageNo);
            int total=getRecommendChildrenService().searchRecommendChildren(
                recommendChildrenVo, 1);
            json.put("list", list);
            json.put("total", total);
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
