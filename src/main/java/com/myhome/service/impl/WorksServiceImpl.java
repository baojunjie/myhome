package com.myhome.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
import com.myhome.entity.vo.ArtistModel;
import com.myhome.entity.vo.GameVo;
import com.myhome.entity.vo.ImpWorks;
import com.myhome.entity.vo.WorksVo;
import com.myhome.service.IWorksService;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.DateUtil;

@Component("worksServiceImpl")
public class WorksServiceImpl extends AbstractServiceImpl implements IWorksService {

    @Transactional(readOnly = true)
    public Works get(Long id) {
        return getWorksDAO().get(id);
    }

    @Transactional
    public void add(Works works) {
    	getWorksDAO().add(works);
    }

    @Transactional
    public void update(Works works) {
        getWorksDAO().update(works);
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

    /**
     * 手机端作品展现 TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.myhome.service.IWorksService#getMobileWorksList(com.myhome.entity.Works)
     */
    @Override
    @Transactional(readOnly = true)
    public List<Works> getMobileWorksList(Works works) throws Exception {

        return getWorksDAO().getMobileWorksList(works);
    }

    @Override
    @Transactional
    public long addMobileWork(Works works) throws Exception {
        long id = getWorksDAO().addMobileWork(works);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getWorksInfoMobile(Long id) throws Exception {
        return getWorksDAO().getWorksInfoMobile(id);
    }

    /**
     * app 删除作品 gwb
     */
    @Override
    @Transactional
    public void deleteWorksMobile(Long id) throws Exception {
        getWorksDAO().deleteWorksMobile(id);
    }

    /**
     * app 修改作品 TODO
     * 
     */
    @Override
    @Transactional
    public boolean updateWorksMobile(WorksVo worksVo) throws Exception {
        // 修改作品名和作品类别
        Works works = new Works();
        works.setId(worksVo.getWorksId());
        works.setName(worksVo.getName());
        works.setDescription(worksVo.getDescription());
        // 2.1新增
        works.setInstructor(worksVo.getInstructor());
        works.setInstructorMobile(worksVo.getInstructorMobile());

        if (worksVo.getWorksId() > 0) {
            super.getWorksDAO().updateWorksMobile(works);
        }

        Picture pic = new Picture();
        pic.setId(worksVo.getPictureId());
        pic.setPath(worksVo.getPicture());
        pic.setOrginpath(worksVo.getOrginimg());
        if (null != worksVo.getPictureId() && worksVo.getPictureId() > 0) {
            super.getPictureDAO().updatePictureMobile(pic);
        }

        WorksTagItem worksTagItem = new WorksTagItem();
        worksTagItem.setId(worksVo.getWtiId());
        Tag tag = new Tag();
        tag.setId(worksVo.getTagId());
        worksTagItem.setTag(tag);
        if (worksVo.getWtiId() != null && worksVo.getTagId() != null) {
            if (worksVo.getWtiId() > 0 && worksVo.getTagId() > 0) {
                super.getWorksTagItemDAO().updateWorksTagItem(worksTagItem);
            }
        }

        return false;

    }

    /**
     * app 根据id查看作品 TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getgetWorksByIdMobile(Long id) throws Exception {

        return super.getWorksDAO().getgetWorksByIdMobile(id);
    }

    /**
     * app
     * 
     * 获取画家的参赛作品
     */
    @Override
    @Transactional
    public List<Map<String, Object>> getArtistGameWorks(Long id, int pageNo, int pageSize,
                                                        int status) throws Exception {

        return getWorksDAO().getArtistGameWorks(id, pageNo, pageSize, status);
    }

    /**
     * 审核作品
     */
    @Override
    @Transactional
    public boolean getCheckWorks(String[] ids, int status) throws Exception {
        int k = 0;
        for (String id : ids) {
            k += super.getWorksDAO().getCheckWorks(id, status);
        }
        return k == ids.length;
    }

    /**
     * 最新作品 TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getWorksMobile(Integer age, String region_code, String male,
                                                    int pageSize, int pageIndex, String type,
                                                    int status, String typeRegion) throws Exception {

        return super.getWorksDAO().getWorksMobile(age, region_code, male, pageSize, pageIndex,
            type, status, typeRegion);
    }

    /**
     * 
     * 修改画家信息时修改画家作品信息
     * 
     * @throws Exception
     * 
     */
    @Override
    @Transactional
    public void updateWorksMobile(Works works) throws Exception {

        super.getWorksDAO().updateWorksMobile(works);

    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    @Transactional
    public List<ArtistModel> getBoyList() {
        return getWorksDAO().getBoyList();
    }

    @Transactional
    @Override
    public List<ArtistModel> getGirlList() {
        return getWorksDAO().getGirlList();
    }

    @Transactional
    @Override
    public List<Works> getListByAge(Integer age) {
        return getWorksDAO().getListByAge(age);
    }

    @Transactional
    @Override
    public int getCountByAge(Integer age,String male) throws Exception{
        return getWorksDAO().getCountByAge(age,male);
    }

    @Transactional
    @Override
    public List<Works> getListByAgeAndMale(Integer age, boolean male) {
        return getWorksDAO().getListByAgeAndMale(age, male);
    }

    @Transactional
    @Override
    public List<Works> getListByRegion(Long region) {
        return getWorksDAO().getListByRegion(region);
    }

    @Transactional
    @Override
    public int getCountByRegion(Long region, String male)throws Exception {
        return getWorksDAO().getCountByRegion(region,male);
    }

    @Transactional
    @Override
    public List<Works> getListByRegionAndMale(Long region, boolean male) {
        return getWorksDAO().getListByRegionAndMale(region, male);
    }

    @Transactional
    @Override
    public List<Works> getWorksByAgeAndMaleAndRegion(Integer age, Boolean male, Integer code) {
        return getWorksDAO().getWorksByAgeAndMaleAndRegion(age, male, code);
    }
    @Transactional
    @Override
    public List<Works> getWorksByAgeAndMaleAndRegion1(Integer age, Boolean male, Integer code) {
        return getWorksDAO().getWorksByAgeAndMaleAndRegion1(age, male, code);
    }
    @Override
    @Transactional
    public List<Works> getListByAge0fPage(Integer searchage, int index, Integer size) {
        return getWorksDAO().getListByAge0fPage(searchage, index, size);
    }

    @Override
    @Transactional
    public List<Works> getListByAgeAndMale0fPage(Integer age, boolean male, int index, Integer size) {
        return getWorksDAO().getListByAgeAndMale0fPage(age, male, index, size);
    }

    @Override
    @Transactional
    public List<Works> getListByRegionOfPage(Long region, int index, Integer size) {
        return getWorksDAO().getListByRegionOfPage(region, index, size);
    }

    @Override
    @Transactional
    public List<Works> getListByRegionAndMaleOfPage(Long region, boolean male, int index,
                                                    Integer size) {
        return getWorksDAO().getListByRegionAndMaleOfPage(region, male, index, size);
    }

    @Override
    @Transactional
    public Integer getByArtistid(long artist_id, Integer type) {
        return getWorksDAO().getByArtistid(artist_id, type);
    }

    @Override
    @Transactional
    public List<WorksTagItem> getByArtistidOfPage(long artist_id, Integer type, Integer index,
                                                  Integer size) {
        return getWorksDAO().getByArtistidOfPage(artist_id, type, index, size);
    }

    @Override
    @Transactional
    public WorksTagItem getWorksTagItemByWorksid(Long worksid) {
        return getWorksDAO().getWorksTagItemByWorksid(worksid);
    }

    @Override
    @Transactional
    public String upload(GameVo game, String userid, String artistid) {
        Works works = new Works();
        Tag tag = new Tag();
        tag.setId(Long.valueOf(game.getWorkstag()));
        WorksTagItem workstag = new WorksTagItem();
        User user = new User();
        user.setId(Long.valueOf(userid));
        Artist artist = getArtistDAO().get(Long.valueOf(artistid));
        // 保存作品
        works.setAge(DateUtil.getage(artist.getArtistInfo().getBirthday()));
        works.setArtist(artist);
        works.setMale(artist.getArtistInfo().getMale());
        works.setRegion(artist.getArtistInfo().getRegion());
        works.setName(game.getWorksname());
        works.setVotenum(0);
        works.setAuthor(artist.getArtistInfo().getName());
        works.setInstructor(game.getInstructor());
        works.setInstructorMobile(game.getInstructorMobile());
        works.setSchool(artist.getArtistInfo().getSchool());
        works.setDescription(game.getDescription());
        // 图片塞值
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
        // 保存作品类型
        workstag.setWorks(works);
        workstag.setTag(tag);
        getWorksTagItemDAO().add(workstag);
        // 保存比赛信息
        GameWorksItem gameworks = new GameWorksItem();
        List<Game> gamelist = getGameDAO().findList();
        if (CommonUtils.isNotEmpty(gamelist)) {
            gameworks.setGame(gamelist.get(0));
        }
        gameworks.setWorks(works);
        gameworks.setApplicant(user);
        getGameDAO().add(gameworks);
        return works.getId().toString();
    }

    @Override
    @Transactional
    public Works uploaduUpdate(GameVo game, String userid, String artistid) {
        try {
            Works works = getWorksDAO().get(Long.valueOf(game.getWorksid()));
            Tag tag = new Tag();
            tag.setId(Long.valueOf(game.getWorkstag()));
            // 保存作品
            works.setName(game.getWorksname());
            works.setAuthor(game.getName());
            works.setInstructor(game.getInstructor());
            works.setInstructorMobile(game.getInstructorMobile());
            works.setSchool(game.getSchool());
            works.setDescription(game.getDescription());
            getWorksDAO().update(works);
            // 保存作品类型
            WorksTagItem workstag = getWorksTagItemDAO().getByWorksid(works.getId());
            workstag.setTag(tag);
            getWorksTagItemDAO().update(workstag);
            return works;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Transactional
    public Works getById(long id) {
        return getWorksDAO().getById(id);
    }

    @Override
    public int getWorksCount() {
        return super.getWorksDAO().getWorksCount();
    }

    @Override
    public List<Map<String, Object>> getCitySort() throws Exception{
        return super.getWorksDAO().getCitySort();
    }

    @Override
    public int getWorksByRegion(Region region) {
        return super.getWorksDAO().getWorksByRegion(region);
    }

    //    @Override
    //    public CityAndWork getWorksWithoutRegion() {
    //        return super.getWorksDAO().getWorksWithoutRegion();
    //    }
    @Override
    public List<Map<String, Object>> getWorksOnStateThree()throws Exception {
        return super.getWorksDAO().getWorksOnStateThree();
    }

    @Override
    public Map<String, Object> getTheWorkSort(Long worksid){
        return super.getWorksDAO().getTheWorkSort(worksid);
    }

	@Override
	public Long addImg(String filename) {
	        Picture picture = new Picture();
	        picture.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/thumb_"
	                        +filename);
	        picture.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/"
	                             +filename);
	        getPictureDAO().add(picture);
	        return picture.getId();
	}

	@Override
	public void batchImport(ImpWorks impWorks,Long userid) {
		try {

            Artist artist = new Artist();
            Works works = new Works();
            ArtistInfo artistinfo = new ArtistInfo();
            List<Tag> tagList = getTagDAO().getTagListByName(impWorks.getWorksType());
            WorksTagItem workstag = new WorksTagItem();
            User user = new User();
            user.setId(Long.valueOf(userid));
            //所属城市
            Region region = new Region();
            region.setId(impWorks.getRegion());
            //画家信息
            artistinfo.setInvalid(true);
            artistinfo.setAge(DateUtil.getage(impWorks.getBirthday()));
            artistinfo.setImg(impWorks.getHeadImg());
            artistinfo.setOrginimg(impWorks.getHeadImg().replace("thumb_", ""));
            artistinfo.setName(impWorks.getName());
            artistinfo.setBirthday(new java.sql.Date(impWorks.getBirthday().getTime()));
            artistinfo.setMale("男".equals(impWorks.getSex()));
            artistinfo.setRegion(region);
            artistinfo.setZodiac(DateUtil.getZodica(impWorks.getBirthday()));
            artistinfo.setConstellation(DateUtil.getConstellation(impWorks.getBirthday()));
            artistinfo.setParentMobile(impWorks.getParentMobile());
            artistinfo.setSchool(impWorks.getSchool());
            artistinfo.setTrainingInstitution(impWorks.getTrainingInstitution());
            artistinfo.setCartoon(impWorks.getCartoon());
            // 2.0 新增
            artistinfo.setAlipayAccount(impWorks.getAlipayAccount());
            artistinfo.setBankAccount(impWorks.getBankAccount());
            artistinfo.setAccountName(impWorks.getAccountName());
            artistinfo.setWeChatAccount(impWorks.getWeChatAccount());

            artistinfo.setUser(user);
            artist.setInvalid(true);
            artist.setArtistInfo(artistinfo);
            artistinfo.setArtist(artist);
            getArtistDAO().add(artist);
            //保存作品
            works.setInvalid(true);
            works.setAge(artistinfo.getAge());
            works.setArtist(artist);
            works.setMale("男".equals(impWorks.getSex()));
            works.setRegion(region);
            works.setVotenum(0);
            works.setName(impWorks.getWorksName());
            works.setAuthor(impWorks.getName());
            works.setInstructor(impWorks.getInstructor());
            works.setInstructorMobile(impWorks.getInstructorMobile());
            works.setSchool(impWorks.getSchool());
            works.setDescription(impWorks.getDescription());
            //图片塞值
            Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
            WorksPictureItem workspictureitem = new WorksPictureItem();
            workspictureitem.setWorks(works);
            Picture picture = new Picture();
            picture.setPath(impWorks.getWorksImg());
            picture.setOrginpath(impWorks.getWorksImg().replace("thumb_", ""));
            workspictureitem.setPicture(picture);
            worksPictureItemSet.add(workspictureitem);
            works.setWorksPictureItemSet(worksPictureItemSet);
            getWorksDAO().add(works);
            //保存作品类型
            workstag.setWorks(works);
            workstag.setInvalid(true);
            workstag.setTag(tagList.get(0));
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
            //保存角色类型
            UserInfo userinfo = getUserInfoDAO().get(Long.valueOf(userid));
            if ("3".equals(userinfo.getType())) {
                userinfo.setType("6");
            } else {
                userinfo.setType("2");
            }
            getUserInfoDAO().update(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    public Works getWorksByArtistId(Works works){
        return super.getWorksDAO().getWorksByArtistId(works);
    }

	@Override
	public int getArtistByRegion(Region region) {
		return getWorksDAO().getArtistByRegion(region);
	}

	@Override
	public List<WorksTagItem> findByKeywords(String keywords, String index, String size) {
		return getWorksDAO().findByKeywords(keywords,index,size);
	}

	@Override
	public List<WorksTagItem> findList(Integer index,Integer size) {
		return getWorksDAO().findList(index,size);
	}

	@Override
	public Integer countByKeywords(String keywords) {
		return getWorksDAO().countByKeywords(keywords);
	}


	@Override
	public List<WorksTagItem> findListByType(Integer type, String size,
			String index,String condition,String age,String awards) {
		return getWorksDAO().findListByType(type,size,index,condition,age,awards);
	}

	@Override
	public Integer findCountByType(Integer type,String age,String awards) {
		return getWorksDAO().findCountByType(type,age,awards);
	}

	@Override
	public List<WorksTagItem> findPassWorks() {
		return getWorksDAO().findPassWorks();
	}

	@Override
	@Transactional
	public void uploadImg(GameVo game, Long id) {
            WorksPictureItem workspictureitem = getWorksPictureItemDAO().getByWorksid(id);
            Picture picture = workspictureitem.getPicture();
            picture.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/thumb_"
                            + game.getPath());
            picture.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/"
                                 + game.getPath());
            getPictureDAO().update(picture);
		
	}

	@Override
	public List<Works> getWorksByTime(Integer index,Integer size) {
		return getWorksDAO().getWorksByTime(index,size);
	}

}
