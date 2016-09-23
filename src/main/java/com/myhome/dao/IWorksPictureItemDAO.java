package com.myhome.dao;

import com.myhome.entity.WorksPictureItem;



public interface IWorksPictureItemDAO extends IDAO {

    public WorksPictureItem get(Long id);

	public Long addWorksPictureItemMobile(WorksPictureItem worksPictureItem);

    public WorksPictureItem getByWorksid(Long id);

	public WorksPictureItem getByUsersid(String userid);




}
