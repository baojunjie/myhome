package com.myhome.mobile.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.Region;
import com.myhome.entity.Sponsors;
import com.myhome.entity.Teacher;
import com.myhome.entity.User;
import com.myhome.utils.DateUtil;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.ReturnData;
import com.myhome.utils.Tools;
import com.myhome.utils.UtilMD5Encoder;

/**
 * 
 * ClassName: LoginAuthenticationController <br/>
 * Reason: 用户登录认证 <br/>
 * date: 2015年9月8日 下午2:05:13 <br/>
 * 
 */
@Controller(value = "MobileLoginAuthenticationController")
@RequestMapping(value = "/mobile/login/authentication", produces = "text/html;charset=UTF-8")
public class LoginAuthenticationController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(LoginAuthenticationController.class);

	/**
	 * 用户登录 login
	 * 
	 * @author gwb
	 * @param request
	 * @param response
	 * @param authentication
	 * @return 2015年9月8日 上午10:36:22
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public String mobileLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		// 参数验证
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");

		if (null == mobile || "".equals(mobile)) {
			returnData.setReturnCode("1008");
			returnData.setMsg("请输入账号");
			return returnData.toString();
		}
		if (null == password || "".equals(password)) {
			returnData.setReturnCode("1008");
			returnData.setMsg("请输入密码");
			return returnData.toString();
		}
		try {
			// 根据用户名查询salt
			String salt = "";
			authentication.setLogin(mobile);
			Authentication auth1 = super.getAuthenticationService().getMobileAuthenticationByName(authentication);
			if (auth1 != null && auth1.getId() > 0) {
				salt = auth1.getSalt();
			} else {
				returnData.setMsg("该用户不存在");
				returnData.setReturnCode(PublicConstants.GET_ERROR);
				return returnData.toString();
			}
//			auth1.get
			Authentication au = new Authentication();
			au.setLogin(mobile);
			String pwdSalt = UtilMD5Encoder.encodePassword(password, salt);
			au.setPassword(pwdSalt);
			// 根据用户

			if (auth1.getPassword().equals(pwdSalt)) {
                Authentication loginMobile = super.getAuthenticationService()
                        .getByUseridAndType(auth1.getUser().getId(), "2");
				// 普通用户
				if (auth1.getUser().getUserInfo().getType().equals("1")) {
					User user = super.getUserService().getMobileUser(auth1.getUser());
					if (user == null) {
						returnData.setMsg("用户不存在");
						returnData.setReturnCode(PublicConstants.GET_ERROR);
						return returnData.toString();
					}

					if (user.getUserInfo().getRegion() != null) {
						List<Map<String, String>> lsMap = super.getRegionService().getRegion(
						        (long) user.getUserInfo().getRegion().getRegionCode(),
						        user.getUserInfo().getRegion().getLevel());
						json.put("region", lsMap);
					} else {
						json.put("region", null);
					}
                    json.put("loginMobile", loginMobile.getLogin());
					json.put("userInfo", user.getUserInfo());
					json.put("type", user.getUserInfo().getType());
					returnData.setData(json);
				} else if (auth1.getUser().getUserInfo().getType().equals("2")) {// 画家

					ArtistInfo artistInfo = new ArtistInfo();
					artistInfo.setUser(auth1.getUser());
					ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);

					// Map<String, Object>
					// map=super.getArtistService().getArtistMobile(at.getId());
					// at.setStatus(Integer.parseInt(map.get("status")+""));

					Artist artist = super.getArtistService().get(at.getId());
					at.setStatus(artist.getStatus());
					//
					// Map<String, Object> map =
					// super.getAuthenticationService().getUserArtistInfoMobile(
					// auth1.getUser().getId());

					if (at.getRegion() != null) {
						List<Map<String, String>> lsMap = super.getRegionService().getRegion(
						        (long) at.getRegion().getId(), at.getRegion().getLevel());
						json.put("region", lsMap);
					} else {
						json.put("region", null);
					}
                    json.put("loginMobile", loginMobile.getLogin());
					json.put("artist", at);
					json.put("userInfo", auth1.getUser().getUserInfo());
					// json.put("artist", map);
					// at.getr
					json.put("type", auth1.getUser().getUserInfo().getType());
					returnData.setData(json);
				} else if (auth1.getUser().getUserInfo().getType().equals("3")) {// 受捐者
					HelpChildren children = new HelpChildren();
					children.setUser(auth1.getUser());
					HelpChildren ch = super.getHelpChildrenService().getMobileHelpChildren(children);
					if (ch == null) {
						returnData.setMsg("受帮助的小朋友信息不存在");
						returnData.setReturnCode(PublicConstants.GET_ERROR);
						return returnData.toString();
					}
					ch.setUser(null);

					if (ch.getRegion() != null) {
						List<Map<String, String>> lsMap = super.getRegionService().getRegion(
						        (long) ch.getRegion().getId(), ch.getRegion().getLevel());
						json.put("region", lsMap);
					} else {
						json.put("region", null);
					}
                    json.put("loginMobile", loginMobile.getLogin());
					json.put("userInfo", auth1.getUser().getUserInfo());
					json.put("helpChildren", ch);
					json.put("type", auth1.getUser().getUserInfo().getType());
					returnData.setData(json);
				} else if (auth1.getUser().getUserInfo().getType().equals("4")) {// 老师
					Teacher teacher = new Teacher();
					teacher.setUser(auth1.getUser());
					Teacher te = super.getTeacherService().getMobileTeacher(teacher);

					if (te == null) {
						returnData.setMsg("老师信息不存在");
						returnData.setReturnCode(PublicConstants.GET_ERROR);
						return returnData.toString();
					}

					if (te.getRegion() != null) {
						List<Map<String, String>> lsMap = super.getRegionService().getRegion(
						        (long) te.getRegion().getId(), te.getRegion().getLevel());
						json.put("region", lsMap);
					} else {
						json.put("region", null);
					}

					te.setUser(null);
                    json.put("loginMobile", loginMobile.getLogin());
					json.put("userInfo", auth1.getUser().getUserInfo());
					json.put("teacher", te);
					json.put("type", auth1.getUser().getUserInfo().getType());
					returnData.setData(json);
				} else if (auth1.getUser().getUserInfo().getType().equals("5")) {// 赞助商

					Sponsors sponsors = new Sponsors();
					sponsors.setUser(auth1.getUser());
					Sponsors sp = super.getSponsorsService().getMobileSponsors(sponsors);
					if (sp == null) {
						returnData.setMsg("赞助商信息不存在");
						returnData.setReturnCode(PublicConstants.GET_ERROR);
						return returnData.toString();
					}

					if (sponsors.getRegion() != null) {
						List<Map<String, String>> lsMap = super.getRegionService().getRegion(
						        (long) sponsors.getRegion().getId(), sponsors.getRegion().getLevel());
						json.put("region", lsMap);
					} else {
						json.put("region", null);
					}

					sp.setUser(null);
                    json.put("loginMobile", loginMobile.getLogin());
					json.put("userInfo", auth1.getUser().getUserInfo());
					json.put("type", auth1.getUser().getUserInfo().getType());
					json.put("sponsors", sp);
					returnData.setData(json);

				} else if (auth1.getUser().getUserInfo().getType().equals("6")) {// 同时是小画家和受帮助的小朋友
					// Map<String, Object> map =
					// super.getAuthenticationService().getUserArtistInfoMobile(
					// auth1.getUser().getId());
					ArtistInfo artistInfo = new ArtistInfo();
					artistInfo.setUser(auth1.getUser());
					ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);

					Artist artist = super.getArtistService().get(at.getId());
					at.setStatus(artist.getStatus());

					if (at == null) {
						returnData.setMsg("画家信息不存在");
						returnData.setReturnCode(PublicConstants.GET_ERROR);
						return returnData.toString();
					}

					HelpChildren children = new HelpChildren();
					children.setUser(auth1.getUser());
					HelpChildren ch = super.getHelpChildrenService().getMobileHelpChildren(children);
					if (ch == null) {
						returnData.setMsg("受帮助的小朋友信息不存在");
						returnData.setReturnCode(PublicConstants.GET_ERROR);
						return returnData.toString();
					}
					if (at.getRegion() != null) {
						List<Map<String, String>> lsMap = super.getRegionService().getRegion(
						        (long) at.getRegion().getId(), at.getRegion().getLevel());
						json.put("region", lsMap);
					} else {
						json.put("region", null);
					}
					ch.setUser(null);
					json.put("userInfo", auth1.getUser().getUserInfo());
					json.put("type", auth1.getUser().getUserInfo().getType());
					ch.setUser(null);
					json.put("helpChildren", ch);
					json.put("artist", at);
                    json.put("loginMobile", loginMobile.getLogin());
					returnData.setData(json);
				}
				return returnData.toJSon(returnData);
			} else {
				returnData.setMsg("用户名或密码不正确");
				returnData.setReturnCode("1001");
				return returnData.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			returnData.setReturnCode("1001");
			returnData.setMsg("程序错误");
			return returnData.toString();
		}

	}

	/**
	 * 
	 * 获取artist信息 包括注册用户名 getArtistInfo 用户id获取该用户相关信息
	 * 
	 * @author gwb
	 * @param request
	 * @param response
	 * @param authentication
	 * @return 2015年9月11日 下午5:35:14
	 */
	@RequestMapping(value = "/getArtistInfo")
	@ResponseBody
	public String getArtistInfo(HttpServletRequest request, HttpServletResponse response, ArtistInfo ArtistInfo) {

		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();

		// Long userId = Long.parseLong(request.getParameter("userId"));
		// Map<String, Object> map = new HashMap<String, Object>();
		try {
			ArtistInfo ar = super.getArtistService().getUserArtistInfoMobile(ArtistInfo);

			if (ar == null) {
				returnData.setMsg("该画家不存在");
				returnData.setReturnCode("1005");
				return returnData.toString();
			}
			if (ar.getRegion() != null) {
				List<Map<String, String>> lsMap = super.getRegionService().getRegion((long) ar.getRegion().getId(),
				        ar.getRegion().getLevel());
				json.put("region", lsMap);
			} else {
				json.put("region", null);
			}
			// super.getAuthenticationService().getMobileAuthenticationByName(authentication);
			json.put("userInfo", ar.getUser().getUserInfo());
			json.put("type", ar.getUser().getUserInfo().getType());
			returnData.toJSon(ar);
			json.put("artist", ar);
			// map =
			// super.getAuthenticationService().getUserArtistInfoMobile(userId);
			// json.put("map", map);
			returnData.setData(json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return returnData.toString();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 修改artistinfo updateArtistInfo
	 * 
	 * @author gwb
	 * @param request
	 * @param response
	 * @param authentication
	 * @return 2015年9月11日 下午5:47:40
	 */
	@RequestMapping(value = "/updateArtistInfo")
	@ResponseBody
	public String updateArtistInfo(HttpServletRequest request, HttpServletResponse response, ArtistInfo artistInfo) {
		ReturnData returnData = new ReturnData();
		// Long userId=Long.parseLong(request.getParameter("userId"));
		Long regionId = Long.parseLong(request.getParameter("region_id"));
		Long artistId = Long.parseLong(request.getParameter("aritstId"));
		String img = request.getParameter("img");

		artistInfo.setId(artistId);
		// request.getParameter("teacherMobile");
		Artist artist = new Artist();
		artist.setArtistInfo(artistInfo);
		Region region = new Region();
		region.setId(regionId);
		artistInfo.setRegion(region);
		if (Tools.notEmpty(img)) {
			Map<String, String> imgStr = ImageUpload.imageUpload(img, "artist");

			if (!(null == imgStr || "".equals(imgStr))) {
				artistInfo.setImg(imgStr.get("thumb_path"));
				artistInfo.setOrginimg(imgStr.get("orginimg"));
			}
		}

		Date birthday = Date.valueOf(request.getParameter("birthday"));
		System.out.println(("--------------------------------------------DateUtil.getage(birthday)"
		        + DateUtil.getage(birthday) + "---------------"));
		artistInfo.setAge(DateUtil.getage(birthday));
		try {
			super.getAuthenticationService().updateArtistInfoMobile(artistInfo);
			// super.getArtistInfoService().updateArtistInfoMobile(artistInfo);
			// Works works = new Works();
			// works.setRegion(region);
			// works.setSchool(artistInfo.getSchool());
			// works.setArtist(artist);
			// super.getWorksService().updateWorksMobile(works);

			returnData.setMsg("修改成功");
		} catch (Exception e) {
			returnData.setMsg("修改失败");
			returnData.setReturnCode("1002");
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		// Map<String,Object> map=
		// super.getAuthenticationService().getUserArtistInfoMobile(userId);
		return returnData.toString();
	}

	/**
	 * 
	 * 
	 * 修改画家头像
	 * 
	 * 修改artistinfo updateArtistInfo
	 * 
	 * @author gwb
	 * @param request
	 * @param response
	 * @param authentication
	 * @return 2015年9月11日 下午5:47:40
	 */
	@RequestMapping(value = "/updateArtistInfoImg")
	@ResponseBody
	public String updateArtistInfoPhoto(HttpServletRequest request, HttpServletResponse response, ArtistInfo artistInfo) {
		ReturnData returnData = new ReturnData();
		Long artistId = Long.parseLong(request.getParameter("aritstId"));
		String img = request.getParameter("img");
		artistInfo.setId(artistId);
		Artist artist = new Artist();
		artist.setArtistInfo(artistInfo);
		if (Tools.notEmpty(img)) {
			Map<String, String> imgStr = ImageUpload.imageUpload(img, "artist");
			if (!(null == imgStr || "".equals(imgStr))) {
				artistInfo.setImg(imgStr.get("thumb_path"));
				artistInfo.setOrginimg(imgStr.get("orginimg"));
			}
		}
		try {
			super.getAuthenticationService().updateArtistInfoMobilePhoto(artistInfo);
			returnData.setMsg("修改成功");
		} catch (Exception e) {
			returnData.setMsg("修改失败");
			returnData.setReturnCode("1002");
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		return returnData.toString();
	}

	@RequestMapping("getTest")
	@ResponseBody
	public String getTest(Long id) throws Exception {
		ReturnData returnData = new ReturnData();
		JSONObject json = new JSONObject();
		Authentication auth1 = super.getAuthenticationService().get(id);
		ArtistInfo artistInfo = new ArtistInfo();
		artistInfo.setUser(auth1.getUser());
		ArtistInfo at = super.getArtistService().getUserArtistInfoMobile(artistInfo);
		//
		// Map<String, Object> map =
		// super.getAuthenticationService().getUserArtistInfoMobile(
		// auth1.getUser().getId());

		json.put("artist", at);
		json.put("userInfo", auth1.getUser().getUserInfo());
		// json.put("artist", map);
		// json.put("type", auth1.getUser().getUserInfo().getType());
		returnData.setData(json);
		return returnData.toJSon(returnData);
	}

}
