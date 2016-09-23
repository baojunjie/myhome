package com.myhome.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Region;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.GameVo;
import com.myhome.entity.vo.RegionSort;
import com.myhome.entity.vo.ReturnWorks;
import com.myhome.entity.vo.ReturnWorksInfo;
import com.myhome.entity.vo.WorksList;
import com.myhome.entity.vo.WorksListInfo;
import com.myhome.entity.vo.WorksModel;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;
import com.myhome.utils.Trans2PinYin;

@Controller
@RequestMapping(value = "/works", produces = "text/html;charset=UTF-8")
public class WorksController extends AbstractController {

    @RequestMapping("/get")
    public Works get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

    @RequestMapping("/add")
    public void add(@ModelAttribute("works") Works works) {
        // TODO 待完成
    }

    @RequestMapping("/update")
    public int update(@ModelAttribute("works") Works works) {
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

    /**
     * 进入作品页面
     * @param id
     * @return
     * @throws Exception 
     */
    @RequestMapping("/index")
    public String index(Model model) throws Exception {
        List<Map<String, Object>> cityList = super.getWorksService().getCitySort();
        List<RegionSort> list = new ArrayList<RegionSort>();
        for (int i = 0; i < cityList.size(); i++) {
            list.add(new RegionSort(cityList.get(i).get("name").toString().replace("市", ""),
                Integer.valueOf(cityList.get(i).get("count").toString())));
        }
        if (CommonUtils.isNotEmpty(list)) {
            model.addAttribute("citylist", list.size() > 0 ?list:null);
            model.addAttribute("citycount", list.size() > 0 ?list.size():0);
        }
        return "/app/works/index";
    }

    /**
     * ajax 加载数据
     * @param request
     * @return
     */
    @RequestMapping("/load")
    @ResponseBody
    public String load(HttpServletRequest request, HttpServletResponse response) {
        ReturnWorks data = new ReturnWorks();
        ReturnWorksInfo result = new ReturnWorksInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            Integer total = null;
            Integer searchage = request.getParameter("searchage") == null ? null : Integer
                .valueOf(request.getParameter("searchage"));
            Integer searchtype = request.getParameter("searchtype") == null ? 0 : Integer
                .valueOf(request.getParameter("searchtype"));
            Integer searchmale = request.getParameter("searchmale") == null ? 0 : Integer
                .valueOf(request.getParameter("searchmale"));
            String searchregion = request.getParameter("searchregion");
            Region region = new Region();
            if (searchregion != null) {
                region = getRegionService().getRegionByName(searchregion);
            }
            Integer index = request.getParameter("index") == null ? 0 : Integer.valueOf(request
                .getParameter("index"));
            Integer size = request.getParameter("size") == null ? 10 : Integer.valueOf(request
                .getParameter("size"));
            List<Works> list = new ArrayList<Works>();
            if (CommonUtils.isEmpty(list)) {
                if (searchtype == 0) {//属于top100
                    if (searchmale == 0) {//性别为综合
                        total = getWorksService().getCountByAge(searchage, "");
                        list = getWorksService().getListByAge0fPage(searchage, size * index, size);
                    } else if (searchmale == 1) {//性别为男
                        total = getWorksService().getCountByAge(searchage, "1");
                        list = getWorksService().getListByAgeAndMale0fPage(searchage, true,
                            size * index, size);
                    } else if (searchmale == 2) {//性别为女
                        total = getWorksService().getCountByAge(searchage, "0");
                        list = getWorksService().getListByAgeAndMale0fPage(searchage, false,
                            size * index, size);

                    }
                } else if (searchtype == 1) {//属于分赛区
                    if (searchmale == 0) {//性别为综合searchcity
                        total = getWorksService().getCountByRegion(region.getId(), "");
                        list = getWorksService().getListByRegionOfPage(region.getId(),
                            size * index, size);
                    } else if (searchmale == 1) {//性别为男
                        total = getWorksService().getCountByRegion(region.getId(), "1");
                        list = getWorksService().getListByRegionAndMaleOfPage(region.getId(), true,
                            size * index, size);
                    } else if (searchmale == 2) {//性别为女
                        total = getWorksService().getCountByRegion(region.getId(), "0");
                        list = getWorksService().getListByRegionAndMaleOfPage(region.getId(),
                            false, size * index, size);
                    }
                }
                if (!CommonUtils.isEmpty(list)) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIndex(index * size + i + 1);
                    }
                }
                result.setTotal(total);
                result.setList(list);
                data.setResult(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setSuccess(false);
        }
        return data.toJSon(data);

    }

//    /**
//     * ajax 随机获取四张作品2.1新增
//     * @param request
//     * @return
//     */
//    @RequestMapping("/getRandomWorks")
//    @ResponseBody
//    public String getRandomWorks(HttpServletRequest request, HttpServletResponse response) {
//        ReturnWorks data = new ReturnWorks();
//        ReturnWorksInfo result = new ReturnWorksInfo();
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        try {
//            List<Works> list = getWorksService().getWorksOnStateThree();
//            int[] random = RandomDataUtil.randomCommon(0, list.size() - 1, 4);
//            List<Works> outputList = null;
//            outputList.add(list.get(random[0]));
//            outputList.add(list.get(random[1]));
//            outputList.add(list.get(random[2]));
//            outputList.add(list.get(random[3]));
//            result.setList(outputList);
//            data.setResult(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setSuccess(false);
//        }
//        return data.toJSon(data);
//
//    }

    /**
     * ajax 城市排序2.1新增
     * @param request
     * @return
     */
    @RequestMapping("/getCitySort")
    @ResponseBody
    public String getCitySort(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = super.getWorksService().getCitySort();
            json.put("list", list);
            returnData.setData(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnData.toString();
    }

    /**
     * ajax 根据城市查询作品
     * @param request
     * @return
     */
    @RequestMapping("/getWorksByCityRegionId")
    @ResponseBody
    public String getWorksByCityRegionId(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnWorks data = new ReturnWorks();
        ReturnWorksInfo result = new ReturnWorksInfo();
        Integer age = Integer.parseInt(request.getParameter("age"));
        boolean male = "1".equals(request.getParameter("male")) ? true : false;
        Integer cityRegionId = Integer.parseInt(request.getParameter("cityRegionId"));
        try {
            List<Works> works = getWorksService().getWorksByAgeAndMaleAndRegion(age, male,
                cityRegionId);
            result.setList(works);
            data.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toJSon(data);
    }

    /**
     *跳转到作品详情页面
     */
    @RequestMapping("/worksinfo")
    public String worksinfo(HttpServletRequest request, HttpServletResponse response, Model model) {
    	response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            String worksid = request.getParameter("worksid");
            // 取得作品详情
            WorksTagItem works = getWorksService().getWorksTagItemByWorksid(Long.valueOf(worksid));
            // 获得作品的排序
//            String sort = getWorksService().getTheWorkSort(Long.valueOf(worksid)).get("sort")
//                .toString();
            // 随机取得6张状态为3的作品
            List<Map<String, Object>> list = getWorksService().getWorksOnStateThree();
            //            int[] random = RandomDataUtil.randomCommon(0, list.size(), 6);
            model.addAttribute("work1", list.get(0));
            model.addAttribute("work2", list.get(1));
            model.addAttribute("work3", list.get(2));
            model.addAttribute("work4", list.get(3));
            model.addAttribute("work5", list.get(4));
            model.addAttribute("work6", list.get(5));
            model.addAttribute("works", works);
            model.addAttribute("set", works.getWorks().getWorksPictureItemSet());
            //            model.addAttribute("sort", sort);
            model.addAttribute("artistInfo", works.getWorks().getArtist().getArtistInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "app/works/detail";
    }

    /**
     * 进入作品上传+信息页面
     */
    @RequestMapping("/uploadindex")
    public String uploadindex(HttpServletRequest request, Model model) {
        String path = "/app/works/upload";
        if (request.getSession().getAttribute(SysConstants.USER_ID) == null) {
            path = "redirect:/login/authentication/loginindex.do";
        } else {
            String worksid = request.getParameter("worksid");
            if (worksid != null && (!"".equals(worksid))) {
                WorksTagItem works = getWorksService().getWorksTagItemByWorksid(
                    Long.valueOf(worksid));
                GameVo game = new GameVo();
                game.setWorksid(Integer.valueOf(worksid));
                WorksPictureItem pic = getWorksPictureItemService().getByWorksid(
                    works.getWorks().getId());
                game.setPath(pic.getPicture().getPath());
                game.setWorkstag(Integer.valueOf(works.getTag().getId().toString()));
                game.setWorksname(works.getWorks().getName());
                if (works.getWorks().getInstructor() == null
                    || works.getWorks().getInstructor() == "") {
                    game.setInstructor(works.getWorks().getArtist().getArtistInfo().getInstructor());
                    game.setInstructor(works.getWorks().getArtist().getArtistInfo()
                        .getInstructorMobile());
                } else {
                    game.setInstructor(works.getWorks().getInstructor());
                    game.setInstructorMobile(works.getWorks().getInstructorMobile());
                }
                game.setDescription(works.getWorks().getDescription());
                model.addAttribute("game", game);
                model.addAttribute("status", works.getWorks().getStatus());
            }
        }
        return path;
    }

    /**
     * 进入作品上传+信息页面
     */
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request,
                         @RequestParam(value = "status", required = false) String status,
                         GameVo game, Model model) {
        String path = "";
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = request.getSession().getAttribute(SysConstants.ARTIST_ID).toString();
        if (game.getWorksid() == null && (!"".equals(game.getWorksid()))) {
            String worksid = getWorksService().upload(game, userid, artistid);
            model.addAttribute("worksid", worksid);
        } else {
            Works works = getWorksService().uploaduUpdate(game, userid, artistid);
            if (!"useless".equals(game.getPath())) {
                getWorksService().uploadImg(game, works.getId());
            }
            if ("3".equals(status)) {
                return "redirect:/artist/personalCenter.do?userid=" + userid + "&type=3";
            }
            model.addAttribute("worksid", works.getId());
        }
        model.addAttribute("type", "W");//参赛
        path = "/app/competition/info"; //跳到审核页面-- 目前跳刀首页
        return path;
    }

    /**
     * test
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request, GameVo game) {
        List<Region> list = getTagService().getListByLevelAndCode(null, null);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setNameCode(Trans2PinYin.trans2PinYin(list.get(i).getName()));
            getRegionService().update(list.get(i));
        }

        return "111";

    }

    /**
     * 逻辑删除作品信息
     * 
     */
    @ResponseBody
    @RequestMapping("/deleteworks")
    public String deleteworks(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        WorksModel data = new WorksModel();
        try {
            String worksid = request.getParameter("worksid");
            getWorksService().deleteWorksMobile(Long.valueOf(worksid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toJSon(data);

    }

    /**
     * 作品批量进入审批中.0
     * 修改status为2
     * 
     */
    @ResponseBody
    @RequestMapping("/examineall ")
    public String examineall(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        WorksModel data = new WorksModel();
        try {
            String[] worksid = request.getParameterValues("worksid");
            for (int i = 0; i < worksid.length; i++) {
                Works works = getWorksService().get(Long.valueOf(worksid[i]));
                works.setStatus(2);
                getWorksService().update(works);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toJSon(data);

    }

    /**
     * ajax 加载作品信息
     * @param request
     * @return
     * @author lqf
     */
    @RequestMapping("/getworklist")
    @ResponseBody
    public String getworklist(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        WorksList data = new WorksList();
        try {
            List<Works> boylist = new ArrayList<Works>();
            List<Works> girllist = new ArrayList<Works>();
            WorksListInfo model = new WorksListInfo();
            Integer age = request.getParameter("age") == null ? null : Integer.valueOf(request
                .getParameter("age"));
            String city = request.getParameter("city");
            String type = request.getParameter("type");
            if ("age".equals(type)) {
                boylist = getWorksService().getWorksByAgeAndMaleAndRegion(age, true,
                    city == null ? null : Integer.valueOf(city));
                girllist = getWorksService().getWorksByAgeAndMaleAndRegion(age, false,
                    city == null ? null : Integer.valueOf(city));
                //根据年龄排
                model.setBoylist(boylist);
                model.setGirllist(girllist);
            } else if ("city".equals(type)) {
                //根据城市排
                //        	    if(city==null){
                boylist = getWorksService().getWorksByAgeAndMaleAndRegion1(age, true,
                    city == null ? null : Integer.valueOf(city));
                girllist = getWorksService().getWorksByAgeAndMaleAndRegion1(age, false,
                    city == null ? null : Integer.valueOf(city));
                //        	    }else{
                //                    boylist = getWorksService().getWorksByAgeAndMaleAndRegion
                //                            (age,true,city==null?null:Integer.valueOf(city));
                //                    girllist = getWorksService().getWorksByAgeAndMaleAndRegion
                //                            (age,false,city==null?null:Integer.valueOf(city));
                //        	    }
                model.setBoylist(boylist);
                model.setGirllist(girllist);
            }
            data.setResult(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toJSon(data);
    }
    
    /**
     * 获取最新作品
     * @param request
     * @param reqResponse
     * @return
     */
    @RequestMapping("/getworksbytime")
    @ResponseBody
    public String getWorksByTime (HttpServletRequest request,HttpServletResponse response){
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	Integer index = request.getParameter("index") == null ? 0 : Integer.valueOf(request.getParameter("index"));
    	  Integer size = request.getParameter("size") == null ? 12: Integer.valueOf(request.getParameter("size"));
    	  ReturnData returnData = new ReturnData();
          JSONObject json = new JSONObject();
         try {
             List<Works> works = getWorksService().getWorksByTime(index,size);
            json.put("works",works);
             returnData.setData(json);;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return returnData.toJSon(returnData);
    	
    }

}
