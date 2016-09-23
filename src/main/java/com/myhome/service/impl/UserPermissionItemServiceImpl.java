package com.myhome.service.impl;

import com.myhome.service.IUserPermissionItemService;
import com.myhome.entity.UserPermissionItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("userPermissionItemServiceImpl")
public class UserPermissionItemServiceImpl extends AbstractServiceImpl 
        implements IUserPermissionItemService {

    

    



    @Override
    @Transactional(readOnly=true)
    public UserPermissionItem get(Long id) {
        return getUserPermissionItemDAO().get(id);
    }
    
    
    @Transactional
    public void add(UserPermissionItem userPermissionItem) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(UserPermissionItem userPermissionItem) {
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
