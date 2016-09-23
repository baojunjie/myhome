package com.myhome.service;

import com.myhome.entity.ArtistInfo;



public interface IArtistInfoService extends IService {

    public ArtistInfo get(Long id);

	public Long addArtistInfoMobil(ArtistInfo artistInfo) throws Exception;

	public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception;
	/**
	 * 画家批量导入时添加图片
	 * @param filename
	 * @param artistId
	 */
	public void addImg(String filename, String artistId);




}

