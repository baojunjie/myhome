package com.myhome.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Artist;
import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.WantBuyHouse;
import com.myhome.entity.WantToDecorate;
import com.myhome.entity.vo.WantBuyHouseVo;
import com.myhome.entity.vo.WantToDecorateVo;
import com.myhome.utils.SysConstants;


/**
 * 调查问卷
 * @author lqf
 *
 */
@Controller
@RequestMapping(value ="/question", produces = "text/html;charset=UTF-8")
public class QuestionController extends AbstractController{
    /**
     * 跳转我已装修
     * @param teacher
     * lqf
     */
    @RequestMapping("/toRenovated")
    public String toRenovated(HttpServletRequest request,HttpServletResponse response) {
        return "/app/questionnaire/renovated";
    }
    /**
     * 跳转我要装修
     * @param teacher
     * lqf
     */
    @RequestMapping("/toWantRenovate")
    public String toWantRenovate(HttpServletRequest request,HttpServletResponse response) {
        return "/app/questionnaire/wantRenovate";
    }
    /**
     * 跳转我要买房
     * @param teacher
     * lqf
     */
    @RequestMapping("/toBuyHouse")
    public String toBuyHouse(HttpServletRequest request,HttpServletResponse response) {
        return "/app/questionnaire/buyHouse";
    }
    /**
     * 跳转我要装修
     * @param teacher
     * lqf
     */
    @RequestMapping("/toMybalcony")
    public String toMybalcony(HttpServletRequest request,HttpServletResponse response) {
        return "/app/questionnaire/mybalcony";
    }
    /**
     * 保存问卷信息(是否需要买房)
     * @param teacher
     * lqf
     */
    @RequestMapping("/house")
    @ResponseBody
    public String addHouse(HttpServletRequest request,HttpServletResponse response,WantBuyHouseVo model) {
        Integer userid =137;//Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String path="";
        try{
            WantBuyHouse house = getWantBuyHouseService().saveOrUpdate(userid,model,request);
            path ="redirect:/sponsors/join.do?sponsorsid="+house.getId();
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    /**
     * 保存问卷信息(是否需要买房)
     * @param teacher
     * lqf
     */
    @RequestMapping("/decorate")
    @ResponseBody
    public String addDecorate(HttpServletRequest request,HttpServletResponse response,WantToDecorateVo model) {
        Integer userid =137;//Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        String path="";
        try{
            WantToDecorate decorate = getWantToDecorateService().saveOrUpdate(userid,model,request);
            path ="redirect:/sponsors/join.do?sponsorsid="+decorate.getId();
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }
    
    /**
     * 积分规则
     * @param teacher
     * lqf
     */
    @RequestMapping("/pointsRule")
    public String pointsRule(Model model ,HttpServletRequest request,HttpServletResponse response) {
        String userid = request.getSession().getAttribute(SysConstants.USER_ID).toString();
        String artistid = "";
        Artist artist= new Artist();
            if(userid!=null&&(!"".equals(userid))){
                artist= getArtistService().getByUserid(userid);
                    artistid = artist.getId().toString();
            }
            model.addAttribute("artist",artist);
        model.addAttribute("artistid",artistid);
        Integer total1  = getWorksService().getByArtistid(Long.valueOf(artistid),1);
        Integer total2 = getWorksService().getByArtistid(Long.valueOf(artistid),2);
        Integer total3  = getWorksService().getByArtistid(Long.valueOf(artistid),3);
        Integer total4  = getWorksService().getByArtistid(Long.valueOf(artistid),4);
        model.addAttribute("total1", total1);
        model.addAttribute("total2", total2);
        model.addAttribute("total3", total3);
        model.addAttribute("total4", total4);
        Authentication user = getAuthenticationService().getByUserid(Long.valueOf(userid));
        model.addAttribute("user", user);
        HelpChildren  helpchildren = getHelpChildrenService().getByUserid(user.getUser().getId());
        if(helpchildren==null){
        	model.addAttribute("ifhelpchildren", 0);
         }else if(helpchildren.getStatus()==3){
        	model.addAttribute("ifhelpchildren", 1);
        }else{
        	model.addAttribute("ifhelpchildren", 0);
        }
        return "/app/painter/personalCenter/pointsRule";
    }

}
