package com.myhome.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;
import com.myhome.entity.Picture;
import com.myhome.entity.Region;
import com.myhome.entity.Tag;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.GameVo;
import com.myhome.service.IGameService;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.DateUtil;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;

@Component("gameServiceImpl")
public class GameServiceImpl extends AbstractServiceImpl implements IGameService {

    @Override
    @Transactional(readOnly = true)
    public Game get(Long id) {
        return getGameDAO().get(id);
    }

    @Transactional
    public void add(Game game) {
    	getGameDAO().add(game);
    }

    @Transactional
    public int update(Game game) {
        // TODO 待完成
        throw new RuntimeException();
    }

    @Transactional
    public int remove(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

    @Transactional
    public int delete(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

    @Override
    @Transactional
    public Works addWeb(GameVo game, Integer userid, HttpServletRequest request) {
        try {
            if (game.getId() == null && game.getWorksid() == null) {
                Artist artist = new Artist();
                Works works = new Works();
                ArtistInfo artistinfo = new ArtistInfo();
                Tag tag = new Tag();
                tag.setId(Long.valueOf(game.getWorkstag()));
                WorksTagItem workstag = new WorksTagItem();
                User user = new User();
                user.setId(Long.valueOf(userid));
                //所属城市
                Region region = new Region();
                region.setId(Long.valueOf(game.getRegion()));
                //画家信息
                artistinfo.setAge(DateUtil.getage(game.getBirthday()));
                artistinfo.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                  + "/artist/thumb_" + game.getImg());
                artistinfo.setOrginimg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/artist/"
                                       + game.getImg());
                artistinfo.setName(game.getName());
                artistinfo.setNation(game.getNation());
                artistinfo.setBirthday(game.getBirthday());
                artistinfo.setMale("1".equals(game.getMale()));
                artistinfo.setRegion(region);
                artistinfo.setOrigin(game.getOrigin());
                artistinfo.setZodiac(DateUtil.getZodica(game.getBirthday()));
                artistinfo.setConstellation(DateUtil.getConstellation(game.getBirthday()));
                artistinfo.setIdCode(game.getIdCode());
                artistinfo.setParentMobile(game.getParentMobile());
                artistinfo.setSchool(game.getSchool());
                artistinfo.setTrainingInstitution(game.getTrainingInstitution());
                artistinfo.setSchoolMobile(game.getSchoolMobile());
                artistinfo.setInstructor(game.getInstructor());
                artistinfo.setInstructorMobile(game.getInstructorMobile());
                artistinfo.setTeacher(game.getTeacher());
                artistinfo.setCartoon(game.getCartoon());
                artistinfo.setTeacherMobile(game.getTeacherMobile());
                artistinfo.setReferrerSchool(game.getReferrerSchool());
                artistinfo.setReferrerMobile(game.getReferrerMobile());
                // 2.0 新增
                artistinfo.setAlipayAccount(game.getAlipayAccount());
                artistinfo.setBankAccount(game.getBankAccount());
                artistinfo.setBankName(game.getBankName());
                artistinfo.setAccountName(game.getAccountName());
                artistinfo.setWeChatAccount(game.getWeChatAccount());

                artistinfo.setUser(user);
                artist.setArtistInfo(artistinfo);
                artistinfo.setArtist(artist);
                getArtistDAO().add(artist);
                //保存作品
                works.setAge(artistinfo.getAge());
                works.setArtist(artist);
                works.setMale("1".equals(game.getMale()));
                works.setRegion(region);
                works.setVotenum(0);
                works.setName(game.getWorksname());
                works.setAuthor(game.getName());
                works.setInstructor(game.getInstructor());
                works.setInstructorMobile(game.getInstructorMobile());
                works.setSchool(game.getSchool());
                works.setDescription(game.getDescription());
                //图片塞值
                Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
                WorksPictureItem workspictureitem = new WorksPictureItem();
                workspictureitem.setWorks(works);
                Picture picture = new Picture();
                picture.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/thumb_"
                                + game.getPath());
                picture.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/"
                                     + game.getPath());
                workspictureitem.setPicture(picture);
                worksPictureItemSet.add(workspictureitem);
                works.setWorksPictureItemSet(worksPictureItemSet);
                getWorksDAO().add(works);
                //保存作品类型
                workstag.setWorks(works);
                workstag.setTag(tag);
                getWorksTagItemDAO().add(workstag);
                //保存比赛信息
                GameWorksItem gameworks = new GameWorksItem();
                List<Game> gamelist = getGameDAO().findList();
                if (CommonUtils.isNotEmpty(gamelist)) {
                    gameworks.setGame(gamelist.get(0));
                }
                gameworks.setWorks(works);
                gameworks.setApplicant(user);
                getGameDAO().add(gameworks);
                //          System.out.print("save");
                //保存角色类型
                UserInfo userinfo = getUserInfoDAO().get(Long.valueOf(userid));
                if ("3".equals(userinfo.getType())) {
                    userinfo.setType("6");
                } else {
                    userinfo.setType("2");
                }
                getUserInfoDAO().update(userinfo);
                request.getSession().setAttribute(SysConstants.USER_TYPE, userinfo.getType());
                return works;
            } else {
                Works works = getWorksDAO().get(Long.valueOf(game.getWorksid()));
                Artist artist = getArtistDAO().getByUserid(userid.toString());
                ArtistInfo artistinfo = artist.getArtistInfo();
                Tag tag = new Tag();
                tag.setId(Long.valueOf(game.getWorkstag()));
                User user = new User();
                user.setId(Long.valueOf(userid));
                //所属城市
                Region region = new Region();
                region.setId(Long.valueOf(game.getRegion()));
                //画家信息
                artistinfo.setAge(DateUtil.getage(game.getBirthday()));
                if (!"useless".equals(game.getImg())) {
                    artistinfo.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                      + "/artist/thumb_" + game.getImg());
                    artistinfo.setOrginimg(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                           + "/artist/" + game.getImg());
                }
                artistinfo.setName(game.getName());
                artistinfo.setNation(game.getNation());
                artistinfo.setBirthday(game.getBirthday());
                artistinfo.setMale("1".equals(game.getMale()));
                artistinfo.setRegion(region);
                artistinfo.setOrigin(game.getOrigin());
                artistinfo.setZodiac(DateUtil.getZodica(game.getBirthday()));
                artistinfo.setConstellation(DateUtil.getConstellation(game.getBirthday()));
                artistinfo.setIdCode(game.getIdCode());
                artistinfo.setParentMobile(game.getParentMobile());
                artistinfo.setSchool(game.getSchool());
                artistinfo.setTrainingInstitution(game.getTrainingInstitution());
                artistinfo.setSchoolMobile(game.getSchoolMobile());
                artistinfo.setInstructor(game.getInstructor());
                artistinfo.setInstructorMobile(game.getInstructorMobile());
                artistinfo.setTeacher(game.getTeacher());
                artistinfo.setCartoon(game.getCartoon());
                artistinfo.setTeacherMobile(game.getTeacherMobile());
                artistinfo.setReferrerSchool(game.getReferrerSchool());
                artistinfo.setReferrerMobile(game.getReferrerMobile());
                // 2.0 新增
                artistinfo.setAlipayAccount(game.getAlipayAccount());
                artistinfo.setBankAccount(game.getBankAccount());
                artistinfo.setBankName(game.getBankName());
                artistinfo.setAccountName(game.getAccountName());
                artistinfo.setWeChatAccount(game.getWeChatAccount());

