package com.myhome.dao.impl;

import com.myhome.entity.UserArtistItem;
import com.myhome.dao.IUserArtistItemDAO;
import org.springframework.stereotype.Component;




@Component("userArtistItemDAOHibernateImpl")
public class UserArtistItemDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IUserArtistItemDAO {

    

    



    @Override
    public UserArtistItem get(Long id) {
        return (UserArtistItem) super.get(id);
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return UserArtistItem.class;
    }

}
