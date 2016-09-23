package com.myhome.html5.controller;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Picture;
import com.myhome.entity.Tag;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.WorksVo;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;

/**
 * 
 * ClassName: WorksController <br/>
 * 作品展现 date: 2015年9月9日 下午1:38:37 <br/>
 * 
 */
@Controller(value = "H5WorksController")
@RequestMapping(value = "/H5/works", produces = "text/html;charset=UTF-8")
public class WorksController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(WorksController.class);

    @RequestMapping("/worksList")
    @ResponseBody
    public String getWorksList(HttpServletRequest request, HttpServletResponse response, Works works) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        List<Works> listWorks;
        try {
            listWorks = getWorksService().getMobileWorksList(works);
            returnData.setData(listWorks);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("查询作品列表失败");
            returnData.setReturnCode("1002");
            returnData.setSuccess(false);
            logger.error("查询作品列表失败", e.getMessage());
        }
        return returnData.toString();

    }

    /**
     * 上传作品 add
     * 
     * @author ywz
     * @param works
     * @param request
     * @param response
     * @param session
     * @param model
     * @return 2015年9月10日 下午7:17:56
     */
    @RequestMapping("/addWork")
    @ResponseBody
    public String interceptoradd(@ModelAttribute("works") Works works, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();

        Map<String, String> map = new HashMap<String, String>();
        String picture = request.getParameter("picture");
        String artistId = request.getParameter("artistId");
        String tagId = request.getParameter("tagId");

        Artist artist = super.getArtistService().get(Long.parseLong(artistId));
        if (null == picture || "".equals(picture)) {
            returnData.setMsg("图片数据不能为空");
            returnData.setReturnCode("1008");
            returnData.setSuccess(false);
            return returnData.toString();
        }

        Map<String, String> path = ImageUpload.imageUpload(picture, "works");
        if (null == path || "".equals(path)) {
            returnData.setMsg("上传图片失败");
            returnData.setReturnCode("1008");
            returnData.setSuccess(false);
            return returnData.toString();
        }

        works.setArtist(artist);

        // works.setSchool(artist.getArtistInfo().getSchool());
        //works.sets

        works.setAge(artist.getArtistInfo().getAge());
        works.setRegion(artist.getArtistInfo().getRegion());
        works.setMale(artist.getArtistInfo().getMale());
        WorksPictureItem worksPictureItem = new WorksPictureItem();
        Picture pic = new Picture();
        pic.setPath(path.get("thumb_path"));
        pic.setOrginpath(path.get("orginimg"));
        worksPictureItem.setPicture(pic);
        worksPictureItem.setWorks(works);
        Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
        worksPictureItemSet.add(worksPictureItem);
        works.setWorksPictureItemSet(worksPictureItemSet);

        try {
            long id = getWorksService().addMobileWork(works);

            map.put("worksId", id + "");
            WorksTagItem worksTagItem = new WorksTagItem();

            Tag tag = new Tag();
            tag.setId(Long.parseLong(tagId));
            worksTagItem.setWorks(works);
            worksTagItem.setTag(tag);
            // 保存works和 作品类别关系
            Long wtiId = super.getWorksTagItemService().addWorksTagItemMobile(worksTagItem);
            map.put("wtiId", wtiId + "");
            json.put("works", map);
            returnData.setMsg("上传成功");
            returnData.setData(json);
        } catch (Exception e) {

            json.put("status", false);
            returnData.setMsg("上传失败");
            returnData.setReturnCode("1002");
            returnData.setData(json);
            returnData.setSuccess(false);
            e.printStackTrace();
            logger.equals(e.getMessage());

        }
        return returnData.toString();

    }

    /**
     * 修改作品 update
     * 
     * @author gwb
     * @param works
     * @param request
     * @param response
     * @param session
     * @param model
     * @return 2015年9月10日 下午7:21:56
     */
    @RequestMapping("/updateWorks")
    @ResponseBody
    public String update(HttpServletRequest request, HttpServletResponse response, WorksVo worksVo) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        if (!StringUtils.isEmpty(worksVo.getPicture())) {
            Map<String, String> path = ImageUpload.imageUpload(worksVo.getPicture(), "works");
            worksVo.setPicture(path.get("thumb_path"));
            worksVo.setOrginimg(path.get("orginimg"));
            if (null == path || "".equals(path)) {
                json.put("status", false);
                returnData.setMsg("上传图片失败");
                returnData.setSuccess(false);
                return returnData.toString();
            }
        }

        try {
            super.getWorksService().updateWorksMobile(worksVo);
            Map<String, Object> map = super.getWorksService().getgetWorksByIdMobile(worksVo.getWorksId());
            json.put("works", map);
            returnData.setData(json);
            returnData.setMsg("修改成功");
        } catch (Exception e) {
            returnData.setMsg("修改失败");
            returnData.setReturnCode("1002");
            returnData.setSuccess(false);
            e.printStackTrace();
            logger.equals(e.getMessage());
        }
        return returnData.toString();
    }

    /**
     * 
     * app 删除作品 假删 删除作品
     * 
     * 
     * delete
     * 
     * @author gwb
     * @param id
     * @return 2015年9月10日 下午8:07:25
     */
    @RequestMapping("/deleteWorks")
    @ResponseBody
    public String delete(HttpServletRequest request,HttpServletResponse response,Long id) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        try {
            super.getWorksService().deleteWorksMobile(id);
            returnData.setMsg("删除成功");
        } catch (Exception e) {
            returnData.setMsg("删除失败");
            returnData.setSuccess(false);
            returnData.setReturnCode("1002");
            e.printStackTrace();
            logger.equals(e.getMessage());
        }

        return returnData.toString();
    }

    /**
     * 根据works-id 查看作品
     * 
     * @author ywz
     * @param id
     * @return 2015年9月12日 上午10:48:45
     */
    @RequestMapping("/getWorks")
    @ResponseBody
    public String getWorks(HttpServletRequest request,HttpServletResponse response,@RequestParam("id") Long id) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map = super.getWorksService().getgetWorksByIdMobile(id);
            long l = Long.valueOf(String.valueOf(map.get("artist_id"))).longValue();
            ArtistInfo aInfo = super.getArtistInfoService().get(l);
            map.put("img", aInfo.getImg());
            map.put("userId",aInfo.getUser().getId());
            map.put("artistName",aInfo.getName());
            map.put("school",aInfo.getSchool());
            map.put("male",aInfo.getMale());
            map.put("age",aInfo.getAge());
            json.put("data", map);
//            json.put("data", aInfo);
            returnData.setData(json);
            returnData.setMsg("查询数据成功");
        } catch (Exception e) {
            returnData.setMsg("查询数据失败");
            returnData.setReturnCode("1001");
            returnData.setSuccess(false);
            e.printStackTrace();
            logger.equals(e.getMessage());
        }
        return returnData.toString();
    }

    /**
     * 提交审核作品 getWorks
     * 
     * @author gwb
     * @param id
     * @return 2015年9月12日 下午3:36:57
     */
    @RequestMapping("/getCheckWorks")
    @ResponseBody
    public String getCheckWorks(HttpServletRequest request,HttpServletResponse response,String ids, int status) {
        ReturnData returnData = new ReturnData();
        response.addHeader("Access-Control-Allow-Origin", "*");
        String[] idsStr = ids.split(",");

        try {
            boolean falg = super.getWorksService().getCheckWorks(idsStr, status);
            returnData.setMsg("作品审核成功");
        } catch (Exception e) {
            returnData.setMsg("作品审核失败");
            returnData.setReturnCode("1003");
            returnData.setSuccess(false);
            e.printStackTrace();
            logger.equals(e.getMessage());
        }
        return returnData.toString();
    }

}
