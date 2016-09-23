package com.myhome.dao;

import java.util.List;
import java.util.Map;

import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;

public interface IAuthenticationDAO extends IDAO {

    public Authentication get(Long id);

    public Authentication getMobileByAuthenticationIdAndPaswword(Authentication uthentication)
                                                                                              throws Exception;

    public Authentication getMobileAuthenticationByName(Authentication authentication)
                                                                                      throws Exception;

    public int getMobileAuthenticationByNameCount(Authentication authentication)
            throws Exception;

    public Long addMobileAuthentication(Authentication authentication) throws Exception;

    public Long updateMobileAuthenticationPassword(Authentication authentication) throws Exception;

    public Authentication getMobileByAuthenticationNameAndPaswword(Authentication au)
                                                                                     throws Exception;

    public Map<String, Object> getUserArtistInfoMobile(Long id) throws Exception;

    public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception;

    public List<Authentication> findByLogin(String mobile);

    public void updateArtistInfoMobilePhoto(ArtistInfo artistInfo);

    /**
     * 更具userid获取数据
     * @param userid
     * @return
     */
    public Authentication getByUserid(Long userid);

    public Authentication getByUseridAndType(Long userid,String type);

    public List<Authentication> findByLoginOrName(String mobile);

    public List<Authentication> getMobileAuthenticationByToken(String token,String type) throws Exception;

}
