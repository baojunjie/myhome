package com.myhome.dao.impl;

import com.myhome.entity.Permission;
import com.myhome.dao.IPermissionDAO;
import org.springframework.stereotype.Component;




@Component("permissionDAOHibernateImpl")
public class PermissionDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IPermissionDAO {

    

    



    @Override
    public Permission get(Long id) {
        return (Permission) super.get(id);
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return Permission.class;
    }

}
