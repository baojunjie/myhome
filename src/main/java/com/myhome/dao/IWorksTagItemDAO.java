package com.myhome.dao;

import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;



public interface IWorksTagItemDAO extends IDAO {

    public WorksTagItem get(Long id) throws Exception;

	public Long addWorksTagItemMobile(WorksTagItem worksTagItem) throws Exception;

	public void updateWorksTagItem(WorksTagItem worksTagItem) throws Exception;

    public WorksTagItem getByWorksid(Long id);





}
