package com.myhome.html5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Page;
import com.myhome.utils.ReturnData;

@Controller(value = "H5PageController")
@RequestMapping(value = "/H5/page", produces = "text/html;charset=UTF-8")
public class PageController extends AbstractController {

	/**
	 * get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/get")
	@ResponseBody
	public String getPageList(HttpServletRequest request,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		// action
		// List<Page> pageList=super.getPageService().get(1);
		// 轮播图

		try {
			List<Page> picList = super.getPageService().get(2);
			json.put("picList", picList);
		} catch (Exception e) {
			e.printStackTrace();
			returnData.setMsg("查询失败");
			returnData.setReturnCode("1002");
            returnData.setSuccess(false);
		}
		returnData.setData(json);
		return returnData.toString();

	}

	/**
	 * 公益活动 get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/getXCompetition")
	public String get() {

		return "/publishh5/views/app/activity/competition";

	}

	/**
	 * 排行榜 get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/getRanking")
	public String getRanking() {

		return "/publishh5/views/app/activity/ranking";

	}

	/**
	 * 关于大赛 get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/aboutCompetition")
	public String aboutCompetition() {

		return "/publishh5/views/app/activity/aboutCompetition";

	}
	
	/**
	 * 关于大赛 IOS
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/aboutCompetitionIOS")
	public String aboutCompetitionISO() {

		return "/publishh5/views/app/activity/aboutCompetitionIOS";

	}

	/**
	 * 关于我们 get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/aboutUs")
	public String aboutUs() {

		return "/publishh5/views/app/activity/aboutUs";

	}

	/**
	 * 参赛须知 get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/notice")
	public String notice() {

		return "/publishh5/views/app/activity/notice";

	}

	/**
	 * 服务协议 get
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/servicesAgreement")
	public String servicesAgreement() {

		return "/publishh5/views/app/activity/servicesAgreement";

	}

	/**
	 * 赛区活动
	 * 
	 * @author gwb
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/activity")
	public String activity() {

		return "/publishh5/views/app/activity/activity";

	}

	/**
	 * 微信二维码
	 * 
	 * @author gwb
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/download")
	public String download() {

		return "/publishh5/views/app/download/download";

	}

	/**
	 * 诗意的日志
	 * 
	 * @author gwb
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/poetry")
	public String poetry() {

		return "/publishh5/views/app/activity/poetry";

	}

	/**
	 * 为爱设计
	 * 
	 * @author gwb
	 * @return 2015年9月25日 下午5:13:33
	 */
	@RequestMapping("/designForThePeople")
	public String designForThePeople() {

		return "/publishh5/views/app/activity/designForThePeople";

	}

	
	/**
     * 调查问卷协议
     * 
     * @author gwb
     * @return 2015年9月25日 下午5:13:33
     */
    @RequestMapping("/questionnaireRules")
    public String questionnaireRules() {

        return "/publishh5/views/app/activity/questionnaireRules";

    }
}
