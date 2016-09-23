package com.myhome.web.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myhome.entity.Authentication;
import com.myhome.entity.Region;
import com.myhome.entity.Teacher;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.vo.HelpChildrenVo;
import com.myhome.entity.vo.ImpWorks;
import com.myhome.entity.vo.TeacherVo;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.DateUtil;
import com.myhome.utils.SysConstants;
/**
 * 老师
 * @author lqf
 *
 */
@Controller
@RequestMapping(value ="/teacher", produces = "text/html;charset=UTF-8")
public class TeacherController extends AbstractController{
    /**
     * 进入认真老师页面
     */
    @RequestMapping("/join")
    public String join(HttpServletRequest request,HttpServletResponse response,Model model) {
          String teacherid = request.getParameter("teacherid");
          if(teacherid!=null){
              Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
              TeacherVo teachervo = new TeacherVo();
              teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
              Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
              teachervo.setCity(regionid.toString());
              regionid = getRegionService().get(regionid).getParent().getId();
              teachervo.setProvince(regionid.toString());
              teachervo.setId(teacher.getId());
              teachervo.setWorkUnit(teacher.getWorkUnit());
              teachervo.setMale(teacher.getMale());
              teachervo.setNation(teacher.getNation());
              teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
              teachervo.setCeltyl(teacher.getCeltyl());
              teachervo.setImg(teacher.getImg());
              teachervo.setThumbnailImg(teacher.getThumbnailImg());
              teachervo.setMobile(teacher.getMobile());
              teachervo.setName(teacher.getName());
              teachervo.setBirthday(teacher.getBirthday());
              teachervo.setStatus(teacher.getStatus());
              model.addAttribute("teacher", teachervo);
          }
        return "/app/teacher/join";
    }
    /**
     * 认证成为老师
     * @param teacher
     * lqf
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request,HttpServletResponse response, Teacher teacher,TeacherVo model) {
        String pattern = "yyy-MM-dd"; //首先定义时间格式
        SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        User user = new User();
        user.setId(Long.valueOf(userid));
        Region region = new Region();
        region.setId(Long.valueOf(model.getRegionStr()));
        try{
        if(teacher.getId()==null){//保存
            teacher.setBirthday(Date.valueOf(model.getBirthdayStr()));
            teacher.setAge(DateUtil.getage(teacher.getBirthday()));
            teacher.setStatus(2);
            teacher.setMale(model.getMaleStr()==1);
            teacher.setRegion(region);
            teacher.setUser(user);
            if(model.getCeltyl()!=null&&(!"".equals(model.getCeltyl()))){
	            teacher.setCeltyl(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacher/" + model.getCeltyl());//原图
	            teacher.setThumbnailCeltyl(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacher/thumb_" + model.getCeltyl());
            }
            teacher.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacherphoto/" +model.getImg());//原图
            teacher.setThumbnailImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacherphoto/thumb_" + model.getImg());
            getTeacherService().save(teacher);
            
            //保存角色类型
            UserInfo  userinfo = getUserInfoService().get(Long.valueOf(userid));
            userinfo.setType("4");
            getUserInfoService().update(userinfo); 
            request.getSession().setAttribute(SysConstants.USER_TYPE, userinfo.getType());
        }else{//修改
            teacher = getTeacherService().get(teacher.getId());
            teacher.setBirthday(Date.valueOf(model.getBirthdayStr()));
            teacher.setAge(DateUtil.getage(teacher.getBirthday()));
            teacher.setStatus(2);
            teacher.setRegion(region);
            teacher.setMale(model.getMaleStr()==1);
            if (!"useless".equals(model.getCeltyl())) {
            teacher.setCeltyl(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacher/" + model.getCeltyl());//原图
            teacher.setThumbnailCeltyl(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacher/thumb_" + model.getCeltyl());
            }
            if (!"useless".equals(model.getImg())) {
                teacher.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacherphoto/" +model.getImg());//原图
                teacher.setThumbnailImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/teacherphoto/thumb_" + model.getImg());
                }
            teacher.setMobile(model.getMobile());
            teacher.setName(model.getName());
            teacher.setNation(model.getNation());
            teacher.setWorkUnit(model.getWorkUnit());
            getTeacherService().update(teacher);
        }
          return  "redirect:/teacher/personalCenter.do?teacherid="+teacher.getId();
        }catch(Exception e){
            e.printStackTrace();
            return "/app/teacher/join";
        }
    }
    /**
     * 进入老师个人中心
     */
    @RequestMapping("/personalCenter")
    public String personalCenter(HttpServletRequest request,HttpServletResponse response,Model model) {
    	Integer userid;
        if(request.getParameter("userId")!=null){
        	 userid = Integer.valueOf(request.getParameter("userId"));
        }else if(request.getParameter("userid")!=null){
        	 userid = Integer.valueOf(request.getParameter("userid"));
        }else{
        	 userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        }
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/profile";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
           }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 进入老师个人中心
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/profileEdit";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 修改密码页面
     */
    @RequestMapping("/password")
    public String password(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/password";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    
    }
    
    /**
     * 进入老师个人中心
     */
    @RequestMapping("/helpedKids")
    public String helpedKids(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/relationship/helpedKids";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
           }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 积分规则
     */
    @RequestMapping("/pointsRule")
    public String pointsRule(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/relationship/pointsRule";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
           }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 进入打赏
     */
    @RequestMapping("/rewardedPainter")
    public String rewardedPainter(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/relationship/rewardedPainter";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
           }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 进入老师批量上传页面
     */
    @RequestMapping("/batchimport")
    public String batchimport(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =request.getParameter("userId")!=null?Integer.valueOf(request.getParameter("userId")):Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/bulkUpload";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            model.addAttribute("teacher", teachervo);
           }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 进入老师批量上传列表页面
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =request.getParameter("userId")!=null?Integer.valueOf(request.getParameter("userId")):Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String  teacherid= request.getParameter("teacherid");
        String path="/app/teacher/personalCenter/bulkUploadTable";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        TeacherVo teachervo = new TeacherVo();
        try{
            if(teacherid==null){
                Teacher teacher = getTeacherService().getByUserid(Long.valueOf(userid));
                teacherid  = teacher.getId().toString();
            }
            Teacher teacher =  getTeacherService().get(Long.valueOf(teacherid));
            teachervo.setRegionStr(Integer.valueOf(teacher.getRegion().getId().toString()));
            Long regionid = getRegionService().get(teacher.getRegion().getId()).getParent().getId();
            teachervo.setCity(regionid.toString());
            regionid = getRegionService().get(regionid).getParent().getId();
            teachervo.setProvince(regionid.toString());
            teachervo.setId(teacher.getId());
            teachervo.setWorkUnit(teacher.getWorkUnit());
            teachervo.setMale(teacher.getMale());
            teachervo.setNation(teacher.getNation());
            teachervo.setThumbnailCeltyl(teacher.getThumbnailCeltyl());
            teachervo.setCeltyl(teacher.getCeltyl());
            teachervo.setImg(teacher.getImg());
            teachervo.setThumbnailImg(teacher.getThumbnailImg());
            teachervo.setMobile(teacher.getMobile());
            teachervo.setName(teacher.getName());
            teachervo.setRegion(teacher.getRegion());
            teachervo.setBirthday(teacher.getBirthday());
            teachervo.setUser(teacher.getUser());
            teachervo.setStatus(teacher.getStatus());
            List<ImpWorks> impWorks = (List<ImpWorks>) request.getSession().getAttribute(SysConstants.IMPWORKS);
        	model.addAttribute("impWorks", impWorks);
            model.addAttribute("teacher", teachervo);
           }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
