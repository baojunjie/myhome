package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IArtistDAO;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.utils.Tools;

@Component("artistDAOHibernateImpl")
@SuppressWarnings("unchecked")
public class ArtistDAOHibernateImpl extends AbstractDAOHibernateImpl implements IArtistDAO {

    @Override
    public Artist get(Long id) {
        return (Artist) super.get(id);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Artist.class;
    }

    @Override
    public boolean addMobileArtist(Artist artist) throws Exception {

        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        session.save(artist);
        return artist.getId() > 0;

    }

    @Override
    public void updateMobileArtist(Artist artist) {

        getHibernateTemplate().update(artist);
    }

    /**
     * 根据artis——id查询画家信息 TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    public Map<String, Object> getArtistMobile(Long id) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "SELECT a.status,b.age, b.birthday,b.orginimg, b.constellation,b.id_code, "
                     + "  b.img, b.instructor, b.instructor_mobile, b.male, b.name, "
                     + "  b.nation,  b.parent_mobile, b.referrer_school, b.referrer_mobile, "
                     + "  b.school, b.school_mobile,  b.teacher, b.teacher_mobile,b.zodiac,  c.name AS districtName , c.id districtCode, e.name AS cityName ,e.id cityCode, f.name AS provinceName ,f.id provinceCode,b.origin  "
                     + " FROM t_artist a,t_artist_info b,t_region c, t_region e ,t_region f "
                     + "  WHERE a.id=b.id AND b.region_id=c.id and a.id=:id   AND e.id=LEFT(b.region_id,4) AND f.id=LEFT(b.region_id,2) limit 1";
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setLong("id", id);
        List<Map<String, Object>> list = (List<Map<String, Object>>) query.list();
        Map<String, Object> mp = new HashMap<String, Object>();
        if (list.size() > 0) {
            mp = list.get(0);
        }
        return mp;
    }

    /**
     * 
     * 根据画家id审核画家
     * 
     */
    @Override
    public void checkArtistMobile(Long id, int status) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "update t_artist set status=:status where id=:id";
        Query query = session.createSQLQuery(sql);
        query.setInteger("status", status);
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public List<ArtistModel> getList(Integer searchmale, Long regionid, Integer searchage,
                                     Integer index, Integer size) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select v1.name AS cityName, a.id,a.male,a.img , r.name as region ,a.name ,a.school ,a.age , max(w.votenum) as votenum "
                       + "from t_works w inner join t_artist_info a on w.artist_id = a.id  "
                       + "INNER JOIN t_region r on a.region_id = r.id  "
                       + "INNER JOIN t_artist i ON  i.id= a.id "
                       + "left join  t_region v1 on r.region_id =v1.id "
                       + "where w.invalid = false and  i.status = 3 ");
            if (searchmale != null && searchmale == 1) {//男
                sql.append(" and a.male = true");
            } else if (searchmale != null && searchmale == 2) {//女
                sql.append(" and a.male = false");
            }
            if (regionid != null) {
                sql.append(" and r.region_id =" + regionid);
            }
            if (searchage != null) {
                sql.append(" and a.age =" + searchage);
            }
            sql.append(" group by w.artist_id order by votenum desc ");
            Query query = s.createSQLQuery(sql.toString()).setResultTransformer(
                Transformers.aliasToBean(ArtistModel.class));
            query.setMaxResults(size);
            query.setFirstResult(index);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public Integer getListCount(Integer searchmale, Long regionid, Integer searchage) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select  a.id,a.male  , r.name as region ,a.name ,a.school ,a.age , max(w.votenum) as votenum from t_works w inner join t_artist_info a on w.artist_id = a.id  INNER JOIN t_region r on w.region_id = r.id  INNER JOIN t_artist i ON  i.id= a.id where w.invalid = false and  i.status = 3 ");
            if (searchmale != null && searchmale == 1) {//男
                sql.append(" and a.male = true");
            } else if (searchmale != null && searchmale == 2) {//女
                sql.append(" and a.male = false");
            }
            if (regionid != null) {
                sql.append(" and r.region_id =" + regionid);
            }
            if (searchage != null) {
                sql.append(" and a.age =" + searchage);
            }
            sql.append(" group by w.artist_id");
            Query query = s.createSQLQuery(sql.toString()).setResultTransformer(
                Transformers.aliasToBean(ArtistModel.class));
            return (query.list() == null || query.list().size() == 0) ? 0 : query.list().size();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }

    }

    /**
     * 获取画家列表 最新画家，画家排行 getArtistListMobile
     * 
     * @author gwb
     * @param age
     * @param region_code
     * @param male
     * @param pageSize
     * @param pageIndex
     * @param type
     *            1最新画家，2画家排行
     * @return
     * @throws Exception
     *             2015年9月16日 上午11:22:57
     */
    @Override
    public List<Map<String, Object>> getArtistListMobile(Integer age, String region_code,
                                                         String male, int pageSize, int pageNo,
                                                         String type, int status, String typeRegion)
                                                                                                    throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "";
        // String typeRegion="2"; //1 2 3 区 市 省·
        // if(type.equals("1")){
        // sql =
        // "SELECT b.name AS artistName,b.age,a.id AS artistId,c.name AS regionName,c.region_code ,b.img ,b.school,b.male"
        // + "  FROM t_artist a,t_artist_info b,t_region c  "
        // + "  WHERE  a.id=b.id AND b.region_id=c.id ";
        // }
        // if(type.equals("2")){
        sql = "SELECT dddd.* FROM ( SELECT b.weChat_account AS weChatAccount,b.account_name AS accountName,b.bank_account AS bankAccount,b.alipay_account AS alipayAccount, b.name AS artistName, a.created_datetime,b.age,a.id AS artistId,c.name AS regionName,c.region_code ,b.img ,b.orginimg,b.school,IFNULL(d.votenum,0) votenum,b.male"
              + "  ,d.name AS userName, d.id AS userId, a.status, b.constellation, b.nation, b.zodiac,b.cartoon,b.birthday,b.origin"
              + "  FROM t_artist a left join ( SELECT aa.artist_id, MAX(aa.votenum) votenum  FROM  t_works aa,t_artist bb  WHERE aa.artist_id=bb.id  AND aa.status="
              + status
              + " AND bb.status="
              + status
              + "   GROUP BY aa.artist_id ORDER BY votenum DESC "
              + ")  d on  a.id=d.artist_id ,t_artist_info b,t_region c, t_user_info d  "
              + "  WHERE  a.id=b.id AND b.region_id=c.id   AND b.user_id=d.id   ";
        // }

        if (age > 0) {
            sql += "  and b.age=:age";
        }
        if (Tools.notEmpty(region_code)) {
            if (typeRegion.equals("1")) {
                sql += " and c.region_code like '" + region_code + "%'  ";
            } else if (typeRegion.equals("2")) {
                sql += " and c.region_id like '" + region_code + "%' ";
            }

            // sql += " and e.region_code=:region_code";
        }

        if (Tools.notEmpty(male)) {
            sql += "  and b.male=:male";
        }
        if (status > 0) {
            sql += "  and a.status=:status";
        }
        sql += " )dddd ";
        if (type.equals("1")) {
            sql += "  order by dddd.created_datetime desc,artistId ";
        } else {
            sql += "  ORDER BY IFNULL( dddd.votenum  ,0)  desc ";
        }
        sql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        // query.setFirstResult((pageNo - 1) * pageSize);
        // query.setMaxResults(pageSize);
        if (age > 0) {
            query.setInteger("age", age);
        }
        if (Tools.notEmpty(male)) {

            if (male.equals("true")) {
                query.setBoolean("male", true);
            } else {
                query.setBoolean("male", false);
            }
        }
        if (status > 0) {
            query.setInteger("status", status);
        }
        List<Map<String, Object>> map = query.list();
        return map;
        // return null;
    }

    @Override
    public Artist getByUserid(String userid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from Artist where   artistInfo.user.id = :userid ";
            Query query = s.createQuery(hql);
            query.setInteger("userid", Integer.valueOf(userid));
            return ((query.list() == null || query.list().size() == 0) ? null : (Artist) query
                .list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();

        }
    }

    @Override
    public void updateweb(Artist artist) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.update(artist);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

    }

    @Override
    public ArtistInfo getUserArtistInfoMobile(ArtistInfo artistInfo) throws Exception {

        List<String> ls = new ArrayList<String>();

        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        StringBuffer hql = new StringBuffer();
        hql.append(" from ArtistInfo where 1=1");
        if (artistInfo.getId() != null && artistInfo.getId() > 0) {
            hql.append(" and artist.id=? ");
            ls.add(artistInfo.getId() + "");
        }
        if (artistInfo.getUser() != null && artistInfo.getUser().getId() > 0) {
            hql.append(" and user.id=? ");
            ls.add(artistInfo.getUser().getId() + "");
        }
        Query query = session.createQuery(hql.toString());
        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i));
        }
        List<Object> list = query.list();
        return (ArtistInfo) (list.size() > 0 ? list.get(0) : null);
    }

    @Override
    public List<Artist> findByKeywords(String keywords, String index, String size) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = " from Artist where 1=1 and status in(3)";
        //选手名字like
        if (null != keywords && !"".equals(keywords)) {
            hql = hql
                  + " and (artistInfo.name like :name or artistInfo.school like :school or artistInfo.trainingInstitution like :trainingInstitution) ";
        }
        //时间排序
        hql = hql + " order by artistInfo.createdDatetime DESC ";
        //加载HQL语句
        Query query = session.createQuery(hql);
        //参数赋值
        //选手名字like
        if (null != keywords && !"".equals(keywords)) {
            query.setString("name", "%" + keywords + "%");
            query.setString("school", "%" + keywords + "%");
            query.setString("trainingInstitution", "%" + keywords + "%");
        }
        //分页
        query.setFirstResult((Integer.valueOf(index)) * Integer.valueOf(size));
        query.setMaxResults(Integer.valueOf(size));
        List<Artist> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Integer countByKeywords(String keywords) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = " from Artist where 1=1 and status in(3)";
        //选手名字like
        if (null != keywords && !"".equals(keywords)) {
            hql = hql
                  + " and (artistInfo.name like :name or artistInfo.school like :school or artistInfo.trainingInstitution like :trainingInstitution) ";
        }
        //时间排序
        //加载HQL语句
        Query query = session.createQuery(hql);
        //参数赋值
        //选手名字like
        if (null != keywords && !"".equals(keywords)) {
            query.setString("name", "%" + keywords + "%");
            query.setString("school", "%" + keywords + "%");
            query.setString("trainingInstitution", "%" + keywords + "%");
        }
        List<Artist> list = query.list();
        session.close();
        return list.size();
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getCitySort() {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select  r1.`name`,count(*) as count from t_region r ,t_region r1 ,(select a2.region_id  from t_artist a1 INNER "
                     + "JOIN t_artist_info  a2 on a1.id = a2.id where a1.invalid = false and a1.`status` =3 ) a where r.id = a.region_id "
                     + "and  r.region_id=r1.id GROUP BY  r1.id order by  count  desc , r1.name desc ";
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mp = query.list();
        return mp;
    }

    @Override
    @Transactional
    public List<Map<String, Object>> findListByWorksType(String type,String age, int size, int index,String awards) {
    	Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "";
        sql+="SELECT t.awards, t.type,ai.img, ai.age,ai.male,ai.`name` AS artistName,ai.id AS artistId,ai.training_institution AS trainingInstitution,ai.school,ai.user_id AS userId"
        +" FROM t_artist a INNER JOIN t_artist_info ai ON a.id=ai.id INNER JOIN "
        +"(SELECT artist_id,max(type) AS type, max(awards) AS awards FROM t_works tt WHERE tt.invalid=0 AND tt.`status`=3 AND"
        + " tt.type IN " +type+ " GROUP BY tt.artist_id ) t ON a.id=t.artist_id WHERE a.invalid=0 AND a.`status`=3 ";
        if (null != age && !"".equals(age)) {
            sql+= " and ai.age IN " + age;
        }
        if(awards!=null && !"".equals(awards)){
        	sql += " and t.awards ="+Integer.valueOf(awards);
        }
        sql+= "  ORDER BY t.awards DESC,t.type DESC,artistId ASC "
        + " limit " + index * size + "," + size;
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mp = query.list();
        return mp;
    }

    @Override
    @Transactional
    public Integer findCountByWorksType(String type, String age,String awards) {
    	  Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
          String sql = "";
          sql+="SELECT count(*) "
          +" FROM t_artist a INNER JOIN t_artist_info ai ON a.id=ai.id INNER JOIN "
          +"(SELECT artist_id,max(type) AS type, max(awards) AS awards FROM t_works tt WHERE tt.invalid=0 AND tt.`status`=3 AND"
          + " tt.type IN " +type+ " GROUP BY tt.artist_id ) t ON a.id=t.artist_id WHERE a.invalid=0 AND a.`status`=3 ";
          if (null != age && !"".equals(age)) {
              sql+= " and ai.age IN " + age;
          }
          if(awards != null && !"".equals(awards)){
        	  sql += " and t.awards = "+Integer.valueOf(awards);
          }
          Query query = session.createSQLQuery(sql);
          //参数赋值
          Integer count = Integer.parseInt(query.uniqueResult().toString());
          return count;
    }

    @Override
    public List<Map<String, Object>> findPassArtist() {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String sql = "select a1.* from t_artist  a INNER JOIN t_artist_info a1 on a.id = a1.id where  a. id IN (select DISTINCT artist_id from t_works"
                     + " where 1=1 and status = 3 and type !=0 and invalid = false) and a.`status` = 3 and a.invalid = false order by rand()";
        Query query = session.createSQLQuery(sql);
        //分页
        query.setMaxResults(6);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mp = query.list();
        session.close();
        return mp;
    }
}
