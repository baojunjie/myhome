package com.myhome.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IWithPictureDAO;
import com.myhome.entity.WithPicture;
import com.myhome.utils.Tools;

@Component("withPictureDAOHibernateImpl")
public class WithPictureDAOHibernateImpl extends AbstractDAOHibernateImpl implements
                                                                         IWithPictureDAO {

    @Override
    public WithPicture updateMobileWithPicture(WithPicture withPicture) throws Exception {
        WithPicture wp = (WithPicture) super.get(withPicture.getId());
        Tools.copyProperties(withPicture, wp);
        wp.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        super.update(wp);
        return withPicture;
    }

    @Override
    public void deleteMobileWithPicture(WithPicture withPicture) throws Exception {
        super.delete(withPicture.getId());
    }

    @Override
    public WithPicture getMobileWithPicture(WithPicture withPicture) throws Exception {
        return (WithPicture) super.get(withPicture.getId());
    }

    @Override
    public List<WithPicture> getMobileWithPictureList(WithPicture withPicture) throws Exception {

        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        StringBuffer hql = new StringBuffer();
        List<String> ls = new ArrayList<String>();
        hql.append(" from WithPicture where 1=1 ");
        if (withPicture.getStatus() > 1) {
            hql.append(" and status=? ");
            ls.add(withPicture.getStatus() + "");
        } else if (withPicture.getUser() != null && withPicture.getUser().getId() > 0) {
            hql.append(" and user.id=? ");
            ls.add(withPicture.getUser().getId() + "");
        } else if (withPicture.getType() > 0) {
            hql.append(" and type =? ");
            ls.add(withPicture.getType() + "");
        }

        Query query = session.createQuery(hql.toString());

        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i) + "");
        }
        return query.list();

    }

	
	@Override
    protected Class getEntityClass() {
	    return WithPicture.class;
    }

	@Override
    public WithPicture addMobileWithPicture(WithPicture withPicture) throws Exception {
		super.add(withPicture);
		return withPicture;
    }
	@Override
    public WithPicture get(Long id) {
        return (WithPicture) super.get(id);
    }

    @Override
    public List<WithPicture> findByUser(Long id) {
        Session s=getHibernateTemplate().getSessionFactory().openSession();
        try{
        StringBuffer hql=new StringBuffer();
        hql.append(" from WithPicture where 1=1 and invalid = false ");
           if(id!=null){
                hql.append(" and user.id=:userid ");
            }
        Query query=s.createQuery(hql.toString());
        query.setLong("userid", id);
        return query.list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            s.close();
        }
    }
}
