package com.myhome.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IDAO;
import com.myhome.entity.AbstractEntity;


abstract public class AbstractDAOHibernateImpl
        implements IDAO {

    @Resource(name = "hibernateTemplate")
    HibernateTemplate hibernateTemplate;


    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    abstract protected Class getEntityClass();
    
    
    protected AbstractEntity get(Long id) {
        return (AbstractEntity) getHibernateTemplate().get(getEntityClass(), id);
    }
    
    
    @Override
    public void add(AbstractEntity abstractEntity) {
        getHibernateTemplate().save(abstractEntity);
    }
    
    
    @Override
    public int update(AbstractEntity abstractEntity) {
        getHibernateTemplate().update(abstractEntity);
        return 1;
    }
    
    
    @Override
    public int remove(Long id) {
        AbstractEntity abstractEntity = get(id);
        abstractEntity.setInvalid(true);
        getHibernateTemplate().update(abstractEntity);
        return 1;
    }
    
    
    @Override
    public int delete(Long id) {
        AbstractEntity abstractEntity = get(id);
        getHibernateTemplate().delete(abstractEntity);
        return 1;
    }
    
    /**
     * mobile新增方法
     */
    @Transactional
    public long addMobile(AbstractEntity abstractEntity) {
        getHibernateTemplate().saveOrUpdate(abstractEntity);
        long id=abstractEntity.getId();    
        return id;
    }

}
