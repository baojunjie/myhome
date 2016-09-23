package com.myhome.dao.impl;


import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.myhome.dao.IArtistInfoDAO;
import com.myhome.entity.ArtistInfo;
import com.myhome.utils.Tools;

@Component("artistInfoDAOHibernateImpl")
public class ArtistInfoDAOHibernateImpl extends AbstractDAOHibernateImpl implements IArtistInfoDAO {

	@Override
	public ArtistInfo get(Long id) {
		return (ArtistInfo) super.get(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return ArtistInfo.class;
	}

	/**
	 * app 
	 * 
	 * 我要报名参赛 TODO 简单描述该方法的实现功能（可选）.
	 */
	@Override
	public Long addArtistInfoMobil(ArtistInfo artistInfo) throws Exception{
		Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
		session.save(artistInfo);
		return artistInfo.getId();

	}

	@Override
    public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception {

		ArtistInfo art= (ArtistInfo) super.getHibernateTemplate().get(ArtistInfo.class, artistInfo.getId());
	        artistInfo.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
	        if(Tools.isEmpty(artistInfo.getImg())){
	            artistInfo.setImg(art.getImg());
	            artistInfo.setOrginimg(art.getOrginimg());
	        }
	        artistInfo.setAge(art.getAge());
	        artistInfo.setCreatedDatetime(art.getCreatedDatetime());
	        artistInfo.setUser(art.getUser());
	        Session session=super.getHibernateTemplate().getSessionFactory().openSession();
	        session.update(artistInfo);
	        session.close();	    
    }

}