                artist.setArtistInfo(artistinfo);
                getArtistDAO().updateweb(artist);

                // 保存作品
                works.setName(game.getWorksname());
                works.setAuthor(game.getName());
                works.setInstructor(game.getInstructor());
                works.setInstructorMobile(game.getInstructorMobile());
                works.setSchool(game.getSchool());
                works.setDescription(game.getDescription());
                // 图片塞值
                if (!"useless".equals(game.getPath())) {
                    Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
                    WorksPictureItem workspictureitem = getWorksPictureItemDAO().getByWorksid(
                        works.getId());
                    Picture picture = workspictureitem.getPicture();
                    picture.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                    + "/works/thumb_" + game.getPath());
                    picture.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                         + "/works/" + game.getPath());
                    workspictureitem.setPicture(picture);
                    worksPictureItemSet.add(workspictureitem);
                    works.setWorksPictureItemSet(worksPictureItemSet);
                }
                getWorksDAO().update(works);
                // 保存作品类型
                WorksTagItem workstag = getWorksTagItemDAO().getByWorksid(works.getId());
                workstag.setTag(tag);
                getWorksTagItemDAO().update(workstag);
                // System.out.print("update");
                return works;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * gwb 用户填写我要参赛信息 TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.myhome.service.IGameService#addMobielCompetition(com.myhome.entity.ArtistInfo,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional
    public String addMobielCompetition(ArtistInfo artistInfo, HttpServletRequest request)
                                                                                         throws Exception {

        boolean falg = super.getTeacherDao().getMobileToken(artistInfo.getToken(), "t_artist_info");
        if (!falg) {
            return "";
        }

        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        String userType = "2";
        if (artistInfo.getUser() != null && artistInfo.getUser().getUserInfo() != null) {
            userType = artistInfo.getUser().getUserInfo().getType();
        }

        // 当前用户信息
        String userId = request.getParameter("userId");

        // String gameId =request.getParameter("gameId");
        User user = new User();
        user.setId(Long.parseLong(userId));
        request.getParameter("birthday");
        /**
         * 填写作者信息
         */
        String img = request.getParameter("img");
        String name = request.getParameter("name");
        String male = request.getParameter("male");// 性别
        String origin = request.getParameter("origin");// 籍贯
        boolean flag = male.equals("1");

        String nation = request.getParameter("nation");// 名族
        String zodiac = request.getParameter("zodiac");// 生肖
        String constellation = request.getParameter("constellation");// 星座
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        //		String idCode = request.getParameter("idCode");
        Long region_id = Long.parseLong(request.getParameter("region_id"));// 城市
        String school = request.getParameter("school");// 学校培训机构
        String parentMobile = request.getParameter("parentMobile");

        /**
         * 填写推荐方信息
         */
        String referrerSchool = request.getParameter("referrerSchool"); // 培训
        String referrerMobile = request.getParameter("referrerMobile");

        String teacher = request.getParameter("teacher");// 组织
        String teacherMobile = request.getParameter("teacherMobile");

        String instructor = request.getParameter("instructor");// 指导
        String instructorMobile = request.getParameter("instructorMobile");

        /**
         * 填写作品信息
         */
        // 作品类别
        Long tag_id = Long.parseLong(request.getParameter("tag_id"));
        // 作品名称
        String work_name = request.getParameter("work_name");
        // 作品描述
        String description = request.getParameter("description");
        // 上传作品
        String path = request.getParameter("path");

        // 2.1新增
        // 培训机构
        String trainingInstitution = request.getParameter("trainingInstitution");

        Artist artist = new Artist();
        artist.setStatus(artistInfo.getStatus());
        // artist.setStatus(2);
        // 向artist表中添加信息
        super.getArtistDAO().addMobileArtist(artist);

        Region region = new Region();
        region.setId(region_id);
        Tag tag = new Tag();
        tag.setId(tag_id);
        // TODO 从sesion中获取artist_id
        // 向artistinof表中插入参赛者信息
        artist.setId(artist.getId());
        Map<String, String> map = ImageUpload.imageUpload(img, "artist");
        if (null == path || "".equals(path)) {
            json.put("status", false);
            returnData.setMsg("上传图片失败");
            returnData.setData(json);
            return returnData.toString();
        }

        artistInfo.setArtist(artist);
        artistInfo.setUser(user);
        artistInfo.setOrigin(origin);
        artistInfo.setImg(map.get("thumb_path"));
        artistInfo.setOrginimg(map.get("orginimg"));
        artistInfo.setName(name);
        artistInfo.setMale(male.equals("1"));// TODO
        artistInfo.setNation(nation);
        artistInfo.setZodiac(zodiac);
        artistInfo.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
        artistInfo.setConstellation(constellation);
        artistInfo.setBirthday(birthday);
        //		artistInfo.setIdCode(idCode);
        artistInfo.setRegion(region);
        artistInfo.setSchool(school);
        artistInfo.setParentMobile(parentMobile);
        // artistInfo.setMale(flag);
        artistInfo.setReferrerMobile(referrerMobile);
        artistInfo.setReferrerSchool(referrerSchool);

        artistInfo.setTeacher(teacher);
        artistInfo.setTeacherMobile(teacherMobile);

        // 2.1新增
        // 培训机构
        artistInfo.setTrainingInstitution(trainingInstitution);


        // artistInfo.setr
        artistInfo.setAge(DateUtil.getage(birthday));
        Long artistId = super.getArtistInfoDAO().addArtistInfoMobil(artistInfo);
        // 向work中插入数据
        Map<String, String> pathMap = ImageUpload.imageUpload(path, "works");
        if (null == pathMap || "".equals(pathMap)) {
            json.put("status", false);
            returnData.setMsg("上传图片失败");
            returnData.setData(json);
            return returnData.toString();
        }

        Works works = new Works();
        works.setArtist(artist);
        works.setDescription(description);
        works.setName(work_name);
        works.setSchool(instructor);
        works.setSchoolMobile(instructorMobile);
        works.setAge(DateUtil.getage(birthday));
        // 2.1新增
        works.setInstructor(instructor);
        works.setInstructorMobile(instructorMobile);
        works.setMale(false);
        works.setRegion(region);

        long workId = 0l;
        workId = super.getWorksDAO().addMobileWork(works);
        works.setId(workId);

        // 向t_picture表中国插入数据
        Picture picture = new Picture();
        picture.setPath(pathMap.get("thumb_path"));
        picture.setOrginpath(pathMap.get("orginimg"));
        Long picId = super.getPictureDAO().addPictureMobile(picture);
        picture.setId(picId);

        // 向WorksPictureItem表中插入数据
        WorksPictureItem worksPictureItem = new WorksPictureItem();
        worksPictureItem.setPicture(picture);
        worksPictureItem.setWorks(works);
        Long wptId = super.getWorksPictureItemDAO().addWorksPictureItemMobile(worksPictureItem);

        // 向 t_works_tag_item表中插入数据
        WorksTagItem worksTagItem = new WorksTagItem();
        worksTagItem.setWorks(works);
        worksTagItem.setTag(tag);

        Long wtiId = super.getWorksTagItemDAO().addWorksTagItemMobile(worksTagItem);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong(userId));

        userInfo.setType(userType);
        super.getUserDAO().updateUserMobile(userInfo);

        Map<String, String> worksMap = new HashMap<String, String>();
        worksMap.put("worksId", workId + "");
        worksMap.put("artistId", artist.getId() + "");
        json.put("works", worksMap);
        returnData.setData(json);
        return returnData.toString();
    }

	@Override
	public List<Game> findList() {
		 List<Game> gamelist = getGameDAO().findList();
		return gamelist;
	}

	@Override
	public void add(GameWorksItem gameworks) {
		getGameDAO().addmodel(gameworks);
	}

}
