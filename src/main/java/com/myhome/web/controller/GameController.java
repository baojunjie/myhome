package com.myhome.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Activity;
import com.myhome.entity.Artist;
import com.myhome.entity.Game;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.GameVo;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;

@Controller
@RequestMapping(value = "/game", produces = "text/html;charset=UTF-8")
public class GameController extends AbstractController {

    @RequestMapping("/get")
    public Game get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

    @RequestMapping("/updateindex")
    public String updateindex(Model model, HttpServletRequest request) {
        String worksid = request.getParameter("worksid");
        Works works = getWorksService().get(Long.valueOf(worksid));
        String artistid = works.getArtist().getId().toString();
        Artist artist = getArtistService().get(Long.valueOf(artistid));
        GameVo game = new GameVo();
        game.setId(Integer.valueOf(artistid));
        game.setWorksid(Integer.valueOf(worksid));
        game.setName(artist.getArtistInfo().getName());
        game.setBirthday(artist.getArtistInfo().getBirthday());
        game.setMale(artist.getArtistInfo().getMale() ? "1" : "2");
        game.setNation(artist.getArtistInfo().getNation());
        game.setImg(artist.getArtistInfo().getImg());//缩略图
        game.setOrigin(artist.getArtistInfo().getOrigin());
        game.setRegion(artist.getArtistInfo().getRegion().getId().toString());
        Long regionid = getRegionService()
            .get(Long.valueOf(artist.getArtistInfo().getRegion().getId().toString())).getParent()
            .getId();
        game.setCity(regionid.toString());
        regionid = getRegionService().get(regionid).getParent().getId();
        game.setProvince(regionid.toString());
        game.setIdCode(artist.getArtistInfo().getIdCode());
        game.setSchool(artist.getArtistInfo().getSchool());
        game.setTrainingInstitution(artist.getArtistInfo().getTrainingInstitution());
        game.setParentMobile(artist.getArtistInfo().getParentMobile());
        game.setReferrerSchool(artist.getArtistInfo().getReferrerSchool());//推荐
        game.setReferrerMobile(artist.getArtistInfo().getReferrerMobile());
        if (works.getInstructor() == null || works.getInstructor() == "") {
            game.setInstructor(works.getInstructor());//指导
        } else {
            game.setInstructor(artist.getArtistInfo().getInstructor());//指导
        }
        if (works.getInstructorMobile() == null || works.getInstructorMobile() == "") {
            game.setInstructor(works.getInstructorMobile());//指导
        } else {
            game.setInstructorMobile(artist.getArtistInfo().getInstructorMobile());
        }
        game.setTeacher(artist.getArtistInfo().getTeacher());//指导
        game.setTeacherMobile(artist.getArtistInfo().getTeacherMobile());
        WorksTagItem tag = getWorksTagItemService().getByWorksid(works.getId());
        game.setWorkstag(Integer.valueOf(tag.getTag().getId().toString()));
        game.setWorksname(works.getName());
        game.setDescription(works.getDescription());
        WorksPictureItem pic = getWorksPictureItemService().getByWorksid(works.getId());
        game.setPath(pic.getPicture().getPath());
        game.setCartoon(artist.getArtistInfo().getCartoon());
        //2.0新增
        game.setAlipayAccount(artist.getArtistInfo().getAlipayAccount());
        game.setWeChatAccount(artist.getArtistInfo().getWeChatAccount());
        game.setAccountName(artist.getArtistInfo().getAccountName());
        game.setBankAccount(artist.getArtistInfo().getBankAccount());
        model.addAttribute("game", game);
        return "/app/competition/join";
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
     * lqf
     * 需要拦截
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/interceptorindex")
    public String interceptorjoinindex(HttpServletRequest request, Model model) {
        return "/app/competition/join";
    }

    @RequestMapping("/interceptorAdd")
    public String interceptorAdd(HttpServletRequest request, @ModelAttribute("game") GameVo game,
                                 Model model) {
        String path = "";
        try {
            Integer userid = Integer.valueOf(request.getSession()
                .getAttribute(SysConstants.USER_ID).toString());
            Works works = getGameService().addWeb(game, userid, request);
            model.addAttribute("game", game);
            model.addAttribute("worksid", works.getId());
            model.addAttribute("artistid", works.getArtist().getId());
            model.addAttribute("type", "WA");//参赛
            if (works.getId() != null) {
                path = "/app/competition/info"; //跳到审核页面-- 目前跳刀首页
                //			    path= "redirect:/artist/personal.do?userid="+userid;
            } else {
                path = "/app/competition/join";
            }
        } catch (Exception e) {
            e.printStackTrace();
            path = "/app/competition/join";
        }
        return path;
    }

    /**
     *进入审核状态
     * @return
     */
    @RequestMapping("/examine")
    public String examine(HttpServletRequest request, Model model) {
        Integer userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID)
            .toString());
        String artistid = request.getParameter("artistid");
        String workid = request.getParameter("worksid");
        Works works = new Works();
        Artist artist = new Artist();
        if (workid != null && (!"".equals(workid))) {
            works = getWorksService().get(Long.valueOf(workid));
            works.setStatus(2);
            getWorksService().update(works);
            if (works.getArtist().getStatus() == 1 || works.getArtist().getStatus() == 4) {
                artistid = works.getArtist().getId().toString();
            }
        }
        if (artistid != null && (!"".equals(artistid))) {
            artist = getArtistService().get(Long.valueOf(artistid));
            artist.setStatus(2);
            getArtistService().update(artist);
        }
        model.addAttribute("artistid", artistid == null ? works.getArtist().getId() : artistid);
        return "redirect:/artist/personalCenter.do?userid=" + userid + "&type=2";
    }

    /**
     *进入审核状态
     * @return
     */
    @RequestMapping("/worksexamine")
    @ResponseBody
    public String worksexamine(HttpServletRequest request, Model model, HttpServletResponse response) {
        ReturnData data = new ReturnData();
        response.addHeader("Access-Control-Allow-Origin", "*");
        Integer userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID)
            .toString());
        String workid = request.getParameter("worksid");
        Works works = new Works();
        if (workid != null && (!"".equals(workid))) {
            works = getWorksService().get(Long.valueOf(workid));
            works.setStatus(2);
            getWorksService().update(works);
            if (works.getArtist().getStatus() == 1 || works.getArtist().getStatus() == 4) {
                Artist artist = getArtistService().get(works.getArtist().getId());
                artist.setStatus(2);
                getArtistService().update(artist);
            }
        }
        data.setResult(request.getSession().getServletContext().getContextPath()
                       + "/artist/personalCenter.do?userid=" + userid + "&type=2");
        return data.toString();
    }

    /**
     * 进入关于大赛页面
     * @return
     */
    @RequestMapping("/about")
    public String about() {
        return "/app/competition/about";
    }

    /**
     * 进入申请页面
     * @param id
     * @return
     */
    @RequestMapping("/info")
    public String info() {
        return "/app/competition/info";
    }

    /**
     * 进入赛区活动
     */
    @RequestMapping("/activity")
    public String activity(HttpServletRequest request, Model model, HttpServletResponse response) {
        try {
            List<Activity> list = getActivityService().get();
            if (CommonUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    Date date = list.get(i).getTime();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                    SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy");
                    //这样处理更合适一些，可以避免一些潜在Timestamp 问题
                    list.get(i).setMonth(sdf1.format(date));
                    list.get(i).setDate(sdf2.format(date));
                    list.get(i).setHour(sdf3.format(date));
                    if (i != 0
                        && Integer.parseInt(sdf4.format(date)) < Integer.parseInt(sdf4.format(list
                            .get(i - 1).getTime()))) {
                        list.get(i).setYear(sdf4.format(list.get(i - 1).getTime()));
                    }
                    if(i+1==list.size()){
                        list.get(i).setLast(sdf4.format(date));
                    }
                }
                model.addAttribute("list", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/app/competition/activity";
    }
}
