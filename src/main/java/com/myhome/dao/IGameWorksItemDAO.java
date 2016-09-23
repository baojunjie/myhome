package com.myhome.dao;

import com.myhome.entity.GameWorksItem;



public interface IGameWorksItemDAO extends IDAO {

    public GameWorksItem get(Long id);

	public int addGameWorksItemMobile(GameWorksItem gameWorksItem,String id) throws Exception;




}
