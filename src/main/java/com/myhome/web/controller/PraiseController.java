package com.myhome.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistPraise;
import com.myhome.entity.Praise;
import com.myhome.entity.User;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPraise;
import com.myhome.utils.CommonUtils;



@Controller
@RequestMapping("/praise")
public class PraiseController extends AbstractController {

    

    



    @RequestMapping("/get")
    public Praise get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    /**
     * 作品点赞
     * @param voting
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    public String add(@RequestParam("works_id") Long works_id,@RequestParam("user_id") Long user_id) {
    	//long works_id = 1;//页面传过来
    	//long user_id=1;//session中获得
    	//1.判断该用户有没有投过票
    	List<WorksPraise> workspraiselist =  getPraiseService().getByUserAndWorks(works_id,user_id);
    	if(CommonUtils.isEmpty(workspraiselist)){
    		//2.没有投过
        	User user = new User();
        	user.setId(user_id);
        	Works works = getWorksService().get(works_id);
        	WorksPraise workpraise = new WorksPraise();
        	workpraise.setUser(user);
        	workpraise.setWorks(works);
        	getPraiseService().add(workpraise);
        	works.setPraise(works.getPraise()==null?1:works.getPraise()+1);  //作品投票在作品 投票数上+1
        	getWorksService().update(works);
        	//作品的票数加1
    	}
    	return "我的家";
    }
    /**
     * 人物点赞
     * @param voting
     */
    @ResponseBody
    @RequestMapping(value = "/addman", produces = "text/html;charset=UTF-8")
    public String addman(@RequestParam("artist_id") Long artist_id,@RequestParam("user_id") Long user_id) {
    	//long works_id = 1;//页面传过来
    	//long user_id=1;//session中获得
    	//1.判断该用户有没有投过票
    	List<ArtistPraise> artistpraiselist =  getPraiseService().getByUserAndArtist(artist_id,user_id);
    	if(CommonUtils.isEmpty(artistpraiselist)){
    		//2.没有投过
        	User user = new User();
        	user.setId(user_id);
//        	Works works = getWorksService().get(works_id);
        	Artist artist = new Artist();
        	artist.setId(artist_id);
        	ArtistPraise artistpraise = new ArtistPraise();
        	artistpraise.setUser(user);
        	artistpraise.setArtist(artist);
        	getPraiseService().add(artistpraise);
//        	works.setPraise(works.getVotenum()==null?1:works.getVotenum()+1);  //需要在人物投票数量+1，目前没有
//        	getWorksService().update(works);
        	//作品的票数加1
    	}
    	return "我的家";
    }
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("praise") Praise praise) {
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
    
    
    

}
