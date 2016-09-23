package com.myhome.service;

import com.myhome.entity.GameWorksItem;



public interface IGameWorksItemService extends IService {

    public GameWorksItem get(Long id);

	public boolean addGameWorksItemMobile(GameWorksItem gameWorksItem ,String[] ids,int status) throws Exception;
//test
	public void addGameWorksItem(GameWorksItem gameWorksItem);

	public void getGameWorksItem(GameWorksItem gameWorksItem);




}
