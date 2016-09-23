package com.myhome.dao;

import java.util.List;

import com.myhome.entity.ArtistPraise;
import com.myhome.entity.Praise;
import com.myhome.entity.WorksPraise;



public interface IPraiseDAO extends IDAO {

    public Praise get(Long id);

	public List<WorksPraise> getByUserAndWorks(Long works_id, Long user_id);

	public List<ArtistPraise> getByUserAndArtist(Long artist_id, Long user_id);




}
