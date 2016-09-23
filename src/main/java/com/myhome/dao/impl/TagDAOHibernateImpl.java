package com.myhome.dao.impl;

import java.util.List;

import com.myhome.dao.ITagDAO;
import com.myhome.entity.Region;
import com.myhome.entity.Tag;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;




@Component("tagDAOHibernateImpl")
public class TagDAOHibernateImpl extends AbstractDAOHibernateImpl 
        implements ITagDAO {

    

    



    @Override
    public Tag get(Long id) {
        return (Tag) super.get(id);
    }
    
    

    
    
    @Override
    protected Class<?> getEntityClass() {
        return Tag.class;
    }




    /**
     * 作品类别列表
     * TODO 简单描述该方法的实现功能（可选）.
     */
	@Override
    public List<Tag> getTagList() throws Exception{
		Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = " FROM Tag ";
		Query query = session.createQuery(hql);
		List<Tag> listTag = query.list();
	    return listTag;
    }





	@Override
	public List<Region> getListByLevelAndCode(Integer level, String code) {
		Session s=getHibernateTemplate().getSessionFactory().openSession();
		try{
			StringBuffer hql = new StringBuffer();
			hql.append("from Region where invalid = false");
			if(level!=null){
				 hql.append(" and level=:level");
			}
			if(code!=null){
				 hql.append(" and parent.regionCode=:code");
			}
			hql.append(" order by nameCode asc");
			Query query =  s.createQuery(hql.toString());
			if(level!=null){
				query.setInteger("level", level);
			}
			if(code!=null){
				query.setInteger("code", Integer.valueOf(code));
			}
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			s.close();
		}
		}





	@Override
	public List<Tag> getTagListByName(String worksType) {
		Session s=getHibernateTemplate().getSessionFactory().openSession();
		try{
			StringBuffer hql = new StringBuffer();
			hql.append("from Tag where invalid = false");
			hql.append(" and name like :name");
			Query query =  s.createQuery(hql.toString());
			query.setString("name", "%" + worksType + "%");
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			s.close();
		}
		}

}
