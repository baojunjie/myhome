package com.myhome.dao.impl;

import java.util.Date;

import com.myhome.entity.Voting;
import com.myhome.utils.DateUtil;
import com.myhome.dao.IVotingDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component("votingDAOHibernateImpl")
public class VotingDAOHibernateImpl extends AbstractDAOHibernateImpl implements IVotingDAO {

    @Override
    public Voting get(Long id) {
        return (Voting) super.get(id);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Voting.class;
    }

    @Override
    public int getByUserAndWorks(long works_id, long user_id) {
        Session s = hibernateTemplate.getSessionFactory().getCurrentSession();
        Date d = new Date();
        String date1 = DateUtil.toStringDS(d);
        Long l = d.getTime() + 1000 * 60 * 60 * 24;
        d.setTime(l);
        String date2 = DateUtil.toStringDS(d);

        //        String hql = "select count(id) from Voting " + "where user.id = :userid "
        //                     + "and works.id = :worksid " + "and invalid = false "
        //                     + "and DATE_FORMAT(createdDatetime,'%Y%m%d') = " + date + "";
        //        Query query = session.createQuery(hql);
        String hql = "select count(1) from t_voting where user_id=:user_id and works_id = :works_id "
                     + "and invalid = false and ";
        hql += " created_datetime >=" + date1 + " and created_datetime < " + date2;
        Query query = s.createSQLQuery(hql);
        if (works_id > 0) {
            query.setLong("works_id", works_id);
        }
        if (user_id > 0) {
            query.setLong("user_id", user_id);
        }
        if (user_id > 0) {
            query.setInteger("user_id", Integer.valueOf(String.valueOf(user_id)));
        }
        if (works_id > 0) {
            query.setInteger("works_id", Integer.valueOf(String.valueOf(works_id)));
        }
        Integer count = Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    /**
     * 判断该用户一天给该作品投了多少票 getVotingNum     判断一个用户一天一共投了多少组票
     * 
     * @author gwb
     * @param works_id
     * @param user_id
     * @return 2015年9月17日 下午3:26:39
     */
    @Override
    public int getVotingNum(Long works_id, Long user_id) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String date = DateUtil.toStringDS(new Date());
        //String hql = "select count(*) from t_voting where  user_id=:user_id and works_id=:works_id and DATE_FORMAT(created_datetime,'%Y%m%d') ="+date+"";
        String hql = "select count(*) from t_voting where  ";
        if (user_id > 0) {
            hql += " user_id=:user_id and";
        }
        if (works_id > 0) {
            hql += " works_id=:works_id and";
        }
        hql += " DATE_FORMAT(created_datetime,'%Y%m%d') =" + date + "";
        Query query = session.createSQLQuery(hql);
        if (works_id > 0) {
            query.setLong("works_id", works_id);
        }
        if (user_id > 0) {
            query.setLong("user_id", user_id);
        }
        int num = Integer.parseInt(query.uniqueResult() + "");
        session.close();
        return num;

    }

    @Override
    public int getByUser(long user_id) {
        Session s = hibernateTemplate.getSessionFactory().getCurrentSession();
        Date d = new Date();
        String date1 = DateUtil.toStringDS(d);
        Long l = d.getTime() + 1000 * 60 * 60 * 24;
        d.setTime(l);
        String date2 = DateUtil.toStringDS(d);
        /*        String hql = "select count(1) from (select count(1) from t_voting where  ";
                if (user_id > 0) {
                    hql += " user_id=:user_id and";
                }
                hql += " DATE_FORMAT(created_datetime,'%Y%m%d') =" + date + "";
                hql += " group by works_id) v1 ";
                Query query = s.createSQLQuery(hql);
                if (user_id > 0) {
                    query.setLong("user_id", user_id);
                }*/
        String hql = "select count( distinct works_id) from t_voting where ";
        if (user_id > 0) {
            hql += " user_id=:user_id and";
        }
        hql += " created_datetime >=" + date1 + " and created_datetime < " + date2;
        Query query = s.createSQLQuery(hql);
        if (user_id > 0) {
            query.setLong("user_id", user_id);
        }
        Integer count = Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getVotingNumByUserId(Long user_id) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String date = DateUtil.toStringDS(new Date());
        //String hql = "select count(*) from t_voting where  user_id=:user_id and works_id=:works_id and DATE_FORMAT(created_datetime,'%Y%m%d') ="+date+"";
        String hql = " select count(*)  from (select count(*) from t_voting where  ";
        hql += " user_id=:user_id and";

        hql += " DATE_FORMAT(created_datetime,'%Y%m%d') =" + date + "";
        hql += " group by works_id";
        hql += ") b";
        Query query = session.createSQLQuery(hql);
        if (user_id > 0) {
            query.setLong("user_id", user_id);
        }
        int num = Integer.parseInt(query.uniqueResult() + "");
        session.close();
        return num;
    }

}
