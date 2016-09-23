package com.myhome.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Parameter;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.PassArtist;
import com.myhome.entity.vo.PassArtistInfo;
import com.myhome.entity.vo.SearchWorks;
import com.myhome.entity.vo.SearchWorksInfo;

/**
 * 关于入围作品接口
 * @author lqf
 *
 */
@Controller
@RequestMapping(value = "/passWork", produces = "text/html;charset=UTF-8")
public class PassWorksController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(PassWorksController.class);

    /**
    * 获取账户
    * @author lqf
    */
    @RequestMapping(value = "/index")
    public String index(HttpServletResponse response, HttpServletRequest request, Model model) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Parameter paranmeter = getParameterService().get(1L);
        model.addAttribute("newType",
            paranmeter.getValue().equals("0") ? "100" : paranmeter.getValue());
        return "/app/pass/rankingList";
    }

    /**
     * 
     * @description加载入围作品
     * @author lqf
     * @date 2016年3月15日
     * @param response
     * @param request
     * @param type 评选级别
     * @return
     */
    @RequestMapping(value = "/loadWorks")
    @ResponseBody
    public String loadWorks(HttpServletResponse response, HttpServletRequest request) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        SearchWorks data = new SearchWorks();
        String index = request.getParameter("index");
        String size = request.getParameter("size");
        String type = request.getParameter("type");
        String age = request.getParameter("age");
        String awards=request.getParameter("awards");
        try {
            SearchWorksInfo model = new SearchWorksInfo();
            List<WorksTagItem> list = getWorksService().findListByType(Integer.valueOf(type), size,
                index, null, age,awards);
            model.setList(list);
            model.setTotal(getWorksService().findCountByType(Integer.valueOf(type), age,awards));
            data.setResult(model);
        } catch (Exception e) {
            logger.error("查询 失败", e);
            data.setSuccess(false);
        }
        return data.toJSon(data);
    }

    /**
     * 
     * @description加载入围小画家
     * @author lqf
     * @date 2016年3月15日
     * @param response
     * @param request
     * @param type
     * @return
     */
    @RequestMapping(value = "/loadArtist")
    @ResponseBody
    public String loadArtist(HttpServletResponse response, HttpServletRequest request, int size,
                             int index, String type, String age,String awards) {
        response.addHeader("Access-Control-Allow-Origin", "*");

        PassArtist data = new PassArtist();
        try {
            PassArtistInfo model = new PassArtistInfo();
            switch (type) {
                case "0":
                    type = "(0,1,2,3,4,5,6,7,8,9,10)";
                    break;
                case "1":
                    type = "(1,2,3,4,5,6,7,8,9,10)";
                    break;
                case "2":
                    type = "(2,3,4,5,6,7,8,9,10)";
                    break;
                case "3":
                    type = "(3,4,5,6,7,8,9,10)";
                    break;
                case "4":
                    type = "(4,5,6,7,8,9,10)";
                    break;
                case "5":
                    type = "(5,6,7,8,9,10)";
                    break;
                case "6":
                    type = "(6,7,8,9,10)";
                    break;
                case "7":
                    type = "(7,8,9,10)";
                    break;
                case "8":
                    type = "(8,9,10)";
                    break;
                case "9":
                    type = "(9,10)";
                    break;
                case "10":
                    type = "(10)";
                    break;
            }
            if(age==null){
                age="";
            }
            switch (age) {
                case "":
                    age = "(3,4,5,6,7,8,9,10,11,12)";
                    break;
                case "1":
                    age = "(3,4,5,6)";
                    break;
                case "2":
                    age = "(7,8,9)";
                    break;
                case "3":
                    age = "(10,11,12)";
                    break;
                default:
                    age = "";
                    break;
            }
            List<Map<String, Object>> list = getArtistService().findListByWorksType(type, age,
                size, index,awards);
            model.setList(list);
            model.setTotal(getArtistService().findCountByWorksType(type, age,awards));
            data.setResult(model);
        } catch (Exception e) {
            logger.error("查询 失败", e);
            data.setSuccess(false);
        }
        return data.toJSon(data);
    }
}
