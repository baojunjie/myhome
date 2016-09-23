package com.myhome.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.myhome.dao.IVerificationCodeDAO;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.VerificationCode;
import com.myhome.service.IAuthenticationService;
import com.myhome.utils.ReturnData;
import com.myhome.utils.UtilMD5Encoder;

@Component("authenticationServiceImpl")
public class AuthenticationServiceImpl extends AbstractServiceImpl implements
                                                                  IAuthenticationService {

    @Autowired
    private IVerificationCodeDAO verificationCodeDAO;

    @Override
    @Transactional(readOnly = true)
    public Authentication get(Long id) {
        return getAuthenticationDAO().get(id);
    }

    @Transactional
    public void add(Authentication authentication) {
        // TODO 待完成
    }

    @Override
    @Transactional
    public void update(Authentication authentication) {
        getAuthenticationDAO().update(authentication);
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
    public Authentication getMobileByAuthenticationIdAndPaswword(Authentication uthentication)
                                                                                              throws Exception {

        return getAuthenticationDAO().getMobileByAuthenticationIdAndPaswword(uthentication);
    }

    /**
     * 用户注册，根据用户名查询用户是否已注册
     * TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    @Transactional(readOnly = true)
    public Authentication getMobileAuthenticationByName(Authentication authentication)
                                                                                      throws Exception {

        return getAuthenticationDAO().getMobileAuthenticationByName(authentication);
    }

    /**
     * 用户注册，根据用户名查询用户是否已注册
     * 返回数字
     */
    @Override
    @Transactional(readOnly = true)
    public int getMobileAuthenticationByNameCount(Authentication authentication)
                                                                                      throws Exception {

        return getAuthenticationDAO().getMobileAuthenticationByNameCount(authentication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Authentication> getMobileAuthenticationByToken(String token,String type) throws Exception {

        return getAuthenticationDAO().getMobileAuthenticationByToken(token,type);
    }

    @Override
    public Long addMobileAuthentication(Authentication authentication) throws Exception {
        super.getAuthenticationDAO().addMobileAuthentication(authentication);
        return authentication.getId();
    }

    /**
     * app
     * 修改密码
     * TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    public boolean updateMobileAuthenticationPassword(Authentication authentication)
                                                                                    throws Exception {
        super.getAuthenticationDAO().updateMobileAuthenticationPassword(authentication);
        return authentication.getId() > 0;

    }

    @Override
    public Authentication getMobileByAuthenticationNameAndPaswword(Authentication au)
                                                                                     throws Exception {

        return getAuthenticationDAO().getMobileByAuthenticationNameAndPaswword(au);
    }

    /**
     * 获取用户登录信息
     * getUserArtistInfoMobile
     * @author gwb
     * @param id
     * @return
     * 2015年9月12日 下午2:17:02
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getUserArtistInfoMobile(Long id) throws Exception {
        return getAuthenticationDAO().getUserArtistInfoMobile(id);
    }

    @Override
    @Transactional
    public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception {
        getAuthenticationDAO().updateArtistInfoMobile(artistInfo);
    }

    @Override
    public List<Authentication> findByLogin(String mobile) {
        return getAuthenticationDAO().findByLogin(mobile);
    }

    @Override
    @Transactional
    public void updateArtistInfoMobilePhoto(ArtistInfo artistInfo) {

        getAuthenticationDAO().updateArtistInfoMobilePhoto(artistInfo);

    }

    @Override
    @Transactional
    public String getMobilePwd(Authentication authentication, String code) throws Exception {
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        List<VerificationCode> listVer = verificationCodeDAO.findByMobile(authentication.getLogin()
                                                                          + "", code);
        if (listVer.size() == 0) {
            returnData.setReturnCode("1002");
            returnData.setData(json);
            returnData.setMsg("验证码不正确");
            return returnData.toString();
        }

        Authentication auth = super.getAuthenticationDAO().getMobileAuthenticationByName(
            authentication);
        if (null != auth && null != auth.getId()) {
            String pwdSalt = UtilMD5Encoder.encodePassword(authentication.getPassword(),
                auth.getSalt());
            authentication.setPassword(pwdSalt);
            authentication.setId(auth.getId());
            authentication.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
            super.getAuthenticationDAO().updateMobileAuthenticationPassword(authentication);
        }
        return returnData.toString();
    }

    @Override
    public Authentication getByUserid(Long userid) {
        return getAuthenticationDAO().getByUserid(userid);
    }

    @Override
    public Authentication getByUseridAndType(Long userid,String type) {
        return getAuthenticationDAO().getByUseridAndType(userid,type);
    }

    @Override
    public List<Authentication> findByLoginOrName(String mobile) {
        return getAuthenticationDAO().findByLoginOrName(mobile);
    }
}
