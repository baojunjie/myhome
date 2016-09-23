package com.myhome.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IPageDAO;
import com.myhome.entity.Page;
import com.myhome.entity.User;
import com.myhome.utils.Tools;

@Component("pageDAOHibernateImpl")
public class PageDAOHibernateImpl extends AbstractDAOHibernateImpl implements IPageDAO {

	@Override
	public List<Page> get(int type)  throws Exception{

        Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        String hql = " from Page  where invalid=false and type=:type order by sort ASC";
        Query query = session.createQuery(hql);
        query.setInteger("type", type);
        List<Page> list = query.list();
        return list;
    }


	@Override
	protected Class<?> getEntityClass() {
		return Page.class;
	}

	@Override
	public List<Page> addTest(Page page) throws Exception {
		String sql = "update Page set name=:name where idssss=6";
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		page.setId(10l);
		session.delete("from Page as c where c.id>8");
		// Query query=session.createQuery(sql);
		// query.setString("name", page.getName());
		// query.executeUpdate();
		return null;
	}

	@Override
	public Object addTest2(Page page) throws Exception {

		// String sql="update Page set name=:name where id=5";
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.save(page);
		// session.delete("from Page as c where c.id>"+page.getId());
		// Page ss= (Page) session.get(Page.class, page.getId());
		// session.merge(page);
		// Page page1= (Page) super.get(page.getId());
		// session.merge(page);
		// Query query=session.createQuery(sql);
		// query.setString("name", page.getName());
		// query.executeUpdate();
		return null;
	}

	@Override
	public void updateTest(Page page) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Page page1 = (Page) session.get(Page.class, page.getId());
		System.out.println(page1.getName());
		Tools.copyProperties(page, page1);
		System.out.println(page1.getName());
		session.update(page1);
	}

	
	@Override
    public void updateUser(User user) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		User page1 = (User) session.get(User.class, user.getId());
		System.out.println(page1.getUserInfo());
		Tools.copyProperties(user.getUserInfo(), page1.getUserInfo());
		session.update(page1);
	    
    }

	@Override
	public List<Page> getByType(int type) {
		Session session = super.getHibernateTemplate().getSessionFactory().openSession();
		List<Page> list = null;
		try{
			String hql = " from Page  where invalid = false and type=:type order by sort";
			Query query = session.createQuery(hql);
			query.setInteger("type", type);
			list = query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return list;
	}

}
