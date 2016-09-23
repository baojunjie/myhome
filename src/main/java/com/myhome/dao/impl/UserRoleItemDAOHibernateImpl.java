package com.myhome.dao.impl;

import com.myhome.entity.UserRoleItem;
import com.myhome.dao.IUserRoleItemDAO;
import org.springframework.stereotype.Component;




@Component("userRoleItemDAOHibernateImpl")
public class UserRoleItemDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IUserRoleItemDAO {

    

    



    @Override
    public UserRoleItem get(Long id) {
        return (UserRoleItem) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return UserRoleItem.class;
    }

}
