package com.myhome.dao.impl;

import java.util.List;

import com.myhome.entity.ArtistPraise;
import com.myhome.entity.Praise;
import com.myhome.entity.WorksPraise;
import com.myhome.dao.IPraiseDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




@Component("praiseDAOHibernateImpl")
public class PraiseDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IPraiseDAO {

    

    



    @Override
    public Praise get(Long id) {
        return (Praise) super.get(id);
    }
    
    
    public void add(Praise praise) {
        // TODO 待完成
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return Praise.class;
    }


	@Override
	public List<WorksPraise> getByUserAndWorks(Long works_id, Long user_id) {
		Session s=getHibernateTemplate().getSessionFactory().openSession();
		try{
			String Hql= "from WorksPraise where user.id = :userid and works.id = :worksid and invalid = false  ";
			Query query=s.createQuery(Hql);
			query.setInteger("userid",Integer.valueOf(String.valueOf(user_id)));
			query.setInteger("worksid",Integer.valueOf(String.valueOf(works_id)));
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			s.close();
		}
		}


	@Override
	public List<ArtistPraise> getByUserAndArtist(Long artist_id, Long user_id) {
		Session s=getHibernateTemplate().getSessionFactory().openSession();
		try{
			String Hql= "from ArtistPraise where user.id = :userid and artist.id = :artistid and invalid = false  ";
			Query query=s.createQuery(Hql);
			query.setInteger("userid",Integer.valueOf(String.valueOf(user_id)));
			query.setInteger("artistid",Integer.valueOf(String.valueOf(artist_id)));
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			s.close();
		}
		}

}
