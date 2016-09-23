package com.myhome.dao.impl;

import com.myhome.dao.IRolePermissionItemDAO;
import com.myhome.entity.RolePermissionItem;
import org.springframework.stereotype.Component;




@Component("rolePermissionItemDAOHibernateImpl")
public class RolePermissionItemDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IRolePermissionItemDAO {

    

    



    @Override
    public RolePermissionItem get(Long id) {
        return (RolePermissionItem) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return RolePermissionItem.class;
    }

}
