package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IWorksDAO;
import com.myhome.entity.Region;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ArtistModel;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.Tools;

@Component("worksDAOHibernateImpl")
@SuppressWarnings("unchecked")
public class WorksDAOHibernateImpl extends AbstractDAOHibernateImpl implements IWorksDAO {
    @Override
    public Works get(Long id) {
        return (Works) super.get(id);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Works.class;
    }

    @Override
    public List<ArtistModel> getBoyList() {
        Session s = super.hibernateTemplate.getSessionFactory().getCurrentSession();

        String sql = "select  a.id,a.img ,a.male, r.name as region, v1.name AS cityName, a.name ,a.school ,a.age , max(w.votenum) as votenum "
                     + "from t_works w inner join t_artist_info a on w.artist_id = a.id  "
                     + "INNER JOIN t_region r on w.region_id = r.id  "
                     + "INNER JOIN t_artist i ON i.id = a.id "
                     + "left join  t_region v1 on substr(r.id,1,4) =v1.id "
                     + "where a.male = TRUE and w.invalid = false AND i.status = 3 "
                     + "group by w.artist_id order by votenum desc LIMIT 6";
        Query query = s.createSQLQuery(sql).setResultTransformer(
            Transformers.aliasToBean(ArtistModel.class));

        return query.list();

    }

    @Override
    public List<ArtistModel> getGirlList() {
        Session s = super.hibernateTemplate.getSessionFactory().getCurrentSession();

        String sql = "select  a.id ,a.img ,a.male, r.name as region ,a.name ,a.school ,v1.name AS cityName, a.age , max(w.votenum) as votenum "
                     + "from t_works w inner join t_artist_info a on w.artist_id = a.id  "
                     + "INNER JOIN t_region r on w.region_id = r.id  "
                     + "INNER JOIN t_artist i ON i.id = a.id "
                     + "left join  t_region v1 on substr(r.id,1,4) =v1.id "
                     + "where a.male = false and w.invalid = false AND i.status = 3 "
                     + "group by w.artist_id order by votenum desc LIMIT 6";
        Query query = s.createSQLQuery(sql).setResultTransformer(
            Transformers.aliasToBean(ArtistModel.class));

        return query.list();

    }

    @Override
    public List<Map<String, Object>> getTop100() {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String sql = "select v_works.*  from   v_works ";
            Query query = s.createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public List<Works> getListByAge(Integer age) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (age != null) {
                hql = " from Works where invalid = false and status = 3 and age=:age and artisrt.status = 3 order by votenum desc  ";
            } else {
                hql = " from Works where invalid = false and status = 3  and artisrt.status = 3 order by votenum desc  ";
            }
            Query query = s.createQuery(hql);
            if (age != null) {
                query.setInteger("age", age);
            }
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public int getCountByAge(Integer age, String male) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = "Select Count(id) from Works where invalid = false and artist.status = 3  ";
        if (null != age && !"".equals(age)) {
            hql = hql + " and age=:age ";
        }
        if (null != male && !"".equals(male)) {
            hql = hql + " and male=:male ";
        }

        hql = hql + " order by votenum desc  ";
        Query query = session.createQuery(hql);
        if (null != age && !"".equals(age)) {
            query.setInteger("age", age);
        }
        if (null != male && !"".equals(male)) {
            query.setString("male", male);
        }

        Integer count = Integer.valueOf(query.uniqueResult().toString());
        session.close();
        return count;
    }

    @Override
    public int getCountByRegion(Long region, String male) throws Exception {
        Session session = getHibernateTemplate().getSessionFactory().openSession();

        String hql = "select count(id) from Works  where invalid = false  ";
        if (null != region && !"".equals(region)) {
            hql = hql + " and region.parent.id  =:region ";
        }
        if (null != male && !"".equals(male)) {
            hql = hql + " and male=:male ";
        }
        Query query = session.createQuery(hql);

        if (null != region && !"".equals(region)) {
            query.setLong("region", region);
        }
        if (null != male && !"".equals(male)) {
            query.setString("male", male);
        }
        Integer count = Integer.valueOf(query.uniqueResult().toString());
        session.close();
        return count;
    }

