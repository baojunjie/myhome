package com.myhome.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Artist;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.SearchArtist;
import com.myhome.entity.vo.SearchArtistInfo;
import com.myhome.entity.vo.SearchWorks;
import com.myhome.entity.vo.SearchWorksInfo;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ReturnData;



@Controller
@RequestMapping(value ="/search", produces = "text/html;charset=UTF-8")
public class SearchController extends AbstractController {

	public static void main(String[] args) throws ParseException  {
	}
	/**
	 * 搜索
	 */
	 @RequestMapping("/artist")
	 @ResponseBody
	 public String artist(HttpServletRequest request,HttpServletResponse response){
		 String keywords = request.getParameter("keywords");
		 String index = request.getParameter("index");
		 String size = request.getParameter("size");
		 response.addHeader("Access-Control-Allow-Origin", "*");
		 SearchArtist data = new SearchArtist();
		 try{
			SearchArtistInfo model = new SearchArtistInfo();
			List<Artist> artistlist = getArtistService().findByKeywords(keywords!=null?keywords.trim():null,index,size);
			model.setList(artistlist);
			model.setTotal(getArtistService().countByKeywords(keywords!=null?keywords.trim():null));
			data.setResult(model);
		} catch (Exception e) {
			e.printStackTrace();
			data.setSuccess(false);
		}
		 return data.toJSon(data);
		 
	 }
		/**
		 * 搜索
		 */
		 @RequestMapping("/works")
		 @ResponseBody
		 public String works(HttpServletRequest request,HttpServletResponse response){
			 String keywords = request.getParameter("keywords");
			 String index = request.getParameter("index");
			 String size = request.getParameter("size");
			 response.addHeader("Access-Control-Allow-Origin", "*");
			 SearchWorks data = new SearchWorks();
			 try{
				 SearchWorksInfo model = new SearchWorksInfo();
				List<WorksTagItem> worklist = getWorksService().findByKeywords(keywords!=null?keywords.trim():null,index,size);
				model.setTotal(getWorksService().countByKeywords(keywords!=null?keywords.trim():null));
				model.setList(worklist);
				data.setResult(model);
			} catch (Exception e) {
				e.printStackTrace();
				data.setSuccess(false);
			}
			 return data.toJSon(data);
			 
		 }
	 /**
	  * 推荐作品
	  */
	 @RequestMapping("/recommendWorks")
	 @ResponseBody
	 public String recommendWorks(HttpServletRequest request,HttpServletResponse response){
		 String size = request.getParameter("size");
		 response.addHeader("Access-Control-Allow-Origin", "*");
		ReturnData data=new ReturnData();
		JSONObject json=new JSONObject();
		 try{
				List<WorksTagItem> worklist = getWorksService().findList(1,Integer.valueOf(size));
				json.put("list", worklist);
			    data.setData(json);
		} catch (Exception e) {
			e.printStackTrace();
			data.setSuccess(false);
		}
		 return data.toJSon(data);
	 }
	 /**
	  * 推荐作品
	  */
	 @RequestMapping("/index")
	 public String index(HttpServletRequest request,HttpServletResponse response){
		 response.addHeader("Access-Control-Allow-Origin", "*");
		 return "/app/search/index";
	 }
	
}
