package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;



public interface IGameDAO extends IDAO {

    public Game get(Long id);

	public List<Game> findList();

	public void addmodel(GameWorksItem gameworks);




}
