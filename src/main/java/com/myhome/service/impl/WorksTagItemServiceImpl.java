package com.myhome.service.impl;

import com.myhome.service.IWorksTagItemService;
import com.myhome.entity.WorksTagItem;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("worksTagItemServiceImpl")
public class WorksTagItemServiceImpl extends AbstractServiceImpl 
        implements IWorksTagItemService {

    @Transactional(readOnly=true)
    public WorksTagItem get(Long id) throws Exception {
        return getWorksTagItemDAO().get(id);
    }
    
    
    @Transactional
    public void add(WorksTagItem worksTagItem) {
    	getWorksTagItemDAO().add(worksTagItem);
    }
    
    
    @Transactional
    public int update(WorksTagItem worksTagItem) {
        return getWorksDAO().update(worksTagItem);
    }
    
    
    @Transactional
    public int remove(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int delete(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }


	@Override
	@Transactional
    public Long addWorksTagItemMobile(WorksTagItem worksTagItem) throws Exception {
	    
	    
	    return super.getWorksTagItemDAO().addWorksTagItemMobile(worksTagItem);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    @Transactional
    public WorksTagItem getByWorksid(Long id) {
        return getWorksTagItemDAO().getByWorksid(id);
    }

}

