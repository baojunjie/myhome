package com.myhome.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IVerificationCodeDAO;
import com.myhome.entity.VerificationCode;

@Component("VerificationCodeDAOHibernateImpl")
public class VerificationCodeDAOHibernateImpl extends AbstractDAOHibernateImpl
		implements IVerificationCodeDAO {
	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return VerificationCode.class;
	}

	@Override
	public void save(VerificationCode model) {
		getHibernateTemplate().save(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<VerificationCode> findByMobile(String mobile,String verificationCode)  throws Exception{
		Session  s = hibernateTemplate.getSessionFactory().getCurrentSession();
//		Date createdDatetime = new Date(new Date().getTime() - 1800000);
		String Hql = "from VerificationCode where mobile =:mobile and Code =:verificationCode and invalid=false";
		Query query = s.createQuery(Hql);
		query.setString("mobile", mobile);
		query.setString("verificationCode", verificationCode);
		return query.list();
		
	}

	@Override
    public void saveMobile(String mobile, int code) throws Exception {
		Session  s = hibernateTemplate.getSessionFactory().getCurrentSession();
		VerificationCode verificationCode=new VerificationCode();
		verificationCode.setCode(code+"");
		verificationCode.setMobile(mobile);
	    s.save(verificationCode);
    }

	@Override
    public void upadteVerificationCode(String mobile) throws Exception {
		Session  s = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql=" update VerificationCode set invalid=true where mobile=:mobile";
		Query query=s.createQuery(hql);
		query.setString("mobile", mobile);
		query.executeUpdate();
		
    }
	
    @Override
    public int findCount(String mobile, Date date)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(date);
        Session session=  super.hibernateTemplate.getSessionFactory().openSession();
        String hql="select count(id) from VerificationCode where mobile=:mobile and createdDatetime > :startTime";
        Query query=session.createQuery(hql);
        query.setString("mobile", mobile);
        query.setString("startTime", todayStr+" 00:00:00");
        int count= Integer.valueOf(query.uniqueResult().toString());

        session.close();
        return count;

    }
}
