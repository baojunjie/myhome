package com.myhome.dao.impl;

import com.myhome.dao.IWorksTagItemDAO;
import com.myhome.entity.WorksTagItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component("worksTagItemDAOHibernateImpl")
public class WorksTagItemDAOHibernateImpl extends AbstractDAOHibernateImpl implements IWorksTagItemDAO {

	@Override
	public WorksTagItem get(Long id){
		return (WorksTagItem) super.get(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return WorksTagItem.class;
	}

	@Override
	public Long addWorksTagItemMobile(WorksTagItem worksTagItem) throws Exception{
		Session session = super.hibernateTemplate.getSessionFactory().openSession();
		session.save(worksTagItem);
		session.close();
		return worksTagItem.getId();
	}

	
	
	@Override
    public void updateWorksTagItem(WorksTagItem worksTagItem) throws Exception {
		Session session=super.hibernateTemplate.getSessionFactory().openSession();
		String sql=" update t_works_tag_item set tag_id=:tag_id where id=:id";
		Query qu=	session.createSQLQuery(sql);
		qu.setLong("tag_id", worksTagItem.getTag().getId());
		qu.setLong("id", worksTagItem.getId());
		qu.executeUpdate();
		session.close();
    }
	
	
    @Override
    public WorksTagItem getByWorksid(Long id) {
        Session s=getHibernateTemplate().getSessionFactory().openSession();
        try{
            String hql= " from WorksTagItem  where works.id = :worksid  ";
            Query query =  s.createQuery(hql);
            query.setLong("worksid", id);
            return (query.list()==null||query.list().size()==0)?null:(WorksTagItem) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            s.close();
        }
        }

}

