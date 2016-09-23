
package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IUserDAO;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.utils.Tools;




@Component("userDAOHibernateImpl")
public class UserDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements IUserDAO {
//    @Override
//    public User get(Long id) {
//        return (User) super.get(id);
//    }
	
    @Override
    @Transactional
    public User get(Long id) {
        return (User) super.get(id);
    }
    @Override
    protected Class<?> getEntityClass() {
        return User.class;
    }
    @Override
    public void save(String name, String password, String mobile) {
        Session session=getHibernateTemplate().getSessionFactory().openSession();
        Transaction ta=null;
        try{
            ta=session.beginTransaction();
            User user = new User();
            UserInfo userinfo = new UserInfo();
            userinfo.setName(name);
            userinfo.setUser(user);
            session.save(userinfo);
            ta.commit();
 
        }catch(Exception e){
            ta.rollback();
            System.out.println(e.getMessage());
        }
        finally{
            session.close();
        }
        
    }
	@Override
    public Long addUserMobile(User user) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.save(user);
		session.close();
		return user.getId();
    }
	@Override
    public void updateUserMobile(UserInfo userInfo) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		UserInfo us=(UserInfo) session.get(UserInfo.class, userInfo.getId());
		Tools.copyProperties(userInfo, us);
		session.update(us);
    }
	@Override
    public User getMobileUser(User user) throws Exception {
	     Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	     StringBuffer hql =new StringBuffer();
	     List<String> ls=new ArrayList<String>();
	     hql.append(" from User where 1=1");
	     if(Tools.notEmpty(user.getId()+"")){
	    	 hql.append(" and id=? ");
	    	 ls.add(user.getId()+"");
	     }
		Query query=session.createQuery(hql.toString());
		for(int i=0;i<ls.size();i++){
			query.setString(i, ls.get(i)+"");
		}
		User us=(User) query.uniqueResult();
		return us;
    }

}

