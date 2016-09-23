package com.myhome.web.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Parameter;
import com.myhome.utils.ReturnData;

@Controller(value = "systemParameterController")
@RequestMapping(value = "/system/parameter", produces = "text/html;charset=UTF-8")
public class ParameterController extends AbstractController {

    /**
     * get
     * 
     * @author ywz
     * @param id
     * @return 2015年9月25日 下午5:13:33
     */
    @RequestMapping("/getParameter")
    @ResponseBody
    public String getAdvertiseList(HttpServletRequest request, HttpServletResponse response,Parameter parameter) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();

        try {
            Parameter pa = super.getParameterService().get(parameter.getId());
            json.put("pa", pa);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("查询失败");
            returnData.setSuccess(false);
            returnData.setReturnCode("1002");
        }
        returnData.setData(json);
        return returnData.toString();

    }
//
//    /**
//     * add
//     * 
//     * @author ywz
//     * @param id
//     * @return 2015年9月25日 下午5:13:33
//     */
//    @RequestMapping("/addAdvertise")
//    @ResponseBody
//    public String addAdvertise(HttpServletRequest request, HttpServletResponse response,
//                               Advertise advertise) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        ReturnData returnData = new ReturnData();
//        JSONObject json = new JSONObject();
//
//        try {
//            Advertise ad = new Advertise();
//            ad.setPath(advertise.getPath());
//            ad.setTitle(advertise.getTitle());
//            ad.setUrl(advertise.getUrl());
//            super.getAdvertiseService().add(ad);
//        } catch (Exception e) {
//            e.printStackTrace();
//            returnData.setMsg("新增失败");
//            returnData.setSuccess(false);
//            returnData.setReturnCode("1002");
//        }
//        returnData.setData(json);
//        return returnData.toString();
//
//    }
//
//    /**
//     * remove
//     * 
//     * @author ywz
//     * @param id
//     * @return 2015年9月25日 下午5:13:33
//     */
//    @RequestMapping("/removeAdvertise")
//    @ResponseBody
//    public String removeAdvertise(HttpServletRequest request, HttpServletResponse response, long id) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        ReturnData returnData = new ReturnData();
//        JSONObject json = new JSONObject();
//
//        try {
//            Advertise advertise = super.getAdvertiseService().get(id);
//            advertise.setInvalid(true);
//            int flag = super.getAdvertiseService().update(advertise);
//            if (flag > 0) {
//                returnData.setMsg("删除成功");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            returnData.setMsg("删除失败");
//            returnData.setSuccess(false);
//            returnData.setReturnCode("1002");
//        }
//        returnData.setData(json);
//        return returnData.toString();
//
//    }

    /**
     * update
     * 
     * @author ywz
     * @param id
     * @return 2015年9月25日 下午5:13:33
     */
    @RequestMapping("/updateParameter")
    @ResponseBody
    public String updateParameter(HttpServletRequest request, HttpServletResponse response,
                                  Parameter parameter) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();

        try {
            Parameter pa = super.getParameterService().get(parameter.getId());
            // 修改内容
            pa.setValue(parameter.getValue());
            pa.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
            int flag = super.getParameterService().update(pa);
            if (flag > 0) {
                returnData.setMsg("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("更新失败");
            returnData.setSuccess(false);
            returnData.setReturnCode("1002");
        }
        returnData.setData(json);
        return returnData.toString();

    }
}
