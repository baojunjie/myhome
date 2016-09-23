package com.myhome.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.GameWorksItem;
import com.myhome.entity.Works;
import com.myhome.service.IGameWorksItemService;

@Component("gameWorksItemServiceImpl")
public class GameWorksItemServiceImpl extends AbstractServiceImpl implements IGameWorksItemService {

    @Override
    @Transactional(readOnly = true)
    public GameWorksItem get(Long id) {
        return getGameWorksItemDAO().get(id);
    }
    @Transactional
    public void add(GameWorksItem gameWorksItem) {
        // TODO 待完成
    }

    @Transactional
    public int update(GameWorksItem gameWorksItem) {
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

    /**
     * 
     * 
     * 
     */
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
    public boolean addGameWorksItemMobile(GameWorksItem gameWorksItem,String[] ids,int status) throws Exception{
        
        int i=0;
        for(String id:ids){
            int k= super.getGameWorksItemDAO().addGameWorksItemMobile(gameWorksItem,id);
            Works works =super.getWorksDAO().get(Long.parseLong(id));
            works.setStatus(status);
            super.getWorksDAO().updateWorksMobile(works);
             i++;
        }
        
        return ids.length==i;
    }

    @Override
    @Transactional
    public void addGameWorksItem(GameWorksItem gameWorksItem) {
        
        super.getGameWorksItemDAO().add(gameWorksItem);
        
    }

    @Override
    @Transactional
    public void getGameWorksItem(GameWorksItem gameWorksItem) {
        
          super.getGameWorksItemDAO().get(27l);
        
    }
}