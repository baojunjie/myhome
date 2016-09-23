package com.myhome.dao.impl;

import com.myhome.entity.WorksPictureItem;
import com.myhome.dao.IWorksPictureItemDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




@Component("worksPictureItemDAOHibernateImpl")
public class WorksPictureItemDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IWorksPictureItemDAO {

    

    



    @Override
    public WorksPictureItem get(Long id) {
        return (WorksPictureItem) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return WorksPictureItem.class;
    }





	@Override
    public Long addWorksPictureItemMobile(WorksPictureItem worksPictureItem) {
		//super.hibernateTemplate.save(worksPictureItem);
	    Session session=super.getHibernateTemplate().getSessionFactory().openSession();
	    session.save(worksPictureItem);
	    session.close();
	    return worksPictureItem.getId();
    }





    @Override
    public WorksPictureItem getByWorksid(Long id) {
        Session s=getHibernateTemplate().getSessionFactory().openSession();
        try{
            String hql= " from WorksPictureItem  where works.id = :worksid  ";
            Query query =  s.createQuery(hql);
            query.setLong("worksid", id);
            return (query.list()==null||query.list().size()==0)?null:(WorksPictureItem) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            s.close();
        }
    }





	@Override
	public WorksPictureItem getByUsersid(String userid) {
        Session s=getHibernateTemplate().getSessionFactory().openSession();
        try{
            String hql= " from WorksPictureItem  where works.artist.artistInfo.user.id = :userid  ";
            Query query =  s.createQuery(hql);
            query.setLong("userid", Long.valueOf(userid));
            return (query.list()==null||query.list().size()==0)?null:(WorksPictureItem) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            s.close();
        }
    }

}
