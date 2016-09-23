package com.myhome.dao.impl;

import java.util.List;

import com.myhome.dao.IGameDAO;
import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




@Component("gameDAOHibernateImpl")
public class GameDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IGameDAO {

    

    



    @Override
    public Game get(Long id) {
        return (Game) super.get(id);
    }
    
    
    public void add(Game game) {
        // TODO 待完成
    }
    
    

    
    @Override
    protected Class<?> getEntityClass() {
        return Game.class;
    }


	@Override
	public List<Game> findList() {
		Session session = super.hibernateTemplate.getSessionFactory().openSession();
		int  i=0;
		try {
	        String hql="from Game where invalid = false ";
	        Query query =session.createQuery(hql);
	        return query.list();
        } catch (Exception e) {
	        e.printStackTrace();
	        return null;
        }finally{
        	session.close();
        }
    }


	@Override
	public void addmodel(GameWorksItem gameworks) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.save(gameworks);
		session.close();
    }

}
