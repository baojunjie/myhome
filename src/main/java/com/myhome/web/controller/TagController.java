package com.myhome.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Tag;
import com.myhome.entity.vo.ReturnTag;
import com.myhome.entity.vo.ReturnTagInfo;



@Controller
@RequestMapping(value = "/tag", produces = "text/html;charset=UTF-8")
public class TagController extends AbstractController {

    

    



	/**
	 * 作品类别
	 * getTagList
	 * @author gwb
	 * @return
	 * 2015年9月11日 上午10:01:30
	 */
	@RequestMapping("/getTagList")
	@ResponseBody
	public String getTagList(HttpServletResponse response) {
	    ReturnTag data = new ReturnTag();
	    ReturnTagInfo result = new ReturnTagInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
		try{
		List<Tag> listTag=super.getTagService().getTagList();
		result.setList(listTag);
		data.setResult(result);
		}catch(Exception e){
		    e.printStackTrace();
		    data.setSuccess(false);
		    
		}
		return data.toJSon(data);
	}
	
    @RequestMapping("/get")
    public Tag get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/add")
    public void add(@ModelAttribute("tag") Tag tag) {
        // TODO 待完成
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("tag") Tag tag) {
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
