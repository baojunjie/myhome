package com.myhome.dao.impl;

import java.util.List;

import com.myhome.dao.ICommentDAO;
import com.myhome.entity.Comment;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component("commentDAOHibernateImpl")
public class CommentDAOHibernateImpl extends AbstractDAOHibernateImpl implements ICommentDAO {

    @Override
    public Comment get(Long id) {
        return (Comment) super.get(id);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Comment.class;
    }

    /**
     * 查询作品评论
     */
    @Override
    public List<Comment> getCommentListMobile(Long worksId,int pageNo,int pageSize) {
        
        String hql=" from Comment where works.id=:worksId order by createdDatetime desc";
        Session session=  super.hibernateTemplate.getSessionFactory().openSession();
        Query query=session.createQuery(hql);
        query.setLong("worksId", worksId);
        query.setFirstResult((pageNo-1)*pageSize);
        query.setMaxResults(pageSize);
        List<Comment> list= query.list();
        session.close();
        return list;
    }
     @Override
        public List<Comment> getByWorksid(long worksid) {
            Session s=getHibernateTemplate().getSessionFactory().openSession();
            try{
                String hql= " from Comment where works.id=:worksid order by createdDatetime desc  ";
                Query query =  s.createQuery(hql);
                query.setLong("worksid", worksid);
                return query.list();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }finally{
                s.close();
            }
            }

}
