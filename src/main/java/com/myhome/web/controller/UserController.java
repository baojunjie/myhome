package com.myhome.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.entity.Authentication;
import com.myhome.entity.Region;
import com.myhome.entity.User;
import com.myhome.entity.vo.UserInfoVO;
import com.myhome.utils.DateUtil;
import com.myhome.utils.SysConstants;



@Controller
@RequestMapping(value ="/user", produces = "text/html;charset=UTF-8")
public class UserController extends AbstractController {

    





    @RequestMapping("/get")
    public User get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/add")
    public String add(HttpServletRequest request,HttpServletResponse response,UserInfoVO userinfo) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String path="";
        try{
            getUserInfoService().editInfo(userid,userinfo);
            path ="redirect:/user/personalCenter/profile.do";
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("user") User user) {
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
     * 进入用户个人中心申请认证界面
     * @param id
     * @return
     */
    @RequestMapping("/personalCenter/auth")
    public String auth(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
       return"/app/user/personalCenter/auth";
    }
    /**
     * 进入用户个人中心详情资料界面
     * @param id
     * @return
     */
    @RequestMapping("/personalCenter/profile")
    public String profile(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid;
        if(request.getParameter("userId")!=null){
        	 userid = Integer.valueOf(request.getParameter("userId"));
        }else if(request.getParameter("userid")!=null){
        	 userid = Integer.valueOf(request.getParameter("userid"));
        }else{
        	 userid = Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        }
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
       return"/app/user/personalCenter/profile";
    }
    
    /**
     * 进入用户个人中心详情资料编辑界面
     * @param id
     * @return
     */
    @RequestMapping("/personalCenter/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
        if( user.getUser().getUserInfo().getRegion()!=null){
            model.addAttribute("region", user.getUser().getUserInfo().getRegion().getId());
            if(user.getUser().getUserInfo().getRegion().getLevel() == 1){
            	model.addAttribute("city", user.getUser().getUserInfo().getRegion().getParent().getId());
            }
            Region region = getRegionService().get(user.getUser().getUserInfo().getRegion().getParent().getId());
            model.addAttribute("province",region.getParent().getId());
            }
        
       return"/app/user/personalCenter/profileEdit";
    }
    /**
     *修改密码
     * @param id
     * @return
     */
    @RequestMapping("/personalCenter/password")
    public String password(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
        if( user.getUser().getUserInfo().getRegion()!=null){
            model.addAttribute("region", user.getUser().getUserInfo().getRegion().getId());
            model.addAttribute("city", user.getUser().getUserInfo().getRegion().getParent().getId());
            Region region = getRegionService().get(user.getUser().getUserInfo().getRegion().getParent().getId());
            model.addAttribute("province",region.getParent().getId());
            }
       return"/app/user/personalCenter/password";
    }

    /**
     * 帮助的小朋友
     * @param id
     * @return
     */
    @RequestMapping("/helpedKids")
    public String helpedKids(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
       return"/app/user/personalCenter/relationship/helpedKids";
    }
    /**
     * 积分
     * @param id
     * @return
     */
    @RequestMapping("/pointsRule")
    public String pointsRule(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
       return"/app/user/personalCenter/relationship/pointsRule";
    }
    /**
     * 打赏的小朋友
     * @param id
     * @return
     */
    @RequestMapping("/rewardedPainter")
    public String rewardedPainter(HttpServletRequest request,HttpServletResponse response,Model model) {
        Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        if(user.getUser().getUserInfo().getBirthday()!=null){
            model.addAttribute("age", DateUtil.getage(user.getUser().getUserInfo().getBirthday()));
        }
       return"/app/user/personalCenter/relationship/rewardedPainter";
    }
}
