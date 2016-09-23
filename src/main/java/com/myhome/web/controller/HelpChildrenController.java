package com.myhome.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.vo.HelpChildrenVo;
import com.myhome.utils.SysConstants;

/**
 * 幸运小朋友
 * @author lqf
 *
 */
@Controller
@RequestMapping(value ="/help", produces = "text/html;charset=UTF-8")
public class HelpChildrenController extends AbstractController{
    /**
     * 进入认证成为幸运小朋友页面
     */
//    @RequestMapping("/join")
    public String join(HttpServletRequest request,HttpServletResponse response,Model model) {
    	 response.addHeader("Access-Control-Allow-Origin", "*");
          String helpid = request.getParameter("helpid");
          if(helpid!=null){
              HelpChildrenVo help =  getHelpChildrenService().getWebinfo(Long.valueOf(helpid));
              model.addAttribute("help", help);
          }
        return "/app/luckykids/join";
    }
    /**
     * 认证成为幸运小朋友
     * @param teacher
     * lqf
     */
//    @RequestMapping("/add")
    public String add(HttpServletRequest request,HttpServletResponse response,HelpChildrenVo model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        System.out.println("---------------------------"+userid+"------------------------------");
        String path="";
        try{
            HelpChildren helpchildren = getHelpChildrenService().saveOrUpdate(userid,model,request);
            if(model.getId()==null){
                path="redirect:/help/personalCenter/profile.do?helpchildrenid="+helpchildren.getId(); 
            }else{
                path ="redirect:/help/personalCenter/profile.do?helpchildrenid="+helpchildren.getId(); 
            } 
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("---------------------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx------------------------------");
        }
        return path;
    }
    
