package com.myhome.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.ArtistInfo;
import com.myhome.service.IArtistInfoService;
import com.myhome.utils.BaseCodeParam;

@Component("artistInfoServiceImpl")
public class ArtistInfoServiceImpl extends AbstractServiceImpl implements IArtistInfoService {

    @Override
    @Transactional(readOnly = true)
    public ArtistInfo get(Long id) {
        return getArtistInfoDAO().get(id);
    }

    @Transactional
    public void add(ArtistInfo artistInfo) {
        // TODO 待完成
    }

    @Transactional
    public int update(ArtistInfo artistInfo) {
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

    /**
     * app 我要报名 TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    @Transactional
    public Long addArtistInfoMobil(ArtistInfo artistInfo) throws Exception {
        return super.getArtistInfoDAO().addArtistInfoMobil(artistInfo);

    }

    @Override
    @Transactional
    public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception {
        super.getArtistInfoDAO().updateArtistInfoMobile(artistInfo);
    }

	@Override
	public void addImg(String filename, String artistId) {
		ArtistInfo artistInfo = getArtistInfoDAO().get(Long.valueOf(artistId));
		artistInfo.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/artist/thumb_"+filename);
		artistInfo.setOrginimg(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/artist/"+filename);
		getArtistInfoDAO().update(artistInfo);
	}

}
