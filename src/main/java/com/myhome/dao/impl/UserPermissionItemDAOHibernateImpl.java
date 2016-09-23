package com.myhome.dao.impl;

import com.myhome.dao.IUserPermissionItemDAO;
import com.myhome.entity.UserPermissionItem;
import org.springframework.stereotype.Component;




@Component("userPermissionItemDAOHibernateImpl")
public class UserPermissionItemDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IUserPermissionItemDAO {

    

    



    @Override
    public UserPermissionItem get(Long id) {
        return (UserPermissionItem) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return UserPermissionItem.class;
    }

}
