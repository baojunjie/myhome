package com.myhome.html5.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.Picture;
import com.myhome.entity.Region;
import com.myhome.entity.Tag;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.DateUtil;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SMSUtil;
import com.myhome.utils.UtilMD5;
import com.myhome.utils.UtilMD5Encoder;

/**
 * 我要参赛 1、用户必须已经注册过之后才可以报名参赛（ps：相当于用户已经在artist中拥有信息） 2、参赛者个人信息保存在artist——info表中
 * 3、同时在works中新增一条记录 4、作品上传到t_picture中 5、通过
 * t_works_picture_item建立works和t_picture的关系 6、通过t_works_tag_item建立t_tag和works的关系
 * ClassName: ArtistInfoController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015年9月10日 下午3:09:03 <br/>
 */

@Controller(value = "H5GameController")
@RequestMapping(value = "/H5/game", produces = "text/html;charset=UTF-8")
public class GameController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(GameController.class);

    /**
     * app 我要参赛 add
     * 
     * @author ywz
     * @param artistInfo
     * @return 2015年9月10日 下午3:27:00
     */
    //	@SuppressWarnings("unused")
    @RequestMapping("/addCompetition")
    @ResponseBody
    public String interceptoraddMobielCompetition(@ModelAttribute("artistInfo") ArtistInfo artistInfo,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String returnStr = "";
        ReturnData returnData = new ReturnData();
        try {
            returnStr = super.getGameService().addMobielCompetition(artistInfo, request);
            return returnStr;
        } catch (Exception e) {
            returnData.setMsg("提交失败");
            returnData.setReturnCode("1001");
            returnData.setSuccess(false);
            e.printStackTrace();
            return returnData.toString();
        }
    }

    /**
     * 点击提交审核 提交作品参赛 add
     * 
     * @author ywz
     * @param artistInfo
     * @return 2015年9月10日 下午3:27:00
     */
    @RequestMapping("/addGameWorks")
    @ResponseBody
    public String interceptoraddGame(String ids, int status, HttpServletRequest request,
                                     HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        String[] idsStr = ids.split(",");
        // 当前用户信息
        String userId = request.getParameter("userId");

        // game
        Game game = new Game();
        game.setId(1l);// TODO 哪一个赛事
        Works works = new Works();
        User user = new User();
        user.setId(Long.parseLong(userId));
        GameWorksItem gameWorksItem = new GameWorksItem();
        gameWorksItem.setApplicant(user);
        gameWorksItem.setGame(game);
        gameWorksItem.setWorks(works);
        try {
            boolean falg = super.getGameWorksItemService().addGameWorksItemMobile(gameWorksItem,
                idsStr, status);
            if (falg) {
                returnData.setMsg("提交成功");
            } else {
                returnData.setMsg("提交失败");
                returnData.setReturnCode("1002");
                returnData.setSuccess(false);
            }
        } catch (Exception e) {
            returnData.setMsg("提交失败");
            returnData.setReturnCode("1002");
            returnData.setSuccess(false);
            e.printStackTrace();
            logger.error(e.getMessage());

        }
        return returnData.toString();
    }

    /**
     * 快速注册上传作品
     * 
     * @author ywz
     * @return 2016年4月5日 上午10:16:59
     */
    @RequestMapping(value = "/fastRegistration")
    @ResponseBody
    public String fastRegistration(HttpServletRequest request,
                                              HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();

        // 参数验证
        String mobile = request.getParameter("mobile");
        if (null == mobile || "".equals(mobile)) {
            returnData.setMsg("请输入手机号");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String name = request.getParameter("name");
        if (null == name || "".equals(name)) {
            returnData.setMsg("请输入姓名");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String sex = request.getParameter("sex");
        if (null == sex || "".equals(sex)) {
            returnData.setMsg("请输入性别");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String birthday = request.getParameter("birthday");
        if (null == birthday || "".equals(birthday)) {
            returnData.setMsg("请输入生日");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String regionCode = request.getParameter("regionCode");
        if (null == regionCode || "".equals(regionCode)) {
            returnData.setMsg("请输入地区");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String workName = request.getParameter("workName");
        if (null == workName || "".equals(workName)) {
            returnData.setMsg("请输入作品名");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String img = request.getParameter("img");
        if (null == img || "".equals(img)) {
            returnData.setMsg("请上传头像");
            returnData.setSuccess(false);
            return returnData.toString();
        }
        String wimg = request.getParameter("wimg");
        if (null == wimg || "".equals(wimg)) {
            returnData.setMsg("请上传作品");
            returnData.setSuccess(false);
            return returnData.toString();
        }

        try {
            //注册
            List<Authentication> authenticationlist = getAuthenticationService()
                .findByLogin(mobile);
            String userName = "wodejia"+ RandomDataUtil.SixNumRadom() +"_"+mobile.substring(7);
            List<UserInfo> list = getUserInfoService().findByName(userName);
            if (CommonUtils.isEmpty(authenticationlist) && CommonUtils.isEmpty(list)) {
                String salt = RandomDataUtil.SixRadom();
                UserInfo userinfo = new UserInfo();
                userinfo.setName(userName);
                userinfo.setType("1");
                User user = new User();
                user.setUserInfo(userinfo);
                userinfo.setUser(user);
                userinfo.setRegion(null);
                getUserService().add(user);
                // 注册成功后吧信息写入session13023617068
                //保存手机认证
                MobileAuthentication mobileAuthentication = new MobileAuthentication();
                mobileAuthentication.setUser(user);
                mobileAuthentication.setLogin(mobile);
                mobileAuthentication.setSalt(salt);
                String password = UtilMD5.getMD5ofStr(mobile.substring(5)).toLowerCase();
                mobileAuthentication.setPassword(UtilMD5Encoder.encodePassword(password, salt));
                getMobileAuthenticationService().add(mobileAuthentication);
                //保存用户名认证
                LoginAuthentication loginAuthentication = new LoginAuthentication();
                loginAuthentication.setUser(user);
                loginAuthentication.setLogin(userName);
                loginAuthentication.setSalt(salt);
                loginAuthentication.setPassword(UtilMD5Encoder.encodePassword(password, salt));
                getLoginAuthenticationService().add(loginAuthentication);

                /**
                 * 成为小画家
                 */
                Artist artist = new Artist();
                Works works = new Works();
                ArtistInfo artistinfo = new ArtistInfo();
                Tag tag = getTagService().get((long) 8);
                WorksTagItem workstag = new WorksTagItem();
                //所属城市
                Region region = new Region();
                region.setId(Long.parseLong(regionCode));
                //画家信息
                artistinfo.setInvalid(false);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(birthday);
                artistinfo.setAge(DateUtil.getage(date));
                Map<String, String> pathArtist = ImageUpload.imageUpload(img, "artist");;
                artistinfo.setImg(pathArtist.get("thumb_path"));
                artistinfo.setOrginimg(pathArtist.get("orginimg"));
                artistinfo.setName(name);
                artistinfo.setBirthday(new java.sql.Date(date.getTime()));
                artistinfo.setMale("1".equals(sex));
                artistinfo.setRegion(region);
                artistinfo.setZodiac(DateUtil.getZodica(date));
                artistinfo.setConstellation(DateUtil.getConstellation(date));
                artistinfo.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
                artistinfo.setUser(user);
                artist.setInvalid(false);
                artist.setStatus(2);
                artist.setArtistInfo(artistinfo);
                artistinfo.setArtist(artist);
                getArtistService().add(artist);
                //保存作品
                works.setInvalid(false);
                works.setStatus(2);
                works.setAge(artistinfo.getAge());
                works.setArtist(artist);
                works.setMale("1".equals(sex));
                works.setRegion(region);
                works.setVotenum(0);
                works.setName(workName);
                works.setAuthor(name);
                //图片塞值
                Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
                WorksPictureItem workspictureitem = new WorksPictureItem();
                workspictureitem.setWorks(works);
                Picture picture = new Picture();
                Map<String, String> path = ImageUpload.imageUpload(wimg, "works");
                picture.setPath(path.get("thumb_path"));
                picture.setOrginpath(path.get("orginimg"));
                workspictureitem.setPicture(picture);
                worksPictureItemSet.add(workspictureitem);
                works.setWorksPictureItemSet(worksPictureItemSet);
                getWorksService().add(works);
                //保存作品类型
                workstag.setWorks(works);
                workstag.setInvalid(false);
                workstag.setTag(tag);
                getWorksTagItemService().add(workstag);
                //保存比赛信息
                GameWorksItem gameworks = new GameWorksItem();
                List<Game> gamelist = getGameService().findList();
                if (CommonUtils.isNotEmpty(gamelist)) {
                    gameworks.setGame(gamelist.get(0));
                }
                gameworks.setWorks(works);
                gameworks.setApplicant(user);
                getGameService().add(gameworks);
                //保存角色类型
                if ("3".equals(userinfo.getType())) {
                    userinfo.setType("6");
                } else {
                    userinfo.setType("2");
                }
                getUserInfoService().update(userinfo);

                String message = "您好！感谢您参加我的家全国绘画大赛，您登录帐号为：" + mobile
                              + "初始密码为：" + mobile.substring(5)
                              + "。请尽快登录我的家官网haaaaaa.com或者下载官方APP “画儿”更改密码。";
                boolean smsFalg = SMSUtil.send(mobile, message, "我的家");
                json.put("sendIsSuccess", smsFalg == true ? "短信发送成功" : "短信发送失败");
                json.put("message", message);
            } else {
                returnData.setMsg("您已注册过我的家全国绘画大赛，请到我的家官网haaaaaa.com或者下载APP认证上传照片");
                returnData.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("注册失败");
            returnData.setSuccess(false);
        }
        returnData.setData(json);
        return returnData.toString();
    }
}
