package com.myhome.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IUserInfoDAO;
import com.myhome.entity.Region;
import com.myhome.entity.UserInfo;
import com.myhome.entity.vo.UserInfoVO;
import com.myhome.service.IUserInfoService;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.PicturePath;
import com.myhome.utils.Tools;

@Component("userInfoServiceImpl")
public class UserInfoServiceImpl extends AbstractServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Override
    @Transactional(readOnly = true)
    public UserInfo get(Long id) {
        return getUserInfoDAO().get(id);
    }

    @Override
    @Transactional
    public void add(UserInfo userInfo) {
        userInfoDAO.add(userInfo);
    }

    @Override
    @Transactional
    public int update(UserInfo userInfo) {
        userInfoDAO.update(userInfo);
        return 1;
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

    @Transactional
    @Override
    public List<UserInfo> findByMobile(String mobile) {
        return userInfoDAO.findByMobile(mobile);
    }

    @Override
    public List<UserInfo> findByMobileAndPassword(String mobile, String password) {
        return userInfoDAO.findByMobileAndPassword(mobile, password);
    }

    @Override
    public List<UserInfo> findByName(String usename) {
        return userInfoDAO.findByName(usename);
    }

    @Override
    @Transactional
    public void updateUserMobile(UserInfo userInfo) throws Exception {
        boolean falg = super.getTeacherDao().getMobileToken(userInfo.getToken(), "t_user_info");
        if (!falg) {
            return;
        }
        String img = userInfo.getOriginImg();
        if (Tools.notEmpty(img)) {
            Map<String, String> map = ImageUpload.imageUpload(img, PicturePath.user);
            userInfo.setOriginImg(map.get("originImg"));
            userInfo.setImg(map.get("thumb_path"));
        }
        userInfoDAO.updateUserMobile(userInfo);
    }

    @Override
    @Transactional
    public void editInfo(Integer userid, UserInfoVO userinfo) {
        try {
            UserInfo model = getUserInfoDAO().get(Long.valueOf(userid));
            Region region = new Region();
            region.setId(Long.valueOf(userinfo.getRegionid()));
            model.setNickName(userinfo.getNickName());
            model.setMale(userinfo.getMalestr() == 1);
            model.setNation(userinfo.getNation());
            model.setBirthday(userinfo.getBirthday());
            model.setProfession(userinfo.getProfession());
            model.setMobile(userinfo.getMobile());
            model.setRegion(region);
            getUserDAO().update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
