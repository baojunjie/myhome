package com.myhome.dao;

import com.myhome.entity.ArtistInfo;



public interface IArtistInfoDAO extends IDAO {

    public ArtistInfo get(Long id);

	public Long addArtistInfoMobil(ArtistInfo artistInfo) throws Exception;

	public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception;




}

