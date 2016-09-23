package com.myhome.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Region;
import com.myhome.entity.vo.ReturnRegion;
import com.myhome.entity.vo.ReturnRegionInfo;



@Controller
@RequestMapping(value = "/region", produces = "text/html;charset=UTF-8")
public class RegionController extends AbstractController {

    

    

	/**
	 * 省列表
	 * @return
	 * 2015年9月11日 上午10:01:30
	 */
	@RequestMapping("/getprovience")
	@ResponseBody
	public String getProvinceList(HttpServletResponse response) {
        ReturnRegion data = new ReturnRegion();
        ReturnRegionInfo result = new ReturnRegionInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try{
        List<Region> provincelist=getTagService().getListByLevelAndCode(1,null);//获取省及
        result.setList(provincelist);
        data.setResult(result);
        }catch(Exception e){
            e.printStackTrace();
            data.setSuccess(false);
        }
        return data.toJSon(data);
	}
	/**
	 * 市列表或区级
	 * @return
	 * 2015年9月11日 上午10:01:30
	 */
	@RequestMapping("/getcity")
	@ResponseBody
	public String getcity(HttpServletRequest request,HttpServletResponse response) {
        ReturnRegion data = new ReturnRegion();
        ReturnRegionInfo result = new ReturnRegionInfo();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try{
        String code = request.getParameter("code");
        List<Region> citylist=getTagService().getListByLevelAndCode(null,code);//获取省及
        result.setList(citylist);
        data.setResult(result);
        }catch(Exception e){
            e.printStackTrace();
            data.setSuccess(false);
        }
        return data.toJSon(data);
	}
	@RequestMapping("/get")
    public Region get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/add")
    public void add(@ModelAttribute("region") Region region) {
        // TODO 待完成
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("region") Region region) {
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
