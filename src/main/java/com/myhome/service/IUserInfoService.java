package com.myhome.service;

import java.util.List;

import com.myhome.entity.UserInfo;
import com.myhome.entity.vo.UserInfoVO;

public interface IUserInfoService extends IService {

    public UserInfo get(Long id);

    public List<UserInfo> findByMobile(String mobile);

    public void add(UserInfo userInfo);

    public List<UserInfo> findByMobileAndPassword(String mobile, String password);

    /**
     * 更具姓名找用户
     * lqf
     * @param usename
     * @return
     */
    public List<UserInfo> findByName(String usename);

    public void updateUserMobile(UserInfo userInfo) throws Exception;

    /**
     * 更新
     * lqf
     * @param userinfo
     */
    public int update(UserInfo userinfo);

    /**
     * 编辑信息
     * @param userid
     * @param userinfo
     */
    public void editInfo(Integer userid, UserInfoVO userinfo);

}