    @Override
    public List<Works> getListByAge0fPage(Integer age, int index, Integer size) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (age != null) {
                hql = " from Works where invalid = false and artist.status = 3 and age=:age order by votenum desc  ";
            } else {
                hql = " from Works where invalid = false and artist.status = 3   order by votenum desc  ";
            }
            Query query = s.createQuery(hql);
            if (age != null) {
                query.setInteger("age", age);
            }
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
    public List<Works> getListByAgeAndMale(Integer age, boolean male) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (age != null) {
                hql = " from Works  where invalid = false and artist.status = 3 and age=:age and male=:male and artisrt.status = 3  order by votenum desc  ";
            } else {
                hql = " from Works  where invalid = false and status = 3 and male=:male and artisrt.status = 3  order by votenum desc  ";
            }
            Query query = s.createQuery(hql);
            if (age != null) {
                query.setInteger("age", age);
            }
            query.setBoolean("male", male);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public List<Works> getListByRegion(Long region) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (region == null) {
                hql = " from Works  where invalid = false and status = 3 and artisrt.status = 3  order by votenum desc  ";
            } else {
                hql = " from Works  where invalid = false and status = 3 and region.parent.id  =:region and artisrt.status = 3   order by votenum desc  ";
            }

            Query query = s.createQuery(hql);
            if (region != null) {
                query.setLong("region", region);
            }
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public List<Works> getListByRegionAndMale(Long region, boolean male) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (region == null) {
                hql = " from Works  where invalid = false and status = 3 and male=:male and artisrt.status = 3 order by votenum desc  ";
            } else {
                hql = " from Works  where invalid = false and status = 3 and region.parent.id  = :region and male=:male and artisrt.status = 3 order by votenum desc  ";
            }

            Query query = s.createQuery(hql);
            if (region != null) {
                query.setLong("region", region);
            }
            query.setBoolean("male", male);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public Integer getByArtistid(long artist_id, Integer type) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "from WorksTagItem  where works.invalid = false and works.status = :type and works.artist.id = :artistid  and works.artist.status=3 order by works.votenum desc  ";
            Query query = s.createQuery(hql);
            query.setInteger("artistid", Integer.valueOf(String.valueOf(artist_id)));
            query.setInteger("type", type);
            return (query.list() == null || query.list().size() == 0) ? 0 : query.list().size();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public List<Works> getWorksByAgeAndMaleAndRegion(Integer age, Boolean male, Integer regionCode) {
        Session s = super.hibernateTemplate.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Works where invalid = false and status = 3 and artist.status=3 ");
        if (age != null) {
            sb.append(" and age =:age");
        }
        if (male != null && male) {
            sb.append(" and male = true ");
        } else if (male != null && male == false) {
            sb.append(" and male = false ");
        }
        if (regionCode != null) {
            sb.append(" and region.parent.id  = :regionCode");
        }
        sb.append(" order by votenum desc ");
        Query query = s.createQuery(sb.toString());

        if (age != null) {
            query.setInteger("age", age);
        }
        if (regionCode != null) {
            query.setInteger("regionCode", regionCode);
        }
        query.setMaxResults(3);
        query.setFirstResult(1);
        return query.list();

    }

    @Override
    public List<Works> getWorksByAgeAndMaleAndRegion1(Integer age, Boolean male, Integer regionCode) {
        Session s = super.hibernateTemplate.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Works where invalid = false and status = 3 and artist.status=3 ");
        if (age != null) {
            sb.append(" and age =:age");
        }
        if (male != null && male) {
            sb.append(" and male = true ");
        } else if (male != null && male == false) {
            sb.append(" and male = false ");
        }
        if (regionCode != null) {
            sb.append(" and region.parent.id  = :regionCode");
        }
        sb.append(" order by createdDatetime desc ");
        Query query = s.createQuery(sb.toString());

        if (age != null) {
            query.setInteger("age", age);
        }
        if (regionCode != null) {
            query.setInteger("regionCode", regionCode);
        }
        query.setMaxResults(3);
        query.setFirstResult(1);
        return query.list();

    }