    /**
     * 进入幸运小朋友个人中心
     */
//    @RequestMapping("/personalCenter")
    public String personalCenter(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/info";
        try{
            String userid = request.getParameter("userid")==null?request.getParameter("userId"):request.getParameter("userid");
            if(userid==null){
                userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
            }
           
            String helpchildrenid = request.getParameter("helpchildrenid");
            HelpChildren helpchildren = new HelpChildren();
            if(helpchildrenid!=null){
                helpchildren = getHelpChildrenService().get(Long.valueOf(helpchildrenid));
            }else{
                helpchildren = getHelpChildrenService().getByUserid(Long.valueOf(userid));
            }
            model.addAttribute("helpchildren", helpchildren);
            Authentication user = getAuthenticationService().getByUserid(helpchildren.getUser().getId());
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 详情
     */
//    @RequestMapping("/personalCenter/profile")
    public String profile(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/profile";
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        String helpchildrenid = request.getParameter("helpchildrenid");
        HelpChildrenVo helpchildren = new HelpChildrenVo();
            if(helpchildrenid!=null){
                helpchildren=  getHelpChildrenService().getWebinfo(Long.valueOf(helpchildrenid));
            }else{
                HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                 helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            }
            model.addAttribute("helpchildren", helpchildren);
           
            
            Authentication user = getAuthenticationService().getByUserid(userid==null?helpchildren.getUser().getId():(Long.valueOf(userid)));
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    
    /**
     * 编辑页面
     */
//    @RequestMapping("/personalCenter/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response,Model model) {
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        String helpid = request.getParameter("helpid");
        if(helpid!=null){
            HelpChildrenVo help =  getHelpChildrenService().getWebinfo(Long.valueOf(helpid));
            model.addAttribute("helpchildren", help);
            model.addAttribute("help", help);
        }else{
            HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
            HelpChildrenVo helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            model.addAttribute("helpchildren", helpchildren);
            model.addAttribute("help", helpchildren);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
      return "/app/luckykids/personalCenter/profileEdit";
  }

    /**
     * 编辑页面1
     */
//    @RequestMapping("/personalCenter/edit1")
    public String edit1(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            String userid = request.getParameter("userid");
            if (userid == null) {
                userid = request.getSession().getAttribute(SysConstants.USER_ID) == null ? null
                    : request.getSession().getAttribute(SysConstants.USER_ID).toString();
            }
            Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
            model.addAttribute("user", user);
            String helpid = request.getParameter("helpid");
            if (helpid != null) {
                HelpChildrenVo help = getHelpChildrenService().getWebinfo(Long.valueOf(helpid));
                model.addAttribute("helpchildren", help);
                model.addAttribute("help", help);
            } else {
                HelpChildren help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                HelpChildrenVo helpchildren = getHelpChildrenService().getWebinfo(help.getId());
                model.addAttribute("helpchildren", helpchildren);
                model.addAttribute("help", helpchildren);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/app/luckykids/personalCenter/profileEditCollectionAccount";
    }
    
    /**
     * 修改密码页面
     */
//    @RequestMapping("/password")
    public String password(HttpServletRequest request,HttpServletResponse response,Model model) {
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
            userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        String helpid = request.getParameter("helpid");
        if(helpid!=null){
            HelpChildrenVo help =  getHelpChildrenService().getWebinfo(Long.valueOf(helpid));
            model.addAttribute("helpchildren", help);
            model.addAttribute("help", help);
        }else{
            HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
            HelpChildrenVo helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            model.addAttribute("helpchildren", helpchildren);
            model.addAttribute("help", helpchildren);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
      return "/app/luckykids/personalCenter/password";
  
    }
    
    /**
     * 积分规则
     */
//    @RequestMapping("/pointsRule")
    public String pointsRule(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/relationship/pointsRule";
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        String helpchildrenid = request.getParameter("helpchildrenid");
        HelpChildrenVo helpchildren = new HelpChildrenVo();
            if(helpchildrenid!=null){
                helpchildren=  getHelpChildrenService().getWebinfo(Long.valueOf(helpchildrenid));
            }else{
                HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                 helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            }
            model.addAttribute("helpchildren", helpchildren);
           
            
            Authentication user = getAuthenticationService().getByUserid(userid==null?helpchildren.getUser().getId():(Long.valueOf(userid)));
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 帮助的小朋友
     */
//    @RequestMapping("/helpedKids")
    public String helpedKids(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/relationship/helpedKids";
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        String helpchildrenid = request.getParameter("helpchildrenid");
        HelpChildrenVo helpchildren = new HelpChildrenVo();
            if(helpchildrenid!=null){
                helpchildren=  getHelpChildrenService().getWebinfo(Long.valueOf(helpchildrenid));
            }else{
                HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                 helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            }
            model.addAttribute("helpchildren", helpchildren);
           
            
            Authentication user = getAuthenticationService().getByUserid(userid==null?helpchildren.getUser().getId():(Long.valueOf(userid)));
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 收款账户
     */
//    @RequestMapping("/accountsReceivable")
    public String accountsReceivable(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/relationship/accountsReceivable";
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        String helpchildrenid = request.getParameter("helpchildrenid");
        HelpChildrenVo helpchildren = new HelpChildrenVo();
            if(helpchildrenid!=null){
                helpchildren=  getHelpChildrenService().getWebinfo(Long.valueOf(helpchildrenid));
            }else{
                HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                 helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            }
            model.addAttribute("helpchildren", helpchildren);
           
            
            Authentication user = getAuthenticationService().getByUserid(userid==null?helpchildren.getUser().getId():(Long.valueOf(userid)));
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 打赏的小朋友
     */
//    @RequestMapping("/rewardedPainter")
    public String rewardedPainter(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/relationship/rewardedPainter";
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        String helpchildrenid = request.getParameter("helpchildrenid");
        HelpChildrenVo helpchildren = new HelpChildrenVo();
            if(helpchildrenid!=null){
                helpchildren=  getHelpChildrenService().getWebinfo(Long.valueOf(helpchildrenid));
            }else{
                HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                 helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            }
            model.addAttribute("helpchildren", helpchildren);
           
            
            Authentication user = getAuthenticationService().getByUserid(userid==null?helpchildren.getUser().getId():(Long.valueOf(userid)));
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    
    /**
     * 打赏我的人
     */
//    @RequestMapping("/ireward")
    public String ireward(HttpServletRequest request,HttpServletResponse response,Model model) {
        String path="/app/luckykids/personalCenter/relationship/ireward";
        try{
        String userid = request.getParameter("userid");
        if(userid==null){
             userid =request.getSession().getAttribute(SysConstants.USER_ID)==null?null:request.getSession().getAttribute(SysConstants.USER_ID).toString();
        }
        String helpchildrenid = request.getParameter("helpchildrenid");
        HelpChildrenVo helpchildren = new HelpChildrenVo();
            if(helpchildrenid!=null){
                helpchildren=  getHelpChildrenService().getWebinfo(Long.valueOf(helpchildrenid));
            }else{
                HelpChildren  help = getHelpChildrenService().getByUserid(Long.valueOf(userid));
                 helpchildren =  getHelpChildrenService().getWebinfo(help.getId());
            }
            model.addAttribute("helpchildren", helpchildren);
           
            
            Authentication user = getAuthenticationService().getByUserid(userid==null?helpchildren.getUser().getId():(Long.valueOf(userid)));
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    
}
