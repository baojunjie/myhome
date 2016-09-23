package com.myhome.service.impl;

import com.myhome.entity.Role;
import com.myhome.service.IRoleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("roleServiceImpl")
public class RoleServiceImpl extends AbstractServiceImpl 
        implements IRoleService {

    

    



    @Override
    @Transactional(readOnly=true)
    public Role get(Long id) {
        return getRoleDAO().get(id);
    }
    
    
    @Transactional
    public void add(Role role) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(Role role) {
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
