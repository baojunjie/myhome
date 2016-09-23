package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.ITeacherDao;
import com.myhome.entity.Teacher;
import com.myhome.utils.Tools;

/**
 * 老师
 * 
 * @author gwb
 * @version $Id: TeacherDAOHibernateImpl.java, v 0.1 2015年10月12日 下午3:48:54 gwb Exp $
 */
@Component("teacherDAOHibernateImpl")
public class TeacherDAOHibernateImpl extends AbstractDAOHibernateImpl implements ITeacherDao {
    @Override
    protected Class getEntityClass() {
        return Teacher.class;
    }

    @Override
    public Teacher get(Long id) {
        return (Teacher) super.get(id);
    }

    /**
     * 新增老师
     * @see com.myhome.dao.ITeacherDao#addMobileTeacher(com.myhome.entity.Teacher)
     */
    @Override
    public Long addMobileTeacher(Teacher teacher) throws Exception {
        Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.save(teacher);
        return teacher.getId();
    }

    /**
     * 修改老师
     * @see com.myhome.dao.ITeacherDao#updateMobileTeacher(com.myhome.entity.Teacher)
     */
    @Override
    public void updateMobileTeacher(Teacher teacher) throws Exception {
        Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Teacher te = (Teacher) session.get(Teacher.class, teacher.getId());
        Tools.copyProperties(teacher, te);
        session.update(te);

    }

    /**
     * 
     * 根据用户id查询  老师信息 包括用户信息
     * @see com.myhome.dao.ITeacherDao#getMobileTeacher(com.myhome.entity.User)
     */
    @Override
    public Teacher getMobileTeacher(Teacher teacher) throws Exception {
        Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        List<String> ls = new ArrayList<String>();
        StringBuffer hql = new StringBuffer();
        hql.append(" from Teacher where 1=1 ");
        if (Tools.notEmpty(teacher.getId() + "")) {
            hql.append(" and id=? ");
            ls.add(teacher.getId() + "");
        }
        if (teacher.getUser() != null && Tools.notEmpty(teacher.getUser().getId() + "")) {
            hql.append(" and user.id=? ");
            ls.add(teacher.getUser().getId() + "");
        }

        Query query = session.createQuery(hql.toString());

        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i) + "");
        }
        Object object = query.uniqueResult();
        return object == null ? null : (Teacher) object;
    }

    @Override
    public Teacher getByUserid(Long userid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from Teacher where user.id = :userid ";
            Query query = s.createQuery(hql);
            query.setLong("userid", userid);
            return ((query.list() == null || query.list().size() == 0) ? null : (Teacher) query
                .list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();

        }
    }

    //角色类型 1普通用（默认）,2画家，3受捐者，4老师，5赞助商,6同时是画家和受捐者
    @Override
    public void getAuthenticationCheck(Long id, Integer status, String type) throws Exception {
        String className = "";
        if (type.equals("3")) {
            className = "HelpChildren";
        } else if (type.equals("4")) {
            className = "Teacher";
        } else if (type.equals("5")) {
            className = "Sponsors";
        }
        Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
        String hql = " update " + className + " set status=? where user.id=?";
        Query query = session.createQuery(hql);
        query.setString(0, status + "");
        query.setString(1, id + "");
        query.executeUpdate();

    }


	@Override
    public boolean getMobileToken(String token,String className) throws Exception {
		
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql="select * from  "+className+"  where token=?";
		Query query=session.createSQLQuery(hql);
		query.setString(0, token);
		///query.list();
	    return query.list().size()==0;
    }

}
