package com.myhome.service.impl;

import com.myhome.service.IUserRoleItemService;
import com.myhome.entity.UserRoleItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("userRoleItemServiceImpl")
public class UserRoleItemServiceImpl extends AbstractServiceImpl 
        implements IUserRoleItemService {

    

    



    @Override
    @Transactional(readOnly=true)
    public UserRoleItem get(Long id) {
        return getUserRoleItemDAO().get(id);
    }
    
    
    @Transactional
    public void add(UserRoleItem userRoleItem) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(UserRoleItem userRoleItem) {
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