    @Override
    public List<Works> getListByAgeAndMale0fPage(Integer age, boolean male, int index, Integer size) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (age != null) {
                hql = " from Works  where invalid = false and status = 3 and age= :age and male= :male and artist.status = 3  order by votenum desc  ";
            } else {
                hql = " from Works  where invalid = false and status = 3 and male=  :male and artist.status = 3  order by votenum desc  ";
            }
            Query query = s.createQuery(hql);
            if (age != null) {
                query.setInteger("age", age);
            }
            query.setBoolean("male", male);
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
    public List<Works> getListByRegionOfPage(Long region, int index, Integer size) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (region == null) {
                hql = " from Works  where invalid = false and status = 3 and artist.status=3 order by createdDatetime desc  ";
            } else {
                hql = " from Works  where invalid = false and status = 3 and  artist.status=3 and region.parent.id  =:region   order by createdDatetime desc  ";
            }

            Query query = s.createQuery(hql);
            if (region != null) {
                query.setLong("region", region);
            }
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
    public List<Works> getListByRegionAndMaleOfPage(Long region, boolean male, int index,
                                                    Integer size) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = "";
            if (region == null) {
                hql = " from Works  where invalid = false and status = 3 and artist.status=3  and male=:male order by createdDatetime desc  ";
            } else {
                hql = " from Works  where invalid = false and status = 3 and region.parent.id  = :region and artist.status=3  and male=:male order by createdDatetime desc  ";
            }

            Query query = s.createQuery(hql);
            if (region != null) {
                query.setLong("region", region);
            }
            query.setBoolean("male", male);
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
    public List<WorksTagItem> getByArtistidOfPage(long artist_id, Integer type, Integer index,
                                                  Integer size) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from WorksTagItem  where works.invalid = false and works.status = :type and works.artist.id = :artistid  and works.artist.status=3  order by works.votenum desc  ";
            Query query = s.createQuery(hql);
            query.setInteger("artistid", Integer.valueOf(String.valueOf(artist_id)));
            query.setInteger("type", type);
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
    public WorksTagItem getWorksTagItemByWorksid(Long worksid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from WorksTagItem  where works.id = :worksid  and works.artist.status=3  ";
            Query query = s.createQuery(hql);
            query.setLong("worksid", worksid);
            return (WorksTagItem) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    /**
     * app 作品展现
     */
    @Override
    public List<Works> getMobileWorksList(Works works) throws Exception {

        String hql = "from Works u ";
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query qs = session.createQuery(hql);
        List<Works> worksList = qs.list();
        return worksList;
    }

    /**
     * 上传作品 TODO 简单描述该方法的实现功能（可选）.
     */
    @Override
    @Transactional
    public long addMobileWork(Works works) throws Exception {
        return super.addMobile(works);

    }

    @Override
    public List<Map<String, Object>> getWorksInfoMobile(Long id) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "SELECT a.artist_id,ifnull(a.votenum,0) votenum,ifnull(a.praise,0),ifnull(a.comment_num,0) commentNum,a.name,d.name AS tagName,d.id as tagId,e.path,e.id as picId a.description FROM "
                     + " t_works a,t_works_picture_item b,t_works_tag_item c,t_tag d,t_picture e"
                     + " WHERE a.id=b.works_id  AND b.picture_id=e.id "
                     + "AND a.id=c.works_id AND c.tag_id=d.id and  a.id=:id and a.status=3  ";
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setLong("id", id);
        List<Map<String, Object>> mp = query.list();
        return mp;
    }

