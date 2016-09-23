package com.myhome.service.impl;

import com.myhome.service.IRolePermissionItemService;
import com.myhome.entity.RolePermissionItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("rolePermissionItemServiceImpl")
public class RolePermissionItemServiceImpl extends AbstractServiceImpl 
        implements IRolePermissionItemService {

    

    



    @Override
    @Transactional(readOnly=true)
    public RolePermissionItem get(Long id) {
        return getRolePermissionItemDAO().get(id);
    }
    
    
    @Transactional
    public void add(RolePermissionItem rolePermissionItem) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(RolePermissionItem rolePermissionItem) {
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
