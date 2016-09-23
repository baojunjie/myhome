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

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.Region;
import com.myhome.entity.User;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.entity.vo.GameVo;
import com.myhome.entity.vo.RegionSort;
import com.myhome.entity.vo.ReturnArtist;
import com.myhome.entity.vo.ReturnArtistInfo;
import com.myhome.entity.vo.ReturnWorksTag;
import com.myhome.entity.vo.ReturnWorksTagInfo;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.DateUtil;
import com.myhome.utils.SysConstants;

@Controller
@RequestMapping(value = "/artist", produces = "text/html;charset=UTF-8")
public class ArtistController extends AbstractController {

    @RequestMapping("/get")
    public String get(@RequestParam("id") Long id) {
        System.out.println("192.168.1.120");
        Artist artist = getArtistService().get(id);
        return "/login";
        // TODO 待完成
        // throw new RuntimeException();
    }

    @RequestMapping("/add")
    public void add(@ModelAttribute("artist") Artist artist) {
        // TODO 待完成
    }

    @RequestMapping("/update")
    public String update(Model model, HttpServletRequest request, GameVo game) {
        String path = "";
        Integer userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID)
            .toString());
        String status = request.getParameter("status");
        try {
            Artist artist = getArtistService().getByUserid(userid.toString());
            ArtistInfo artistinfo = artist.getArtistInfo();
            User user = new User();
            user.setId(Long.valueOf(userid));

            //所属城市
            Region region = new Region();
            //        if (null != game.getRegion() && !"".equals(game.getRegion())){
            region.setId(Long.valueOf(game.getRegion()));
            //        }
            //画家信息
            artistinfo.setAge(DateUtil.getage(game.getBirthday()));
            if (!"useless".equals(game.getImg())) {
                artistinfo.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                  + "/artist/thumb_" + game.getImg());
                artistinfo.setOrginimg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/artist/"
                                       + game.getImg());
            }
            artistinfo.setName(game.getName());
            artistinfo.setNation(game.getNation());
            artistinfo.setBirthday(game.getBirthday());
            artistinfo.setMale("1".equals(game.getMale()));
            artistinfo.setRegion(region);
            artistinfo.setOrigin(game.getOrigin());
            artistinfo.setZodiac(DateUtil.getZodica(game.getBirthday()));
            artistinfo.setConstellation(DateUtil.getConstellation(game.getBirthday()));
            artistinfo.setIdCode(game.getIdCode());
            artistinfo.setParentMobile(game.getParentMobile());
            artistinfo.setSchool(game.getSchool());
            artistinfo.setTrainingInstitution(game.getTrainingInstitution());
            artistinfo.setSchoolMobile(game.getSchoolMobile());
            //        artistinfo.setInstructor(game.getInstructor());
            //        artistinfo.setInstructorMobile(game.getInstructorMobile());
            artistinfo.setTeacher(game.getTeacher());
            artistinfo.setCartoon(game.getCartoon());
            artistinfo.setTeacherMobile(game.getTeacherMobile());
            artistinfo.setReferrerSchool(game.getReferrerSchool());
            artistinfo.setReferrerMobile(game.getReferrerMobile());
            // 2.0新增
            artistinfo.setAlipayAccount(game.getAlipayAccount());
            artistinfo.setWeChatAccount(game.getWeChatAccount());
            artistinfo.setBankAccount(game.getBankAccount());
            artistinfo.setAccountName(game.getAccountName());

            artist.setArtistInfo(artistinfo);
            if (status == null) {
                artist.setStatus(2);
            }
            getArtistService().update(artist);
            model.addAttribute("type", "A");//参赛
            model.addAttribute("artistid", artist.getId());
            //        path="redirect:/artist/personal.do?userid="+userid;
            path = "redirect:/artist/personalCenter/profile.do?artistid=" + game.getId(); //跳到审核页面-- 目前跳刀首页
        } catch (Exception e) {
            e.printStackTrace();
            path = "redirect:/artist/personalCenter/edit.do?artistid=" + game.getId();
        }
        return path;

    }

    
    @RequestMapping("/updateindex")
    public String updateindex(Model model, HttpServletRequest request) {
        String artistid = request.getParameter("artistid");
        Artist artist = getArtistService().get(Long.valueOf(artistid));
        GameVo game = new GameVo();
        game.setId(Integer.valueOf(artistid));
        game.setName(artist.getArtistInfo().getName());
        game.setBirthday(artist.getArtistInfo().getBirthday());
        game.setMale(artist.getArtistInfo().getMale() ? "1" : "2");
        game.setNation(artist.getArtistInfo().getNation());
        game.setOrginimg(artist.getArtistInfo().getOrginimg());
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
        game.setInstructor(artist.getArtistInfo().getInstructor());//组织
        game.setInstructorMobile(artist.getArtistInfo().getInstructorMobile());
        game.setTeacher(artist.getArtistInfo().getTeacher());//指导
        game.setTeacherMobile(artist.getArtistInfo().getTeacherMobile());
        game.setCartoon(artist.getArtistInfo().getCartoon());
        model.addAttribute("game", game);
        return "/app/artist/update";
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
     * 跳转到画家个人中心详情
     * @return
     */
    @RequestMapping("profile")
    public String profile(Model model, HttpServletRequest request) {
        String artistid = request.getParameter("artist");
        Artist artist = getArtistService().get(Long.valueOf(artistid));
        model.addAttribute("artistid", artistid);
        model.addAttribute("artist", artist);
        return "/app/user/profile";
    }

    /**
     * 跳转到画家个人中心
     * @return
     */
    @RequestMapping("personal")
    public String personal(Model model, HttpServletRequest request) {
        String result = "";
        String userid = request.getParameter("userid");
        String artistid = request.getParameter("artist");

        Artist artist = new Artist();
        if (artistid != null && (!"".equals(artistid))) {
            artist = getArtistService().get(Long.valueOf(artistid));
            result = "/app/user/index";
        }
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            if (artist == null) {
                result = "/app/competition/join";
            } else {
                artistid = artist.getId().toString();
                result = "/app/user/index";
            }
        }
        model.addAttribute("artistid", artistid);
        model.addAttribute("artist", artist);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        return result;
    }

    /**
     * 跳转到小小画家页面
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        List<Map<String, Object>> cityList = super.getArtistService().getCitySort();
        List<RegionSort> list = new ArrayList<RegionSort>();
        for (int i = 0; i < cityList.size(); i++) {
            list.add(new RegionSort(cityList.get(i).get("name").toString().replace("市", ""),
                Integer.valueOf(cityList.get(i).get("count").toString())));
        }
        if (CommonUtils.isNotEmpty(list)) {
            model.addAttribute("citylist", list.size() > 0 ? list:0);
            model.addAttribute("citycount", list.size() > 0 ?  list.size():0);
        }

        //    	List<RegionSort> list = new ArrayList<RegionSort>();
        //    	List<Region> cityList = getTagService().getListByLevelAndCode(2, null);//获取市
        //        Iterator<Region> it = cityList.iterator();
        //        while (it.hasNext()) {
        //            Region region = it.next();
        //            int num = getWorksService().getArtistByRegion(region);
        //            if (num != 0) {
        //                list.add(new RegionSort(num, region.getName().replace("市", ""),region.getRegionCode()));
        //            }
        //        }
        //        Collections.sort(list);
        //        if(CommonUtils.isNotEmpty(list)){
        //        	model.addAttribute("citylist", list.size()>10?list.subList(0, 10):list);
        //        	model.addAttribute("citycount",list.size()>10?10:list.size());
        //        }
        return "/app/painter/index";
    }

    /**
     * ajax 加载小小画家页面信息
     * @param request
     * @return
     */
    @RequestMapping("/load")
    @ResponseBody
    public String load(HttpServletRequest request, HttpServletResponse response) {
        ReturnArtist data = new ReturnArtist();
        ReturnArtistInfo result = new ReturnArtistInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            Integer searchage = request.getParameter("searchage") == null ? null : Integer
                .valueOf(request.getParameter("searchage"));
            Integer searchmale = request.getParameter("searchmale") == null ? null : Integer
                .valueOf(request.getParameter("searchmale"));
            String searchregion = request.getParameter("searchregion");
            Integer index = request.getParameter("index") == null ? 0 : Integer.valueOf(request
                .getParameter("index"));
            Integer size = request.getParameter("size") == null ? 10 : Integer.valueOf(request
                .getParameter("size"));

            Region region = new Region();
            if (searchregion != null) {
                region = getRegionService().getRegionByName(searchregion);
            }
            Integer total = getArtistService().getListCount(searchmale, region.getId(), searchage);
            List<ArtistModel> artistlist = getArtistService().getList(searchmale, region.getId(),
                searchage, size * index, size);
            result.setTotal(total);
            result.setList(artistlist);
            data.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            data.setSuccess(false);
        }
        return ReturnArtist.toJSon(data);
    }

    /**
     * 查看画家个人信息
     * @param request
     * @return
     */
    @RequestMapping("/myhome")
    @ResponseBody
    public String myhome(HttpServletRequest request, HttpServletResponse response) {
        ReturnWorksTag data = new ReturnWorksTag();
        ReturnWorksTagInfo result = new ReturnWorksTagInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            long artist_id = request.getParameter("artist_id") == null ? null : Long
                .valueOf(request.getParameter("artist_id"));
            Artist artist = getArtistService().get(artist_id);
            Integer index = request.getParameter("index") == null ? 0 : Integer.valueOf(request
                .getParameter("index"));
            Integer size = request.getParameter("size") == null ? 10 : Integer.valueOf(request
                .getParameter("size"));
            Integer type = request.getParameter("type") == null ? 3 : Integer.valueOf(request
                .getParameter("type"));
            List<WorksTagItem> workslist = getWorksService().getByArtistidOfPage(artist_id, type,
                size * index, size);
            Integer total = getWorksService().getByArtistid(artist_id, type);
            result.setTotal(total);
            result.setArtist(artist);
            result.setList(workslist);
            data.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            data.setSuccess(false);
        }
        return ReturnWorksTag.toJSon(data);
    }

    /**
     * 个人中心
     * @author lqf
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/personalCenter")
    public String personalCenter(HttpServletRequest request, Model model) {
    	 String type = request.getParameter("type");
         String userid = request.getParameter("userid")==null?request.getParameter("userId"):request.getParameter("userid");
         String artistid = request.getParameter("artistid");
         Artist artist= new Artist();
         if(userid==null){
         	userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
         }
         Integer status=null;
         if(artistid!=null&&(!"".equals(artistid))){
             artist= getArtistService().get(Long.valueOf(artistid));
             type=null;
         }else{
             if(userid!=null&&(!"".equals(userid))){
                 artist= getArtistService().getByUserid(userid);
                 if(artist==null){
                     return "/app/competition/join";    
                 }else{
                     artistid = artist.getId().toString();
                     status=3;
                 }
             }
         }
         if(type !=null && (!"".equals(type))){
         	status=Integer.valueOf(type);
        }else{
        		status=3;
        }
         model.addAttribute("artistid",artistid);
         model.addAttribute("artist",artist);
         Integer total1  = getWorksService().getByArtistid(Long.valueOf(artistid),1);
         Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid),2);
         Integer total4  = getWorksService().getByArtistid(Long.valueOf(artistid),4);
         Integer total3  = getWorksService().getByArtistid(Long.valueOf(artistid),3);
     	model.addAttribute("total3", total3);
         model.addAttribute("total1", total1);
         model.addAttribute("total2", total2);
         model.addAttribute("total4", total4);
         
         Authentication user = getAuthenticationService().getByUserid(artist.getArtistInfo().getUser().getId());
         model.addAttribute("user", user);
         if(status==1){
         	 model.addAttribute("type",1);
         }else if(status==2){
         	 model.addAttribute("type",2);
         }else if(status==4){
         	 model.addAttribute("type",4);
         }else if(status==3){
         	 model.addAttribute("type",3);
         }
      
         HelpChildren  helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
         if(helpchildren==null){
         	model.addAttribute("ifhelpchildren", 0);
          }else if(helpchildren.getStatus()==3){
         	model.addAttribute("ifhelpchildren", 1);
         }else{
         	model.addAttribute("ifhelpchildren", 0);
         }
        return "/app/painter/personalCenter/works/allInfo";
    }

    /**
     * 详情中心
     * @author lqf
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/personalCenter/profile")
    public String profile(HttpServletRequest request, Model model) {
        String userid = request.getParameter("userId") != null ? request.getParameter("userId")
            : request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        String path = "/app/painter/personalCenter/profile";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
            //                    GameVo game = new GameVo();
            //                    game.setId(Integer.valueOf(artistid));
            //                    game.setName(artist.getArtistInfo().getName());
            //                    game.setBirthday(artist.getArtistInfo().getBirthday());
            //                    game.setMale(artist.getArtistInfo().getMale()?"1":"2");
            //                    game.setNation(artist.getArtistInfo().getNation());
            //                    game.setImg(artist.getArtistInfo().getImg());//缩略图
            //                    game.setOrigin(artist.getArtistInfo().getOrigin());
            //                    game.setRegion(artist.getArtistInfo().getRegion().getId().toString());
            //                    Long regionid = getRegionService().get(Long.valueOf(artist.getArtistInfo().getRegion().getId().toString())).getParent().getId();
            //                    game.setCity(regionid.toString());
            //                    regionid = getRegionService().get(regionid).getParent().getId();
            //                    game.setProvince(regionid.toString());
            //                    game.setIdCode(artist.getArtistInfo().getIdCode());
            //                    game.setSchool(artist.getArtistInfo().getSchool());
            //                    game.setParentMobile(artist.getArtistInfo().getParentMobile());
            //                    game.setReferrerSchool(artist.getArtistInfo().getReferrerSchool());//推荐
            //                    game.setReferrerMobile(artist.getArtistInfo().getReferrerMobile());
            //                    game.setInstructor(artist.getArtistInfo().getInstructor());//组织
            //                    game.setInstructorMobile(artist.getArtistInfo().getInstructorMobile());
            //                    game.setTeacher(artist.getArtistInfo().getTeacher());//指导
            //                    game.setTeacherMobile(artist.getArtistInfo().getTeacherMobile());
            //                    game.setCartoon(artist.getArtistInfo().getCartoon());
            //                    model.addAttribute("game",game);
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return path;
    }

    /**
     * 编辑页面跟人信息
     * @author lqf
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/personalCenter/edit")
    public String edit(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        String path = "/app/painter/personalCenter/profileEdit";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
            GameVo game = new GameVo();
            game.setId(Integer.valueOf(artistid));
            game.setName(artist.getArtistInfo().getName());
            game.setBirthday(artist.getArtistInfo().getBirthday());
            game.setMale(artist.getArtistInfo().getMale() ? "1" : "2");
            game.setNation(artist.getArtistInfo().getNation());
            game.setImg(artist.getArtistInfo().getImg());//缩略图
            game.setOrigin(artist.getArtistInfo().getOrigin());
            game.setRegion(artist.getArtistInfo().getRegion().getId().toString());
            Long regionid = getRegionService()
                .get(Long.valueOf(artist.getArtistInfo().getRegion().getId().toString()))
                .getParent().getId();
            game.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            game.setProvince(regionid.toString());
            game.setIdCode(artist.getArtistInfo().getIdCode());
            game.setSchool(artist.getArtistInfo().getSchool());
            game.setTrainingInstitution(artist.getArtistInfo().getTrainingInstitution());
            game.setParentMobile(artist.getArtistInfo().getParentMobile());
            game.setReferrerSchool(artist.getArtistInfo().getReferrerSchool());//推荐
            game.setReferrerMobile(artist.getArtistInfo().getReferrerMobile());
            game.setInstructor(artist.getArtistInfo().getInstructor());//组织
            game.setInstructorMobile(artist.getArtistInfo().getInstructorMobile());
            game.setTeacher(artist.getArtistInfo().getTeacher());//指导
            game.setTeacherMobile(artist.getArtistInfo().getTeacherMobile());
            game.setCartoon(artist.getArtistInfo().getCartoon());
            // 2.0新增
            game.setAlipayAccount(artist.getArtistInfo().getAlipayAccount());
            game.setWeChatAccount(artist.getArtistInfo().getWeChatAccount());
            game.setBankAccount(artist.getArtistInfo().getBankAccount());
            game.setAccountName(artist.getArtistInfo().getAccountName());
            model.addAttribute("game", game);
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return path;
    }

    /**
     * 推荐方信息
     * @author lqf
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/personalCenter/edit1")
    public String edit1(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        String path = "/app/painter/personalCenter/profileEdit1";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
            GameVo game = new GameVo();
            game.setId(Integer.valueOf(artistid));
            game.setName(artist.getArtistInfo().getName());
            game.setBirthday(artist.getArtistInfo().getBirthday());
            game.setMale(artist.getArtistInfo().getMale() ? "1" : "2");
            game.setNation(artist.getArtistInfo().getNation());
            game.setImg(artist.getArtistInfo().getImg());//缩略图
            game.setOrigin(artist.getArtistInfo().getOrigin());
            game.setRegion(artist.getArtistInfo().getRegion().getId().toString());
            Long regionid = getRegionService()
                .get(Long.valueOf(artist.getArtistInfo().getRegion().getId().toString()))
                .getParent().getId();
            game.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            game.setProvince(regionid.toString());
            game.setIdCode(artist.getArtistInfo().getIdCode());
            game.setSchool(artist.getArtistInfo().getSchool());
            game.setTrainingInstitution(artist.getArtistInfo().getTrainingInstitution());
            game.setParentMobile(artist.getArtistInfo().getParentMobile());
            game.setReferrerSchool(artist.getArtistInfo().getReferrerSchool());//推荐
            game.setReferrerMobile(artist.getArtistInfo().getReferrerMobile());
            game.setInstructor(artist.getArtistInfo().getInstructor());//组织
            game.setInstructorMobile(artist.getArtistInfo().getInstructorMobile());
            game.setTeacher(artist.getArtistInfo().getTeacher());//指导
            game.setTeacherMobile(artist.getArtistInfo().getTeacherMobile());
            game.setCartoon(artist.getArtistInfo().getCartoon());
            // 2.0新增
            game.setAlipayAccount(artist.getArtistInfo().getAlipayAccount());
            game.setWeChatAccount(artist.getArtistInfo().getWeChatAccount());
            game.setBankAccount(artist.getArtistInfo().getBankAccount());
            game.setAccountName(artist.getArtistInfo().getAccountName());
            model.addAttribute("game", game);
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return path;
    }

    /**
     * 收款
     * @author lqf
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/personalCenter/edit2")
    public String edit2(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        String path = "/app/painter/personalCenter/profileEdit2";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
            GameVo game = new GameVo();
            game.setId(Integer.valueOf(artistid));
            game.setName(artist.getArtistInfo().getName());
            game.setBirthday(artist.getArtistInfo().getBirthday());
            game.setMale(artist.getArtistInfo().getMale() ? "1" : "2");
            game.setNation(artist.getArtistInfo().getNation());
            game.setImg(artist.getArtistInfo().getImg());//缩略图
            game.setOrigin(artist.getArtistInfo().getOrigin());
            game.setRegion(artist.getArtistInfo().getRegion().getId().toString());
            Long regionid = getRegionService()
                .get(Long.valueOf(artist.getArtistInfo().getRegion().getId().toString()))
                .getParent().getId();
            game.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            game.setProvince(regionid.toString());
            game.setIdCode(artist.getArtistInfo().getIdCode());
            game.setSchool(artist.getArtistInfo().getSchool());
            game.setTrainingInstitution(artist.getArtistInfo().getTrainingInstitution());
            game.setParentMobile(artist.getArtistInfo().getParentMobile());
            game.setReferrerSchool(artist.getArtistInfo().getReferrerSchool());//推荐
            game.setReferrerMobile(artist.getArtistInfo().getReferrerMobile());
            game.setInstructor(artist.getArtistInfo().getInstructor());//组织
            game.setInstructorMobile(artist.getArtistInfo().getInstructorMobile());
            game.setTeacher(artist.getArtistInfo().getTeacher());//指导
            game.setTeacherMobile(artist.getArtistInfo().getTeacherMobile());
            game.setCartoon(artist.getArtistInfo().getCartoon());
            // 2.0新增
            game.setAlipayAccount(artist.getArtistInfo().getAlipayAccount());
            game.setWeChatAccount(artist.getArtistInfo().getWeChatAccount());
            game.setBankAccount(artist.getArtistInfo().getBankAccount());
            game.setAccountName(artist.getArtistInfo().getAccountName());

            model.addAttribute("game", game);
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return path;
    }

    /**
     * 修改密码页面
     */
    @RequestMapping("/password")
    public String password(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return "/app/painter/personalCenter/password";
    }

    /**
     * 收款账户
     * @return
     */
    @RequestMapping("/accountsReceivable")
    public String accountsReceivable(HttpServletRequest request, Model model) {
        String userid = request.getParameter("userId") != null ? request.getParameter("userId")
            : request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return "/app/painter/personalCenter/account/accountsReceivable";
    }

    /**
     * 打赏我的人
     * @return
     */
    @RequestMapping("/ireward")
    public String ireward(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return "/app/painter/personalCenter/relationship/ireward";
    }

    /**
     * 帮助我的小朋友
     * @return
     */
    @RequestMapping("/helpedKids")
    public String helpedKids(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return "/app/painter/personalCenter/relationship/helpedKids";
    }

    /**
     * 打赏我的小画家
     * @return
     */
    @RequestMapping("/rewardedPainter")
    public String rewardedPainter(HttpServletRequest request, Model model) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        Artist artist = new Artist();
        if (userid != null && (!"".equals(userid))) {
            artist = getArtistService().getByUserid(userid);
            artistid = artist.getId().toString();
        }
        model.addAttribute("artist", artist);
        model.addAttribute("artistid", artistid);
        Integer total1 = getWorksService().getByArtistid(Long.valueOf(artistid), 1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid), 2);
        Integer total3 = getWorksService().getByArtistid(Long.valueOf(artistid), 3);
        Integer total4 = getWorksService().getByArtistid(Long.valueOf(artistid), 4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if (helpchildren == null) {
            model.addAttribute("ifhelpchildren", 0);
        } else if (helpchildren.getStatus() == 3) {
            model.addAttribute("ifhelpchildren", 1);
        } else {
            model.addAttribute("ifhelpchildren", 0);
        }
        return "/app/painter/personalCenter/relationship/rewardedPainter";
    }

}
