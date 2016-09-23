package com.myhome.dao.impl;


import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.myhome.dao.IRecommendHelpPlayTourDAO;
import com.myhome.entity.RecommendHelpPlayTour;
import com.myhome.entity.vo.RecommendHelpPlayTourVo;

/**
 * 打赏/帮助
 * 
 * @author ywz
 */
@Component("recommendChildrenDAOHibernateImpl")
public class RecommendHelpPlayTourDAOHibernateImpl extends AbstractDAOHibernateImpl implements IRecommendHelpPlayTourDAO {

    @Override
    protected Class getEntityClass() {
        return RecommendHelpPlayTour.class;
    }

    @Override
    public List<Map<String, Object>> searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo,int t,int pageSize,int pageNo) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select t1.id, t1.type as type,t.money as money,t.message as message,"+
        "(case when t1.type=1 then t1.name when t1.type in (2,6) then t2.name when  t1.type=3 then t3.name when t1.type=4 then t4.name end) as name ,"+
        "(case when t1.type in (2,6) then t2.age when  t1.type=3 then t3.age when t1.type=4 then t4.age end) as age ,"+
        "(case when t1.type in (2,6) then t2.male when  t1.type=3 then t3.male when t1.type=4 then t4.male end) as male ,"+
        "(case when t1.type in (2,6) then t2.img when  t1.type=3 then t3.img when t1.type=4 then t4.img end) as img ";

        if (t==1) {
            sql=sql+",(case when t1.type in (2,6) then t2.weChat_account when  t1.type=3 then t3.weChat_account end) as weChatAccount, "
            +"(case when t1.type in (2,6) then t2.bank_account when  t1.type=3 then t3.bank_account end) as bankAccount, "
            +"(case when t1.type in (2,6) then t2.account_name when  t1.type=3 then t3.account_name end) as accountName, "
            +"(case when t1.type in (2,6) then t2.alipay_account when  t1.type=3 then t3.alipay_account end) as alipayAccount ";
        } 
        sql=sql+" from t_recommend_help_playtour t,t_user_info t1 left join t_artist_info t2 on t2.user_id=t1.id left join t_help_children t3 on t3.user_id=t1.id left join t_teacher t4 on t4.user_id=t1.id";

        if (t==0) {
            sql=sql+" where t.user_id_from=t1.id and t.user_id_to=:id and t.type=:type";
        } else{
            sql=sql+" where t.user_id_to=t1.id and t.user_id_from=:id and t.type=:type";
        }
        sql+=" order by t.created_datetime desc limit " +  (pageNo-1)*pageSize +","+ pageSize ;
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if(t==0){
            query.setLong("id", recommendChildrenVo.getUserTo().getId());
        }else{
            query.setLong("id", recommendChildrenVo.getUserFrom().getId());
        }

        query.setLong("type", recommendChildrenVo.getType());
        List<Map<String, Object>> mp = query.list();
        return mp;
    }
    
    @Override
    public int searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo,int t) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select count(*) ";
        sql=sql+" from t_recommend_help_playtour t,t_user_info t1 left join t_artist_info t2 on t2.user_id=t1.id left join t_help_children t3 on t3.user_id=t1.id left join t_teacher t4 on t4.user_id=t1.id";
        if (t==0) {
            sql=sql+" where t.user_id_from=t1.id and t.user_id_to=:id and t.type=:type";
        } else{
            sql=sql+" where t.user_id_to=t1.id and t.user_id_from=:id and t.type=:type";
        }
        Query query = session.createSQLQuery(sql);
        if(t==0){
            query.setLong("id", recommendChildrenVo.getUserTo().getId());
        }else{
            query.setLong("id", recommendChildrenVo.getUserFrom().getId());
        }

        query.setLong("type", recommendChildrenVo.getType());

        return Integer.parseInt(query.uniqueResult().toString());
    }
}

