package com.myhome.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;
import com.myhome.entity.Works;
import com.myhome.entity.vo.GameVo;



public interface IGameService extends IService {

    public Game get(Long id);

	public Works addWeb(GameVo game,Integer userid,HttpServletRequest request);

	public String addMobielCompetition(ArtistInfo artistInfo, HttpServletRequest request) throws Exception;

	/**
	 * 
	 * @return
	 */
	public List<Game> findList();

	public void add(GameWorksItem gameworks);




}
