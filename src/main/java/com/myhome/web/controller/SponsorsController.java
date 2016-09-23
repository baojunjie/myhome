package com.myhome.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myhome.entity.Authentication;
import com.myhome.entity.Sponsors;
import com.myhome.entity.vo.HelpChildrenVo;
import com.myhome.entity.vo.SponsorsVo;
import com.myhome.entity.vo.TeacherVo;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.SysConstants;

/**
 * 赞助商
 * @author lqf
 *
 */
@Controller
@RequestMapping(value ="/sponsors", produces = "text/html;charset=UTF-8")
public class SponsorsController extends AbstractController{
    /**
     * 进入认证成为赞助商页面
     */
    @RequestMapping("/join")
    public String join(HttpServletRequest request,HttpServletResponse response,Model model) {
        String sponsorsid = request.getParameter("sponsorsid");
        if(sponsorsid!=null){
            SponsorsVo sponsors=  getSponsorsService().getWebinfo(Long.valueOf(sponsorsid));
            model.addAttribute("company", sponsors);
        }
      return "/app/sponsors/join";
  }
    /**
     * 认证成为赞助商
     * @param teacher
     * lqf
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request,HttpServletResponse response,SponsorsVo model) {
        System.out.println("---------------------------"+request.getSession().getAttribute(SysConstants.USER_ID).toString()+"------------------------------");
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        System.out.println("---------------------------"+userid+"------------------------------");
        String path="";
        try{
            Sponsors sponsors = getSponsorsService().saveOrUpdate(userid,model,request);
            path ="redirect:/sponsors/personalCenter.do?sponsorsid="+sponsors.getId();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("---------------------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx------------------------------");
        }
        return path;
    }
//    /**
//     * 进入赞助商个人中心
//     */
//    @RequestMapping("/personalCenter")
//    public String personalCenter(HttpServletRequest request,HttpServletResponse response,HelpChildrenVo model) {
//        Integer userid =137;//Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
//        String path="/app/sponsors/personalCenter/helpKids";
//        try{
//         
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return path;
//    }
    /**
     * 教授详情页面接口
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
        String sponsorsid = request.getParameter("sponsorsid");
        String path="/app/sponsors/personalCenter/profile";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        SponsorsVo sponsorsvo = new SponsorsVo();
        try{
            if(sponsorsid==null){
                Sponsors  sponsors = getSponsorsService().getByUserid(Long.valueOf(userid));
                sponsorsid = sponsors.getId().toString();
            }
            
            sponsorsvo=  getSponsorsService().getWebinfo(Long.valueOf(sponsorsid));
            model.addAttribute("sponsors", sponsorsvo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 教授详情页面接口
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String sponsorsid = request.getParameter("sponsorsid");
        String path="/app/sponsors/personalCenter/profileEdit";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        SponsorsVo sponsorsvo = new SponsorsVo();
        try{
            if(sponsorsid==null){
                Sponsors  sponsors = getSponsorsService().getByUserid(Long.valueOf(userid));
                sponsorsid = sponsors.getId().toString();
            }
            sponsorsvo=  getSponsorsService().getWebinfo(Long.valueOf(sponsorsid));
            model.addAttribute("company", sponsorsvo);
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
        String sponsorsid = request.getParameter("sponsorsid");
        String path="/app/sponsors/personalCenter/password";
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        SponsorsVo sponsorsvo = new SponsorsVo();
        try{
            if(sponsorsid==null){
                Sponsors  sponsors = getSponsorsService().getByUserid(Long.valueOf(userid));
                sponsorsid = sponsors.getId().toString();
            }
            sponsorsvo=  getSponsorsService().getWebinfo(Long.valueOf(sponsorsid));
            model.addAttribute("company", sponsorsvo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
