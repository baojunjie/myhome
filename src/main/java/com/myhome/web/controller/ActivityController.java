package com.myhome.web.controller;

import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Works;
import com.myhome.utils.ReturnData;
/**
 * 关于参赛活动
 * @author lqf
 *
 */
@Controller
@RequestMapping(value = "/activity", produces = "text/html;charset=UTF-8")
public class ActivityController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(ActivityController.class);
	  /**
     * 获取账户
     * @author lqf
     */
    @RequestMapping(value = "/index")
//    @ResponseBody
    public String index(HttpServletResponse response,HttpServletRequest request) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        try {
        	//根据入围等级获取作品
//       	Works  works =  getWorksService().getPassWorksByLevel();
        	
        } catch (Exception e) {
            logger.error("查询 失败", e);
            returnData.setMsg("");
            returnData.setSuccess(false);
        }
        return returnData.toString();
    }
    /**
     * 
     * @description
     * @author lqf
     * @date 2016年3月14日
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/load")
    @ResponseBody
    public String load(HttpServletResponse response,HttpServletRequest request) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        try {
        	
        } catch (Exception e) {
            logger.error("查询 失败", e);
            returnData.setMsg("");
            returnData.setSuccess(false);
        }
        return returnData.toString();
    }
}
