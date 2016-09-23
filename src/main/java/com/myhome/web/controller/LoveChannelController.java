package com.myhome.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Activity;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.ReturnData;

@Controller
@RequestMapping(value ="/love", produces = "text/html;charset=UTF-8")
public class LoveChannelController extends AbstractController{
	private static Logger logger = LoggerFactory.getLogger(ActivityController.class);
    /**
     * 进入申请页面
     * @param id
     * @return
     */
//    @RequestMapping("/index")
//    public String index() {
//      return"/app/lovechannel/lovechannel";
//    }
    
    	 @RequestMapping("/index")
    	    public String activity(HttpServletRequest request, Model model, HttpServletResponse response) {
    	        try {
    	            List<Activity> list = getActivityService().getIActivity();
    	            if (CommonUtils.isNotEmpty(list)) {
    	                for (int i = 0; i < list.size(); i++) {
    	                    Date date = list.get(i).getTime();
    	                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
    	                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
    	                    SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
    	                    SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy");
    	                    //这样处理更合适一些，可以避免一些潜在Timestamp 问题
    	                    list.get(i).setMonth(sdf1.format(date));
    	                    list.get(i).setDate(sdf2.format(date));
    	                    list.get(i).setHour(sdf3.format(date));
    	                    if (i != 0
    	                        && Integer.parseInt(sdf4.format(date)) < Integer.parseInt(sdf4.format(list
    	                            .get(i - 1).getTime()))) {
    	                        list.get(i).setYear(sdf4.format(list.get(i - 1).getTime()));
    	                    }
    	                    if(i+1==list.size()){
    	                        list.get(i).setLast(sdf4.format(date));
    	                    }
    	                }
    	                model.addAttribute("list", list);
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	        return"/app/lovechannel/lovechannel";
    	    }
}
