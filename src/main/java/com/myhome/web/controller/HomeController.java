package com.myhome.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myhome.entity.Page;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.entity.vo.RegionSort;
import com.myhome.utils.CommonUtils;


@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public String index(Model model,HttpServletRequest request) {
	        try {
	        	List<Map<String, Object>> cityList = super.getWorksService().getCitySort();
	        	List<RegionSort> list = new ArrayList<RegionSort>();
	        	for(int i=0;i<cityList.size();i++){
	        		list.add(new RegionSort(Integer.valueOf(cityList.get(i).get("count").toString()),
	        							cityList.get(i).get("name").toString().replace("市", ""),
	        							Integer.valueOf(cityList.get(i).get("id").toString())));
	        	}
	            if(CommonUtils.isNotEmpty(list)){
	            	model.addAttribute("citylist", list.size()>10?list.subList(0, 10):list);
	            	model.addAttribute("citycount",list.size()>10?10:list.size());
	            }
	            List<ArtistModel> artistList_boy  = getWorksService().getBoyList();
	            if(CommonUtils.isNotEmpty(artistList_boy)){
	    			model.addAttribute("artistListBoy", artistList_boy);//孩子  男  票数前6
	    		}
	    		List<ArtistModel> artistList_girl  = getWorksService().getGirlList();
	    		if(CommonUtils.isNotEmpty(artistList_girl)){
	    			model.addAttribute("artistListGirl", artistList_girl);//孩子  女  票数前6
	    		}
	    		
	    		//获取首页banner链接地址 3为pc端banner
//	    		  List<Page> page = getPageService().get(3);
	    		  List<Page> page = getPageService().getByType(3);
	    		  if(page!=null && page.size()>0){
	    			  model.addAttribute("page", page);
	    		  }
	    		  //入围画家入围作品
	    		  List<Map<String, Object>> PassArtist = getArtistService().findPassArtist();
	    		  if(CommonUtils.isNotEmpty(PassArtist)){
	    			  model.addAttribute("PassArtist", PassArtist);
	    		  }
	    		  List<WorksTagItem> PassWorks = getWorksService().findPassWorks();
	    		  if(CommonUtils.isNotEmpty(PassWorks)){
	    			  model.addAttribute("PassWorks", PassWorks);
	    		  }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    return "/app/home/index";
	}
	
    @RequestMapping(value = "/designForThePeople", produces = "text/html;charset=UTF-8")
    public String toDesignForThePeople(Model model, HttpServletRequest request) {
        return "/app/competition/designForThePeople";
    }

    @RequestMapping(value = "/poeticdays", produces = "text/html;charset=UTF-8")
    public String toPoeticdays(Model model, HttpServletRequest request) {
        return "/app/competition/poeticdays";
    }
	@RequestMapping(value = "/createindex", produces = "text/html;charset=UTF-8")
    public String createindex(Model model,HttpServletRequest request) {
	    return "/app/site/soon";
	}
	@RequestMapping(value = "/aboutUs", produces = "text/html;charset=UTF-8")
    public String aboutUs(Model model,HttpServletRequest request) {
        return "/app/home/aboutUs";
    }
	@RequestMapping(value = "/wrong404", produces = "text/html;charset=UTF-8")
    public String wrong404(Model model,HttpServletRequest request) {
        return "/app/site/404";
    }
	@RequestMapping(value = "/wrong500", produces = "text/html;charset=UTF-8")
    public String wrong500(Model model,HttpServletRequest request) {
        return "/app/site/500";
    }
	    
    /**
    * 进入往届回顾页面 2.1新增
    * @param request
    * @return
    */
    @RequestMapping("/pastReview")
    public String pastReview(HttpServletRequest request, HttpServletResponse response) {
        //TODO 
        return "/app/competition/pastReview";
    }

}