    @Override
    public void deleteWorksMobile(Long id) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql = " update  Works set invalid=:invalid where id=:id and artist.status=3  ";
        Query query = session.createQuery(hql);
        query.setBoolean("invalid", true);
        query.setLong("id", id);
        query.executeUpdate();
    }

    /**
     * 修改作品
     */
    @Override
    public void updateWorksMobile(Works works) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append(" update t_works  set  order_tag=order_tag ");
        List<Object> ls = new ArrayList<Object>();
        if (!Tools.isEmpty(works.getInstructor())) {
            hql.append("  ,instructor=?");
            ls.add(works.getInstructor());
        }
        if (!Tools.isEmpty(works.getInstructorMobile())) {
            hql.append("  ,instructor_mobile=?");
            ls.add(works.getInstructorMobile());
        }
        if (!Tools.isEmpty(works.getName())) {
            hql.append("  ,name=?");
            ls.add(works.getName());
        }
        if (!Tools.isEmpty(works.getDescription())) {
            hql.append("  ,description=?");
            ls.add(works.getDescription());
        }
        if (works.getStatus() > 1) {
            hql.append("  ,status=?");
            ls.add(works.getStatus());
        }
        if (!Tools.isEmpty(works.getSchool())) {
            hql.append("  ,school=?");
            ls.add(works.getSchool());
        }
        if (works.getAge() != null && works.getAge() > 0) {
            hql.append("  ,age=?");
            ls.add(works.getAge());
        }
        if (works.getRegion() != null && works.getRegion().getId() > 0) {
            hql.append("  ,region_id=?");
            ls.add(works.getRegion().getId());
        }
        if (works.getId() != null) {
            hql.append("  where id=?");
            ls.add(works.getId());
        } else {
            hql.append("  where artist_id=?");
            ls.add(works.getArtist().getArtistInfo().getId());
        }

        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(hql.toString());
        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i) + "");
        }
        query.executeUpdate();

    }

    /**
     * 根据 works——id查询画家作品 查看画家作品
     */
    @Override
    public Map<String, Object> getgetWorksByIdMobile(Long id) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "SELECT c.id as wtiId, a.instructor,a.instructor_mobile as instructorMobile,a.artist_id,a.id as worksId,a.status as worksStatus ,a.votenum,a.praise,a.name worksName,d.name AS tagName,d.id as tagId,e.path,e.orginpath,e.id as picId,a.description,  a.comment_num commentNum  FROM "
                     + " t_works a,t_works_picture_item b,t_works_tag_item c,t_tag d,t_picture e"
                     + " WHERE a.id=b.works_id  AND b.picture_id=e.id "
                     + "AND a.id=c.works_id AND c.tag_id=d.id and  a.id=:id  and a.invalid=false and a.status=3 ";
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setLong("id", id);
        Map<String, Object> mp = (Map<String, Object>) query.uniqueResult();
        return mp;

    }

    /**
     * 获取画家参赛作品列表 getArtistGameWorks
     * 
     * @author gwb
     * @param id
     * @return 2015年9月12日 下午3:13:04
     */
    @Override
    public List<Map<String, Object>> getArtistGameWorks(Long id, int pageNo, int pageSize,
                                                        int status) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        // String sql =
        // "SELECT c.name ,c.description,c.id AS worksId,g.path,f.name AS tagName,f.id AS tagId ,c.votenum,c.praise,  c.status AS worksStatus "
        // +
        // "FROM t_game a,t_game_works_item b,t_works c,t_works_picture_item d,t_works_tag_item e,t_tag f,t_picture g "
        // +
        // "WHERE  a.id=b.game_id AND b.works_id=c.id AND d.works_id=c.id AND d.picture_id=g.id AND e.works_id=c.id AND e.tag_id=f.id "
        // + "AND  c.artist_id=:id";

        String sql = "SELECT c.instructor,c.instructor_mobile as instructorMobile, e.id as wtiId, c.name as worksName ,c.description,c.id AS worksId,g.path,g.orginpath,f.name AS tagName,f.id AS tagId ,ifnull(c.votenum,0) votenum,ifnull(c.praise,0) praise,  c.status AS worksStatus,c.artist_id,g.id as  picId ,ifnull(c.comment_num,0) as commentNum "
                     + "FROM  t_works c,t_works_picture_item d,t_works_tag_item e,t_tag f,t_picture g "
                     + "WHERE   d.works_id=c.id AND d.picture_id=g.id AND e.works_id=c.id AND e.tag_id=f.id "
                     + "AND  c.artist_id=:id and c.invalid=0 ";

        if (status > 0) {
            sql += "  and c.status=:status";
        }

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setLong("id", id);
        if (status > 0) {
            query.setInteger("status", status);
        }

        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Map<String, Object>> mp = query.list();
        return mp;

    }

    /**
     * 审核作品 TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.myhome.dao.IWorksDAO#getCheckWorks(java.lang.Long, int)
     */
    @Override
    public int getCheckWorks(String id, int status) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        int i = 0;
        String hql = "update Works set status=:status where id:=id";
        Query qu = session.createQuery(hql);
        qu.setInteger("status", status);
        qu.setLong("id", Long.parseLong(id));
        i = qu.executeUpdate();
        return i;
    }

    /**
     * 获取最新作品
     */
    @Override
    public List<Map<String, Object>> getWorksMobile(Integer age, String region_code, String male,
                                                    int pageSize, int pageNo, String type,
                                                    int status, String typeRegion) throws Exception {
        // String typeRegion="1";
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "SELECT h.user_id userId,a.name worksName,a.description,a.artist_Id artist_id,IFNULL(a.votenum,0) votenum ,IFNULL(a.praise,0) praise,IFNULL(a.comment_num,0) commentNum, d.id AS picId,e.name AS regionName,e.region_code AS regionCode ,f.name AS tagName,f.id AS tagId, "
                     + " h.name AS artistName,h.male,d.path,d.orginpath,h.age,h.school,h.img ,a.id worksId "
                     + " FROM  t_works a,t_works_picture_item b,t_works_tag_item c,t_picture d,t_region e,t_tag f,t_artist g,t_artist_info h  "
                     + " WHERE a.id=b.works_id AND b.picture_id =d.id AND a.id=c.works_id AND c.tag_id=f.id AND h.region_id=e.id  "
                     + " AND a.artist_id=g.id  AND g.id=h.id and a.invalid=false  and g.status=3";

        // '%"+ clientname+"%'

        List<String> ls = new ArrayList<String>();
        if (age > 0) {
            sql += " and h.age=?";
            ls.add(age + "");
        }
        if (Tools.notEmpty(region_code)) {
            if (typeRegion.equals("1")) {
                sql += " and e.region_code like '" + region_code + "%'  ";
            } else if (typeRegion.equals("2")) {
                sql += " and e.region_id like '" + region_code + "%' ";
            }
        }
        if (Tools.notEmpty(male)) {
            sql += " and h.male=?";

            if (male.equals("true")) {
                ls.add("1");
            } else {
                ls.add("0");
            }
        }
        if (status > 0) {
            sql += " and a.status=? ";
            ls.add(status + "");
        }
        if (type.equals("1")) {
            sql += " order by a.created_datetime desc,artist_Id ";
        } else {
            sql += " order by IFNULL(a.votenum ,0) desc,artist_Id ";
        }

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i) + "");
        }
        List<Map<String, Object>> map = query.list();
        return map;
    }

    @Override
    public void addCommentNum(Long id) throws Exception {
        String hql = "update t_works set comment_num=ifnull(comment_num,0)+1 where id=:id";
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(hql);
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public Works getById(long id) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from Works  where id = :id and artist.status=3  ";
            Query query = s.createQuery(hql);
            query.setLong("id", id);
            return (query.list() == null || query.list().size() == 0) ? null : (Works) query.list()
                .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    // 统计作品数
    @Override
    public int getWorksCount() {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = "select count(works.id) from Works where artist.status = 3 and status = 3 and artist.artistInfo.region.parent = ";

        // 加载HQL语句
        Query query = session.createQuery(hql);
        Integer count = Integer.valueOf(query.uniqueResult().toString());
        session.close();
        return count;
    }

    // 审核通过对作品
    @Override
    @Transactional
    public List<Map<String, Object>> getWorksOnStateThree() throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = " select w.id,p.path FROM t_works w INNER JOIN t_artist a ON w.artist_id=a.id "
                + " INNER JOIN t_works_picture_item wpi ON w.id = wpi.works_id INNER JOIN t_picture p ON wpi.picture_id = p.id  "
                + " WHERE a.invalid=0 AND w.invalid=0 AND a.`status` = 3 AND w.`status` =3 ORDER BY RAND() LIMIT 6 ";
        // 加载HQL语句
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mp = query.list();
        return mp;
    }

    // 审核通过对作品
    public Map<String, Object> getTheWorkSort(Long worksid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String sql = "select id, (select Count(*) from t_works t1  where t1.invalid = false and t1.status=3 and "
                         + "(CASE WHEN t1.votenum IS NULL THEN 0 WHEN t1.votenum IS NOT NULL THEN t1.votenum END ) > "
                         + "(CASE WHEN t.votenum IS NULL THEN 0 WHEN t.votenum IS NOT NULL THEN t.votenum END) )+1 as sort "
                         + " from t_works t where id = :worksid";
            Query query = s.createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.setLong("worksid", worksid);
            return (Map<String, Object>) query.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    // 通过城市获取作品
    public int getWorksByRegion(Region region) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = "select count(id) from Works where status = 3 and artist.status = 3 and region.parent = :code";
        // 加载HQL语句
        Query query = session.createQuery(hql);
        query.setInteger("code", region.getRegionCode());
        Integer count = Integer.valueOf(query.uniqueResult().toString());
        session.close();
        return count;
    }

    // 这方法只能在批量上传的时候才能用，因为批量上传一个画家对应的是一张作品，否则会报query did not return a unique result错误
    public Works getWorksByArtistId(Works works) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = " from Works where 1=1 ";
            //作者ID
            if (null != works.getArtist().getId() && !"".equals(works.getArtist().getId())) {
                hql = hql + " and artist.id like :id ";
            }

            //时间排序
            hql = hql + " order by createdDatetime DESC ";
            //加载HQL语句
            Query query = session.createQuery(hql);
            //参数赋值
            //作者ID
            if (null != works.getArtist().getId() && !"".equals(works.getArtist().getId())) {
                query.setString("id", "%" + works.getArtist().getId() + "%");
            }
            Works work = (Works) query.uniqueResult();
            return work;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public int getArtistByRegion(Region region) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = "select count(id) from ArtistInfo where artist.invalid = false and artist.status = 3 and region.parent = :code";
        // 加载HQL语句
        Query query = session.createQuery(hql);
        query.setInteger("code", region.getRegionCode());
        Integer count = Integer.valueOf(query.uniqueResult().toString());
        session.close();
        return count;
    }

    @Override
    public List<WorksTagItem> findByKeywords(String keywords, String index, String size) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = " from WorksTagItem where 1=1 and works.status in(3) and works.artist.status in (3)";
        //作品名like
        if (null != keywords && !"".equals(keywords)) {
            hql = hql + " and (works.name like :name or works.description like :description )";
        }
        //时间排序
        hql = hql + " order by createdDatetime DESC ";
        //加载HQL语句
        Query query = session.createQuery(hql);
        //参数赋值
        //选手名字like
        if (null != keywords && !"".equals(keywords)) {
            query.setString("name", "%" + keywords + "%");
            query.setString("description", "%" + keywords + "%");
        }
        //分页
        query.setFirstResult((Integer.valueOf(index)) * Integer.valueOf(size));
        query.setMaxResults(Integer.valueOf(size));
        List<WorksTagItem> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<WorksTagItem> findList(Integer index,Integer size) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = " from WorksTagItem where 1=1 and works.status in(3) and works.invalid = false  order by rand() ";
        //加载HQL语句
        Query query = session.createQuery(hql);
        query.setFirstResult(index*size);
        query.setMaxResults(size);
        List<WorksTagItem> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Integer countByKeywords(String keywords) {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = " from WorksTagItem where 1=1 and works.status in(3) and works.artist.status in(3)";
        //作品名like
        if (null != keywords && !"".equals(keywords)) {
            hql = hql + " and (works.name like :name or works.description like :description )";
        }
        //加载HQL语句
        Query query = session.createQuery(hql);
        //参数赋值
        //选手名字like
        if (null != keywords && !"".equals(keywords)) {
            query.setString("name", "%" + keywords + "%");
            query.setString("description", "%" + keywords + "%");
        }
        List<WorksTagItem> list = query.list();
        session.close();
        return list.size();
    }

    //    public CityAndWork getWorksWithoutRegion(Region region) {
    //        
    //        Session s = getHibernateTemplate().getSessionFactory().openSession();
    //        try {
    //            String hql = "select count(id),region.parent.name from Works where status = 3 and artist.status = 3 ";
    //            Query query = s.createQuery(hql);
    //            return (query.list() == null || query.list().size() == 0) ? null : (CityAndWork) query.list()
    //                .get(0);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return null;
    //        } finally {
    //            s.close();
    //        }
    //    }
    @Override
    @Transactional
    public List<Map<String, Object>> getCitySort() throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select  r1.id,r1.`name`,count(*) as count from t_region r ,t_region r1 ,(select a2.region_id  from t_artist a1 INNER "
                + "JOIN t_artist_info  a2 on a1.id = a2.id where a1.invalid = false and a1.`status` =3 ) a where r.id = a.region_id "
                + "and  r.region_id=r1.id GROUP BY  r1.id order by  count  desc , r1.name desc ";
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mp = query.list();
        return mp;
    }

    @Override
    @Transactional
    public List<WorksTagItem> findListByType(Integer type,String size, String index, String condition,
                                             String age, String awards) {
    	 Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
         String hql = " from WorksTagItem where 1=1 and works.invalid = false  and works.status in(3) and works.artist.status in(3)";
         //作品名like
         hql = hql + "and works.type >= :type ";
         if (null != age && !"".equals(age)) {
             // 年龄段
             if ("1".equals(age)) {
                 hql = hql + " and works.artist.artistInfo.age in (3,4,5,6) ";
             } else if ("2".equals(age)) {
                 hql = hql + " and works.artist.artistInfo.age in (7,8,9) ";
             } else if ("3".equals(age)) {
                 hql = hql + " and works.artist.artistInfo.age in (10,11,12) ";
             }
         }
         if(null != awards && !"".equals(awards)){
        	 hql = hql+"and works.awards=:awards ";
         }
         hql = hql + " order by awards desc ";

         //加载HQL语句
         Query query = session.createQuery(hql);
         query.setInteger("type", type);
         if(awards != null  && !"".equals(awards)){
        	 query.setInteger("awards",Integer.valueOf(awards));
         }
         //分页
         query.setFirstResult((Integer.valueOf(index)) * Integer.valueOf(size));
         query.setMaxResults(Integer.valueOf(size));
         List<WorksTagItem> list = query.list();
         return list;
    }

    @Override
    @Transactional
    public Integer findCountByType(Integer type, String age,String awards) {
    	Session session = super.hibernateTemplate.getSessionFactory().getCurrentSession();
    	 String hql = "select count(works.id) from WorksTagItem where 1=1 and works.invalid = false  and works.status in(3) and works.artist.status in(3)";
         //作品名like
         hql = hql + "and works.type >= :type ";
         if (null != age && !"".equals(age)) {
             // 年龄段
             if ("1".equals(age)) {
                 hql = hql + " and works.artist.artistInfo.age in (3,4,5,6) ";
             } else if ("2".equals(age)) {
                 hql = hql + " and works.artist.artistInfo.age in (7,8,9) ";
             } else if ("3".equals(age)) {
                 hql = hql + " and works.artist.artistInfo.age in (10,11,12) ";
             }
         }
         if(null != awards && !"".equals( awards)){
        	 hql =hql+" and works.awards=:awards";
         }

         //加载HQL语句
         Query query = session.createQuery(hql);
         //参数赋值
         query.setInteger("type", type);
         if(awards != null && !"".equals(awards) ){
        	 query.setInteger("awards",Integer.valueOf(awards));
         }
         Integer count = Integer.valueOf(query.uniqueResult().toString());
         return count;
    }

    @Override
    public List<WorksTagItem> findPassWorks() {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        String hql = " from WorksTagItem where 1=1 and works.status in(3) and works.type !=0 order by rand()";
        //加载HQL语句
        Query query = session.createQuery(hql);
        //参数赋值
        //分页
        query.setMaxResults(6);
        List<WorksTagItem> list = query.list();
        session.close();
        return list;
    }

	@Override
	public List<Works> getWorksByTime( Integer index,Integer size) {
		Session session=super.hibernateTemplate.getSessionFactory().openSession();
            try {
            String hql="from WorksTagItem where 1=1 and works.invalid = false and works.status=3 and works.artist.status=3  and works.type!=0 order by  createdDatetime DESC ";
            Query query = session.createQuery(hql);
            query.setFirstResult((Integer.valueOf(index)) * Integer.valueOf(size));
            query.setMaxResults(Integer.valueOf(size));
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
	}

}
