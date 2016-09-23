package com.myhome.service;

import com.myhome.entity.WorksTagItem;



public interface IWorksTagItemService extends IService {

    public WorksTagItem get(Long id) throws Exception;

	public Long addWorksTagItemMobile(WorksTagItem worksTagItem) throws Exception;

    public WorksTagItem getByWorksid(Long id);
    /**
     * lqf
     * @param workstag
     */
	public void add(WorksTagItem workstag);

    public int update(WorksTagItem worksTagItem);





}
