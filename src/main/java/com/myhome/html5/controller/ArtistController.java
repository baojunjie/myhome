package com.myhome.html5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.HelpChildren;
import com.myhome.utils.ReturnData;

@Controller(value = "H5ArtistController")
@RequestMapping(value = "/H5/artist", produces = "text/html;charset=UTF-8")
public class ArtistController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(ArtistController.class);

    /**
     * 获取画家信息 get
     * 
     * @author ywz
     * @param id
     * @return 2015年9月10日 下午7:46:49
     */
    @RequestMapping("/getArtistInfo")
    @ResponseBody
    public String get(HttpServletRequest request, HttpServletResponse response,
                      ArtistInfo artistInfo) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // map = super.getArtistService().getArtistMobile(id);

            ArtistInfo ar = super.getArtistService().getUserArtistInfoMobile(artistInfo);

            if (ar == null) {
                returnData.setMsg("该画家不存在");
                returnData.setReturnCode("1005");
                return returnData.toString();
            }

            Artist artist = super.getArtistService().get(ar.getId());
            ar.setStatus(artist.getStatus());

            if (artistInfo.getUser().getUserInfo() != null
                && artistInfo.getUser().getUserInfo().getType().equals("6")) {
                HelpChildren children = new HelpChildren();
                children.setUser(artistInfo.getUser());
                HelpChildren ch = super.getHelpChildrenService().getMobileHelpChildren(children);
                children.setUser(null);
                json.put("helpChildren", children);
                if (ch == null) {
                    returnData.setMsg("受帮助的小朋友信息不存在");
                    returnData.setReturnCode(PublicConstants.GET_ERROR);
                    return returnData.toString();
                }
            }

            if (ar.getRegion() != null) {
                List<Map<String, String>> lsMap = super.getRegionService().getRegion(
                    (long) ar.getRegion().getId(), ar.getRegion().getLevel());
                json.put("region", lsMap);
            } else {
                json.put("region", null);
            }
            json.put("userInfo", ar.getUser().getUserInfo());
            json.put("type", ar.getUser().getUserInfo().getType());
            json.put("artist", ar);
        } catch (Exception e) {

            e.printStackTrace();
            returnData.setMsg("查询画家信息失败");
            returnData.setReturnCode("1002");
            logger.error("查询画家信息失败", e.getMessage());
        }
        // List<Map<String, Object>>
        // works=super.getWorksService().getWorksInfoMobile(id);

        // json.put("works", works);
        returnData.setData(json);
        return returnData.toJSon(returnData);
    }

    /**
     * 
     * 根据画家id获取画家参赛作品列表 getArtistGameWorks
     * 
     * @author gwb
     * @param id
     * @return 2015年9月12日 下午3:13:04
     */
    @RequestMapping("/getArtistGameWorks")
    @ResponseBody
    public String getArtistGameWorks(HttpServletRequest request, HttpServletResponse response,
                                     Long artistId, int pageNo, int pageSize, int status) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        // Map<String,Object> map=super.getArtistService().getArtistMobile(id);
        List<Map<String, Object>> works = new ArrayList<Map<String, Object>>();
        try {
            works = super.getWorksService().getArtistGameWorks(artistId, pageNo, pageSize, status);
            json.put("works", works);
            returnData.setMsg("查询成功");
        } catch (Exception e) {
            returnData.setMsg("查询失败");
            returnData.setReturnCode("1002");
            returnData.setSuccess(false);
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        returnData.setData(json);
        return returnData.toString();
    }

    /**
     * 根据画家id审核画家信息 审核画家信息 artist提交审核 getCheckArtistMobile
     * 
     * @author ywz
     * @param id
     * @return 2015年9月11日 上午10:49:46
     */
    @RequestMapping("/checkArtistMobile")
    @ResponseBody
    public String interceptorcheckArtistMobile(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestParam("id") Long id, int status,
                                               @RequestParam("userId") String user_id) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        try {
            super.getArtistService().checkArtistMobile(id, status);
            returnData.setMsg("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return returnData.toString();
    }

    //    /**
    //     * 获取画家信息 get
    //     * 
    //     * @author ywz
    //     * @param id
    //     * @return 2015年9月10日 下午7:46:49
    //     */
    //    @RequestMapping("/getArtistInfoTest")
    //    @ResponseBody
    //    public String getTest(@RequestParam("id") Long id) {
    //        ReturnData returnData = new ReturnData();
    //        JSONObject json = new JSONObject();
    //        Artist map = super.getArtistService().get(id);
    //        json.put("map", map);
    //        returnData.setData(json);
    //        return returnData.toJSon(returnData);
    //    }
}
