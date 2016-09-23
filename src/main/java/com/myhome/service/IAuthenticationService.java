package com.myhome.service;

import java.util.List;
import java.util.Map;

import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;

public interface IAuthenticationService extends IService {

    public Authentication get(Long id);

    public Authentication getMobileByAuthenticationIdAndPaswword(Authentication authentication)
                                                                                               throws Exception;

    public Authentication getMobileAuthenticationByName(Authentication authentication)
                                                                                      throws Exception;

    public int getMobileAuthenticationByNameCount(Authentication authentication)
            throws Exception;

    public Long addMobileAuthentication(Authentication authentication) throws Exception;

    public boolean updateMobileAuthenticationPassword(Authentication authentication)
                                                                                    throws Exception;

    public Authentication getMobileByAuthenticationNameAndPaswword(Authentication au)
                                                                                     throws Exception;

    public Map<String, Object> getUserArtistInfoMobile(Long id) throws Exception;

    public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception;

    public List<Authentication> findByLogin(String mobile);

    public void update(Authentication model);

    public void updateArtistInfoMobilePhoto(ArtistInfo artistInfo) throws Exception;

    public String getMobilePwd(Authentication authentication, String code) throws Exception;

    /**
     * 更具userid获取
     * 
     */
    public Authentication getByUserid(Long userid);

    public Authentication getByUseridAndType(Long userid,String type);

    public List<Authentication> findByLoginOrName(String mobile);

    public List<Authentication> getMobileAuthenticationByToken(String token, String type) throws Exception;

}
