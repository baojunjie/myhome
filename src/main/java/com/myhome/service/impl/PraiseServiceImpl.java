package com.myhome.service.impl;

import java.util.List;

import com.myhome.entity.ArtistPraise;
import com.myhome.entity.Praise;
import com.myhome.entity.WorksPraise;
import com.myhome.service.IPraiseService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("praiseServiceImpl")
public class PraiseServiceImpl extends AbstractServiceImpl 
        implements IPraiseService {

    

    



    @Override
    @Transactional(readOnly=true)
    public Praise get(Long id) {
        return getPraiseDAO().get(id);
    }
    
    
    @Override
    @Transactional
    public void add(Praise praise) {
    	getPraiseDAO().add(praise);
    }
    
    
    @Transactional
    public int update(Praise praise) {
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


	@Override
	public List<WorksPraise> getByUserAndWorks(Long works_id, Long user_id) {
		return getPraiseDAO().getByUserAndWorks(works_id,user_id);
	}


	@Override
	public List<ArtistPraise> getByUserAndArtist(Long artist_id, Long user_id) {
		 return getPraiseDAO().getByUserAndArtist(artist_id,user_id);
	}
    
    
    

}
