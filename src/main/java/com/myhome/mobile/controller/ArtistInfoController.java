package com.myhome.mobile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Region;

@Controller(value = "mobileArtistInfoController")
@RequestMapping(value = "/mobile/artist/info", produces = "text/html;charset=UTF-8")
public class ArtistInfoController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(ArtistInfoController.class);

	/**
	 * test addCompetition
	 * 
	 * @author gwb
	 * @param id
	 * @return 2015年9月16日 上午9:21:02
	 */
	@RequestMapping("/addCompetition")
	@ResponseBody
	public String addCompetition(@RequestParam("id") Long id) {
		ArtistInfo artistInfo = new ArtistInfo();
		Artist artist = new Artist();
		artistInfo.setId(id);
		artistInfo.setArtist(artist);
		artistInfo.setAge(123);
		Region re = new Region();
		re.setId(2l);
		artistInfo.setRegion(re);
		try {
	        super.getArtistInfoService().addArtistInfoMobil(artistInfo);
        } catch (Exception e) {
	        
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        logger.error("我要参赛失败", e.getMessage());
        }
		return null;
	}

	// /**
	// * app
	// * 我要参赛
	// * add
	// * @author gwb
	// * @param artistInfo
	// * @return
	// * 2015年9月10日 下午3:27:00
	// */
	// @SuppressWarnings("unused")
	// @RequestMapping("/addCompetition")
	// @ResponseBody
	// public String add(@ModelAttribute("artistInfo") ArtistInfo
	// artistInfo,HttpServletRequest request, HttpServletResponse response,
	// Authentication authentication) {
	// ReturnData returnData = new ReturnData();
	// JSONObject json = new JSONObject();
	// /**
	// * 填写作者信息
	// */
	// String img=request.getParameter("img");
	// String name=request.getParameter("name");
	// String male=request.getParameter("male");//性别
	// String nation=request.getParameter("nation");//名族
	// String zodiac=request.getParameter("zodiac");//生肖
	// String constellation=request.getParameter("constellation");//星座
	// Date birthday=Date.valueOf(request.getParameter("birthday"));
	// String idCode=request.getParameter("idCode");
	// Long region_id=Long.parseLong(request.getParameter("region_id"));//城市
	// String school=request.getParameter("school");//学校培训机构
	// String parentMobile=request.getParameter("parentMobile");
	//
	// /**
	// * 填写推荐方信息
	// */
	//
	// String school1=request.getParameter("referrerSchool");
	// String schoolMobile=request.getParameter("referrerMobil");
	//
	// String teacher=request.getParameter("teacher");
	// String teacherMobile=request.getParameter("teacherMobile");
	//
	// String instructor=request.getParameter("instructor");
	// String instructorMobile=request.getParameter("instructorMobile");
	//
	//
	// /**
	// * 填写作品信息
	// */
	// //作品类别
	// Long tag_id=Long.parseLong(request.getParameter("tag_id"));
	// //作品名称
	// String work_name=request.getParameter("work_name");
	// //作品描述
	// String description=request.getParameter("description");
	// //上传作品
	// String path=request.getParameter("path");
	//
	// Artist artist=new Artist();
	// Region region=new Region();
	// region.setId(region_id);
	// Tag tag=new Tag();
	// tag.setId(tag_id);
	// //TODO 从sesion中获取artist_id
	// //向artistinof表中插入参赛者信息
	// artist.setId(1l);
	// String imgStr= ImageUpload.imageUpload(img);
	// if (null == path || "".equals(path)) {
	// json.put("status", false);
	// returnData.setMsg("上传图片失败");
	// returnData.setData(json);
	// return returnData.toString();
	// }
	//
	// artistInfo.setArtist(artist);
	// artistInfo.setImg(imgStr);
	// artistInfo.setName(name);
	// artistInfo.setMale(false);//TODO
	// artistInfo.setNation(nation);
	// artistInfo.setZodiac(zodiac);
	// artistInfo.setConstellation(constellation);
	// artistInfo.setBirthday(birthday);
	// artistInfo.setIdCode(idCode);
	// artistInfo.setRegion(region);
	// artistInfo.setSchool(school);
	// artistInfo.setParentMobile(parentMobile);
	// Long id=super.getArtistInfoService().addArtistInfoMobil(artistInfo);
	//
	// //向work中插入数据
	// String pathStr= ImageUpload.imageUpload(path);
	// if (null == pathStr || "".equals(pathStr)) {
	// json.put("status", false);
	// returnData.setMsg("上传图片失败");
	// returnData.setData(json);
	// return returnData.toString();
	// }
	//
	// Works works =new Works();
	// works.setArtist(artist);
	// Long workId=super.getWorksService().addMobileWork(works);
	// works.setId(workId);
	//
	// //向t_picture表中国插入数据
	// Picture picture =new Picture();
	// picture.setPath(pathStr);
	// Long picId =super.getPictureService().addPictureMobile(picture);
	// picture.setId(picId);
	//
	// //向WorksPictureItem表中插入数据
	// WorksPictureItem worksPictureItem=new WorksPictureItem();
	// worksPictureItem.setPicture(picture);
	// worksPictureItem.setWorks(works);
	// Long
	// wptId=super.getWorksPictureItemService().addWorksPictureItemMobile(worksPictureItem);
	//
	// //向 t_works_tag_item表中插入数据
	// WorksTagItem worksTagItem=new WorksTagItem();
	// worksTagItem.setWorks(works);
	// worksTagItem.setTag(tag);
	// Long
	// wtiId=super.getWorksTagItemService().addWorksTagItemMobile(worksTagItem);
	//
	// //game
	// Game game=new Game();
	//
	// //GameWorksItem
	// GameWorksItem gameWorksItem=new GameWorksItem();
	// gameWorksItem.setApplicant(new User());
	// gameWorksItem.setGame(game);
	// gameWorksItem.setWorks(works);
	// return returnData.toString();
	// }

}

