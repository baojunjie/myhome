package com.myhome.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.myhome.dao.IPictureDAO;
import com.myhome.entity.AbstractEntity;
import com.myhome.entity.Picture;

/**
 * ClassName:PictureDAOHibernateImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年9月10日 下午3:38:22 <br/>
 * 
 * @author 1
 * @version
 * @since JDK 1.6
 * @see
 */
@Component("pictureDAOHibernateImpl")
public class PictureDAOHibernateImpl extends AbstractDAOHibernateImpl implements IPictureDAO {

    /**
     * 向Picture表中插入数据 TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    public Long addPictureMobile(Picture picture) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        session.save(picture);
        return picture.getId();
    }

    @Override
    protected Class getEntityClass() {

        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 修改照片 
     * 
     */
    @Override
    public void updatePictureMobile(Picture pic) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append(" update Picture  set path=:path , orginPath=:orginPath ");

//        if (!StringUtils.isEmpty(pic.getPath())) {
//            hql.append("path=:path");
//        }
//        if (!StringUtils.isEmpty(pic.getOrginpath())) {
//            hql.append("orginPath=:orginPath");
//        }

        hql.append(" where id=:id");

        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        Query query = session.createQuery(hql.toString());
        if (!StringUtils.isEmpty(pic.getPath())) {
            query.setString("path", pic.getPath());
        }
        if (!StringUtils.isEmpty(pic.getOrginpath())) {
            query.setString("orginPath", pic.getOrginpath());
        }
        query.setLong("id", pic.getId());

        query.executeUpdate();

        session.close();

    }

}
