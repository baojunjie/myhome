package com.myhome.service;

import com.myhome.entity.WorksPictureItem;



public interface IWorksPictureItemService extends IService {

    public WorksPictureItem get(Long id);

	public Long addWorksPictureItemMobile(WorksPictureItem worksPictureItem);

    public WorksPictureItem getByWorksid(Long id);




}
