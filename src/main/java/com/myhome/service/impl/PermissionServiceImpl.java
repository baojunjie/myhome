package com.myhome.service.impl;

import com.myhome.entity.Permission;
import com.myhome.service.IPermissionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("permissionServiceImpl")
public class PermissionServiceImpl extends AbstractServiceImpl 
        implements IPermissionService {

    

    



    @Override
    @Transactional(readOnly=true)
    public Permission get(Long id) {
        return getPermissionDAO().get(id);
    }
    
    
    @Transactional
    public void add(Permission permission) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(Permission permission) {
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
    
    
    

}
