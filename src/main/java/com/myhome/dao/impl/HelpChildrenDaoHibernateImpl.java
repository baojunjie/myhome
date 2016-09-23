package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.myhome.dao.IHelpChildrenDao;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.Sponsors;
import com.myhome.entity.Teacher;
import com.myhome.entity.User;
import com.myhome.utils.Tools;
/**
 * 受捐者
 * 
 * @author gwb
 * @version $Id: HelpChildrenDaoHibernateImpl.java, v 0.1 2015年10月12日 下午3:49:09 gwb Exp $
 */			
@Component("helpChildrenDAOHibernateImpl")
public class HelpChildrenDaoHibernateImpl extends AbstractDAOHibernateImpl implements IHelpChildrenDao {

	@Override
    protected Class getEntityClass() {
	    return HelpChildren.class;
    }
	@Override
    public HelpChildren get(Long id) {
        return (HelpChildren) super.get(id);
    }
	/**
	 * 新增
	 * @see com.myhome.dao.IHelpChildrenDao#addMobileHelpChildren(com.myhome.entity.HelpChildren)
	 */
	@Override
    public Long addMobileHelpChildren(HelpChildren children) throws Exception {
		
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		session.save(children);
	    return children.getId();
	    
    }

	/**
	 * 修改
	 * @see com.myhome.dao.IHelpChildrenDao#updateMobileHelpChildren(com.myhome.entity.HelpChildren)
	 */
	@Override
    public void updateMobileHelpChildren(HelpChildren children) throws Exception {
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		HelpChildren hc=(HelpChildren) session.get(HelpChildren.class, children.getId());
		Tools.copyProperties(children, hc);
		session.update(hc);
    }

	/**
	 * 删除
	 * @see com.myhome.dao.IHelpChildrenDao#deleteMobileHelpChildren(com.myhome.entity.HelpChildren)
	 */
	@Override
    public void deleteMobileHelpChildren(HelpChildren children) throws Exception {
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.delete(children);
			
    }

	/**
	 * 查询
	 * @see com.myhome.dao.IHelpChildrenDao#getMobileHelpChildren(com.myhome.entity.User)
	 */
	@Override
    public HelpChildren getMobileHelpChildren(HelpChildren children) throws Exception {
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer hql=new StringBuffer();
		List<Object> ls=new ArrayList<Object>();
		hql.append(" from HelpChildren where 1=1");
		if(Tools.notEmpty(children.getId()+"")){
			hql.append(" and id=?");
			ls.add(children.getId()+"");
		}
		if(Tools.notEmpty(children.getUser().getId()+"")){
			hql.append(" and user.id=?");
			ls.add(children.getUser().getId()+"");
		}
		if(children.getRegion()!=null &&Tools.notEmpty(children.getRegion().getId()+"")){
			hql.append(" and region.id=?");
			ls.add(children.getRegion().getId()+"");
		}
		if(children.getStatus()>1){
			hql.append(" and status=?");
			ls.add(children.getStatus()+"");
		}
		
		Query query=session.createQuery(hql.toString());
		
		for(int i=0;i<ls.size();i++){
			query.setString(i, ls.get(i)+"");
		}
		Object object=query.uniqueResult();
		return  object==null?null:(HelpChildren)object;
	     
    }

	/**
	 * 
	 * 查询列表
	 * @see com.myhome.dao.IHelpChildrenDao#getMobileHelpChildrenLisr(com.myhome.entity.User)
	 */
	@SuppressWarnings("unchecked")
    // TODO 待完善
	@Override
    public List<HelpChildren> getMobileHelpChildrenList(HelpChildren children) throws Exception {
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer hql=new StringBuffer();
		List<Object> ls=new ArrayList<Object>();
		hql.append(" from HelpChildren where 1=1");
		if(Tools.notEmpty(children.getId()+"")){
			hql.append(" and id=?");
			ls.add(children.getId()+"");
		}
		if(Tools.notEmpty(children.getUser().getId()+"")){
			hql.append(" and user.id=?");
			ls.add(children.getUser().getId()+"");
		}
		if(Tools.notEmpty(children.getRegion().getId()+"")){
			hql.append(" and region.id=?");
			ls.add(children.getRegion().getId()+"");
		}
		if(children.getStatus()>0){
			hql.append(" and status=?");
			ls.add(children.getStatus()+"");
		}
		
		Query query=session.createQuery(hql.toString());
		
		for(int i=0;i<ls.size();i++){
			query.setString(i, ls.get(i)+"");
		}
		
		
		// query.setMaxResults(size);
         //query.setFirstResult(index);
	    return query.list();
    }
    @Override
    public HelpChildren getByUserid(Long userid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from HelpChildren where user.id = :userid ";
            Query query = s.createQuery(hql);
            query.setLong("userid",userid);
            return ((query.list() == null || query.list().size() == 0) ? null : (HelpChildren) query.list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();

        }
    }

}
