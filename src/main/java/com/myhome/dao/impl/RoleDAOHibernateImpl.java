package com.myhome.dao.impl;

import com.myhome.dao.IRoleDAO;
import com.myhome.entity.Role;
import org.springframework.stereotype.Component;




@Component("roleDAOHibernateImpl")
public class RoleDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IRoleDAO {

    

    



    @Override
    public Role get(Long id) {
        return (Role) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return Role.class;
    }

}
