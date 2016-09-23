
package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.ISponsorsDao;
import com.myhome.entity.Sponsors;
import com.myhome.entity.Teacher;
import com.myhome.utils.Tools;

/**
 * 赞助商
 * 
 * @author gwb
 * @version $Id: SponsorsDAOHibernateImpl.java, v 0.1 2015年10月12日 下午3:48:30 gwb
 *          Exp $
 */
@Component("sponsorsDAOHibernateImpl")
public class SponsorsDAOHibernateImpl extends AbstractDAOHibernateImpl implements ISponsorsDao {

	@Override
	protected Class getEntityClass() {
		return Sponsors.class;
	}
	
	@Override
    public Sponsors get(Long id) {
        return (Sponsors) super.get(id);
    }

	@Override
	public Sponsors addMobileSponsors(Sponsors sponsors) throws Exception {
	//	Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		super.add(sponsors);
		return sponsors;
	}

	@Override
	public void updateMobileSponsors(Sponsors sponsors) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Sponsors sp = (Sponsors) session.get(Sponsors.class, sponsors.getId());
		Tools.copyProperties(sponsors, sp);
		session.update(sp);

	}

	@Override
	public void deleteMobileHSponsors(Sponsors sponsors) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(sponsors);
	}

	@Override
	public Sponsors getMobileSponsors(Sponsors children) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		List<Object> ls = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from Sponsors where 1=1 ");

		if (Tools.notEmpty(children.getId() + "")) {
			hql.append(" and id=?");
			ls.add(children.getId() + "");
		}else if (children.getUser()!=null&&children.getUser().getId()>0) {
			hql.append(" and user.id=?");
			ls.add(children.getUser().getId() + "");
		}else if (children.getStatus() >1) {
			hql.append(" and status=?");
			ls.add(children.getStatus() + "");
		}

		Query query = session.createQuery(hql.toString());
		for (int i = 0; i < ls.size(); i++) {
			query.setString(i, ls.get(i) + "");
		}
		Object object=query.uniqueResult();
		return  object==null?null:(Sponsors)object;
	}

	@Override
	public List<Sponsors> getMobileSponsorsList(Sponsors children) throws Exception {
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		List<Object> ls = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from Sponsors where 1=1 ");

		if (Tools.notEmpty(children.getUser().getId() + "")) {
			hql.append(" and userInfo.id=?");
			ls.add(children.getUser().getId() + "");
		}
		if (Tools.notEmpty(children.getId() + "")) {
			hql.append(" and id=?");
			ls.add(children.getId() + "");
		}
		if (Tools.notEmpty(children.getId() + "")) {
			hql.append(" and status=?");
			ls.add(children.getStatus() + "");
		}

		Query query = session.createQuery(hql.toString());
		for (int i = 0; i < ls.size(); i++) {
			query.setString(i, ls.get(i) + "");
		}

		return (List<Sponsors>) query.list();

	}

    @Override
    public Sponsors getByUserid(Long userid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from Sponsors where user.id = :userid ";
            Query query = s.createQuery(hql);
            query.setLong("userid",userid);
            return ((query.list() == null || query.list().size() == 0) ? null : (Sponsors) query.list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();

        }
    }

}
