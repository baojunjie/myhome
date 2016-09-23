package com.myhome.web.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Comment;
import com.myhome.entity.User;
import com.myhome.entity.Works;
import com.myhome.entity.vo.ReturnComment;
import com.myhome.entity.vo.ReturnCommentInfo;
import com.myhome.utils.SysConstants;



@Controller
@RequestMapping(value ="/comment", produces = "text/html;charset=UTF-8")
public class CommentController extends AbstractController {

    

    



    @RequestMapping("/get")
    public Comment get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    /**
     * 添加评论
     * @param 
     */
    @ResponseBody
    @RequestMapping(value = "/interceptorAdd", produces = "text/html;charset=UTF-8")
    public String Add(HttpServletRequest request,HttpServletResponse response) {
        ReturnComment data = new ReturnComment();
            ReturnCommentInfo result = new ReturnCommentInfo();
            response.addHeader("Access-Control-Allow-Origin", "*");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
           try{
            long works_id =Long.valueOf(request.getParameter("worksid"));
            String words =request.getParameter("words");
            if(request.getSession().getAttribute(SysConstants.USER_ID)!=null){
            long user_id=Long.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        	User user = getUserService().get(user_id);
        	Works works = getWorksService().get(works_id);
        	Comment comment = new Comment();
        	comment.setUser(user);
        	comment.setWorks(works);
        	comment.setWords(words);
        	comment.setCreatedDatetime(Timestamp.valueOf(sdf.format(new Date())));
        	getCommentService().add(comment);
        	works.setCommentNum(works.getCommentNum()==null?1:works.getCommentNum()+1);  //作品投票在作品 投票数上+1
        	getWorksService().update(works);
         	result.setComment(comment);
        	data.setResult(result);
            }else{
                data.setSuccess(false);
                data.setMsg("您还没有登录哦！");
                data.setCode(1);
            }
           }catch(Exception e){
               e.printStackTrace();
               data.setSuccess(false);
           }
            return data.toJSon(data);
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("comment") Comment comment) {
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
    
    @RequestMapping("/getlist")
    @ResponseBody
    public String  getlist(HttpServletRequest request,HttpServletResponse response) {
        ReturnComment data = new ReturnComment();
        ReturnCommentInfo result = new ReturnCommentInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try{
        long worksid =Long.valueOf(request.getParameter("worksid"));
        List<Comment> list=getCommentService().getByWorksid(worksid);
        result.setList(list);
        data.setResult(result);
        }catch(Exception e){
            e.printStackTrace();
            data.setSuccess(false);
        }
        return data.toJSon(data);
    } 
    
    

}
