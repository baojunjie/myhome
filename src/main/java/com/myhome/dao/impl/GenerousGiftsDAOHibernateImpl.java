package com.myhome.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IGenerousGiftsDAO;
import com.myhome.entity.GenerousGifts;
@Component("generousGiftsDAOHibernateImpl")
public class GenerousGiftsDAOHibernateImpl extends AbstractDAOHibernateImpl implements IGenerousGiftsDAO{

    @Override
    protected Class<?> getEntityClass() {
        // TODO Auto-generated method stub
        return GenerousGifts.class;
    }
    @Override
    public GenerousGifts get(Long id) {
          return (GenerousGifts) super.get(id);
    }
	@Override
	public void addMobileGenerousGifts(GenerousGifts generousGifts)
			throws Exception {
		super.add(generousGifts);
	}
	@Override
	public List<GenerousGifts>  getMobileGenerousGifts(GenerousGifts generousGifts)
			throws Exception {
		String hql=" from GenerousGifts where user.id=?";
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql);
		query.setString(0, generousGifts.getUser().getId()+"");
		return query.list();
	}


}
