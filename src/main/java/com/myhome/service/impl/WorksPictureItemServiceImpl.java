package com.myhome.service.impl;

import com.myhome.entity.WorksPictureItem;
import com.myhome.service.IWorksPictureItemService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("worksPictureItemServiceImpl")
public class WorksPictureItemServiceImpl extends AbstractServiceImpl 
        implements IWorksPictureItemService {

    

    



    @Override
    @Transactional(readOnly=true)
    public WorksPictureItem get(Long id) {
        return getWorksPictureItemDAO().get(id);
    }
    
    
    @Transactional
    public void add(WorksPictureItem worksPictureItem) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(WorksPictureItem worksPictureItem) {
        // TODO 待完成
        throw new RuntimeException();
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
    public Long addWorksPictureItemMobile(WorksPictureItem worksPictureItem) {
	    
	   
	    return super.getWorksPictureItemDAO().addWorksPictureItemMobile(worksPictureItem);
    }


    @Override
    @Transactional
    public WorksPictureItem getByWorksid(Long id) {
        return getWorksPictureItemDAO().getByWorksid(id);
    }
    
    
    

}
