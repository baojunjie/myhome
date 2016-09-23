package com.myhome.service.impl;

import com.myhome.service.IUserArtistItemService;
import com.myhome.entity.UserArtistItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("userArtistItemServiceImpl")
public class UserArtistItemServiceImpl extends AbstractServiceImpl 
        implements IUserArtistItemService {

    

    



    @Override
    @Transactional(readOnly=true)
    public UserArtistItem get(Long id) {
        return getUserArtistItemDAO().get(id);
    }
    
    
    @Transactional
    public void add(UserArtistItem userArtistItem) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(UserArtistItem userArtistItem) {
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
