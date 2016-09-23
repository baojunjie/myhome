package com.myhome.dao.impl;

import com.myhome.dao.IGameWorksItemDAO;
import com.myhome.entity.GameWorksItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component("gameWorksItemDAOHibernateImpl")
public class GameWorksItemDAOHibernateImpl extends AbstractDAOHibernateImpl implements IGameWorksItemDAO {

	@Override
	public GameWorksItem get(Long id) {
		return (GameWorksItem) super.get(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return GameWorksItem.class;
	}

	@Override
	public int addGameWorksItemMobile(GameWorksItem gameWorksItem, String id) throws Exception {
		Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "INSERT INTO t_game_works_item (game_id,works_id,applicant_id) " + "VALUE("
		        + gameWorksItem.getGame().getId() + "," + 1 + "," + gameWorksItem.getApplicant().getId() + ") ";
		Query qu = session.createSQLQuery(sql);
		int num = qu.executeUpdate();
		return num;

	}

}
