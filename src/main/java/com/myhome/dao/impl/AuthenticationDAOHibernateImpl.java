package com.myhome.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.myhome.dao.IAuthenticationDAO;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.utils.Tools;

@Component("authenticationDAOHibernateImpl")
public class AuthenticationDAOHibernateImpl extends AbstractDAOHibernateImpl implements
                                                                            IAuthenticationDAO {

    @Override
    public Authentication get(Long id) {
        return (Authentication) super.get(id);
    }

    @Override
    protected Class<?> getEntityClass() {

        return Authentication.class;
    }

    /**
     * 用户登录，根据用户名和密码查询用户信息
     */
    @Override
    public Authentication getMobileByAuthenticationIdAndPaswword(Authentication auth)
                                                                                     throws Exception {

        String hql = "from Authentication u where u.id=:id and u.password=:password";

        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query qs = session.createQuery(hql);
        qs.setLong("id", 123);
        qs.setString("password", "234234");
        Authentication authentication = (Authentication) qs.uniqueResult();
        session.close();
        // Authentication authentication = new Authentication();
        // List<Authentication> list= (List<Authentication>)
        // hibernateTemplate.find("from Authentication u where u.id=? and u.password=?",new
        // Object[]{auth.getLogin(),auth.getPassword()});
        // if(list.size()>0){
        // authentication=list.get(0);
        // }
        return authentication;
    }

    /**
     * 用户注册，根据用户名查询用户是否已经注册
     * 
     */
    @Override
    public Authentication getMobileAuthenticationByName(Authentication authentication)
                                                                                      throws Exception {
        String hql = "from Authentication u where u.login=:login";
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query qs = session.createQuery(hql);
        qs.setString("login", authentication.getLogin());
        Authentication autn = (Authentication) qs.uniqueResult();
        return autn;
    }

    @Override
    public int getMobileAuthenticationByNameCount(Authentication authentication)
                                                                                      throws Exception {
        String hql = "Select count(id) from Authentication u where u.login=:login";
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        
        Query qs = session.createQuery(hql);
        qs.setString("login", authentication.getLogin());
        Integer count = Integer.valueOf(qs.uniqueResult().toString());
        return count;
    }

    /**
     * 用户注册，根据token是否已经注册
     * 2.0新增
     */
    @Override
    public List<Authentication> getMobileAuthenticationByToken(String token,String type)
                                                                                      throws Exception {
        String hql = "from Authentication where login=:token and type=:type";
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql);
        query.setString("token", token);
        query.setString("type", type);
        return query.list();


    }

    
//    /**
//     * 用户注册，根据userId和type查找手机号
//     * 2.1新增
//     */
//    @Override
//    public List<Authentication> getMobileAuthenticationByUserIdAndType(String userId,String type)
//                                                                                      throws Exception {
//        String hql = "from Authentication where user.id=:userId and type=:type";
//        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Query query = session.createQuery(hql);
//        query.setString("user.id", userId);
//        query.setString("type", type);
//        return query.list();
//
//
//    }

    @Override
    public Long addMobileAuthentication(Authentication authentication) throws Exception {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        session.save(authentication);
        session.close();
        return authentication.getId();
    }

    /**
     * 修改用户登录密码
     */
    @Override
    public Long updateMobileAuthenticationPassword(Authentication authentication) throws Exception {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        String sql = "update Authentication set password=:password where login=:login";
        Query qs = session.createQuery(sql);
        qs.setString("login", authentication.getLogin());
        qs.setString("password", authentication.getPassword());
        qs.executeUpdate();
        return authentication.getId();
    }

    @Override
    public Authentication getMobileByAuthenticationNameAndPaswword(Authentication au)
                                                                                     throws Exception {
        String hql = "from Authentication u where u.login=:login and password=:password";
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query qs = session.createQuery(hql);
        qs.setString("login", au.getLogin());
        qs.setString("password", au.getPassword());
        Authentication autn = (Authentication) (qs.uniqueResult() == null ? null : qs
            .uniqueResult());
        return autn;
    }

    /**
     * 
     *  获取当前登录用户详情
     *  
     *  如果该用户已经提交参赛申请者，查询出相应的artis信息
     */
    @Override
    public Map<String, Object> getUserArtistInfoMobile(Long id) throws Exception {
        Session session = super.hibernateTemplate.getSessionFactory().openSession();
        //      String sql = "SELECT * FROM ( SELECT  aa.name AS userName, aa.id AS userId,aa.status AS userStatus, bb.status, "
        //              + "bb.birthday ,bb.constellation, "
        //              + "bb.id_code,bb.img,bb.instructor,bb.instructor_mobile, "
        //              + "bb.male,bb.name,bb.nation,bb.parent_mobile,bb.referrer_school,bb.referrer_mobile,bb.school,bb.school_mobile, "
        //              + "bb.teacher,bb.teacher_mobile,bb.zodiac,  "
        //              + "bb.districtName,  bb.districtCode, bb.cityName,bb.cityCode,  bb.provinceName,  bb.provinceCode, bb.artist_id,bb.age FROM t_user_info aa LEFT JOIN  "
        //              + "(SELECT c.*,a.applicant_id,f.name AS districtName,f.id districtCode,g.name AS cityName,g.id cityCode,h.name AS provinceName,h.id provinceCode   FROM  "
        //              + "t_game_works_item a,t_artist b,t_artist_info c,t_works d, t_region f , t_region g, t_region h    "
        //              + "WHERE  a.works_id=d.id AND b.id=d.artist_id AND b.id=c.artist_id  AND g.id = LEFT(c.region_id, 4) AND h.id = LEFT(c.region_id, 2) AND c.region_id = f.id  AND a.applicant_id=:applicant_id LIMIT 1  "
        //              + ") bb ON aa.id=bb.applicant_id     ) cc where  cc.userId=:id";

        String hql = " from ArtistInfo where 1=1";

        String sql = "SELECT a.name AS userName,a.id AS userId,k.status AS userStatus,b.status,c.birthday,c.constellation, c.id_code,c.img,c.instructor,"
                     + "  c.instructor_mobile, c.male,c.name, c.nation,c.parent_mobile,c.referrer_school,c.referrer_mobile, c.school,"
                     + "  c.school_mobile,c.teacher,c.teacher_mobile,c.zodiac, f.name AS districtName, f.region_code districtCode,"
                     + "  g.name cityName, g.region_code cityCode,h.name provinceName, h.region_code provinceCode, b.id AS artist_id, c.age,c.orginimg,c.origin "
                     + "  FROM "
                     + "  t_user_info a LEFT JOIN t_user k ON a.id=k.id  LEFT JOIN t_artist_info c   ON a.id = c.user_id  LEFT JOIN t_artist b   ON b.id = c.id "
                     + " LEFT JOIN t_region f   ON c.region_id = f.id  LEFT JOIN t_region g  ON g.id = LEFT(c.region_id, 4) LEFT JOIN t_region h ON  h.id = LEFT(c.region_id, 2) "
                     + "  where a.id=:id limit 1";

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setLong("id", id);
        //query.setLong("applicant_id", id);
        List<Map<String, Object>> list = query.list();

        Map<String, Object> mp = new HashMap<String, Object>();

        if (list.size() > 0) {
            mp = list.get(0);
        }

        session.close();
        return mp;
    }

    /**
     * 修改artistinfo休息
     */
    @Override
    public void updateArtistInfoMobile(ArtistInfo artistInfo) throws Exception {
        ArtistInfo art = super.getHibernateTemplate().get(ArtistInfo.class, artistInfo.getId());
        artistInfo.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        if (Tools.isEmpty(artistInfo.getImg())) {
            artistInfo.setImg(art.getImg());
            artistInfo.setOrginimg(art.getOrginimg());
        }
        //artistInfo.setAge(art.getAge());
        artistInfo.setCreatedDatetime(art.getCreatedDatetime());
        artistInfo.setUser(art.getUser());
        Session session = super.getHibernateTemplate().getSessionFactory().openSession();
        session.update(artistInfo);
        session.close();
        this.getHibernateTemplate().flush();
        this.getHibernateTemplate().clear();
        super.getHibernateTemplate().update(artistInfo);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Authentication> findByLogin(String mobile) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String Hql = "from Authentication u where u.login=:login ";
            Query query = s.createQuery(Hql);
            query.setString("login", mobile);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public void updateArtistInfoMobilePhoto(ArtistInfo artistInfo) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String sql = "update  t_artist_info set orginimg=:orginimg ,img=:img where id=:id";
            Query query = s.createSQLQuery(sql);
            query.setString("orginimg", artistInfo.getOrginimg());
            query.setString("img", artistInfo.getImg());
            query.setLong("id", artistInfo.getId());
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

    }

    @Override
    public Authentication getByUserid(Long userid) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from Authentication where user.id = :userid ";
            Query query = s.createQuery(hql);
            query.setLong("userid", userid);
            return ((query.list() == null || query.list().size() == 0) ? null
                : (Authentication) query.list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();

        }
    }

    @Override
    public Authentication getByUseridAndType(Long userid,String type) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String hql = " from Authentication where user.id = :userid  and type=:type";
            Query query = s.createQuery(hql);
            query.setLong("userid", userid);
            query.setString("type", type);
            return ((query.list() == null || query.list().size() == 0) ? null
                : (Authentication) query.list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();

        }
    }

    @Override
	public List<Authentication> findByLoginOrName(String mobile) {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        try {
            String Hql = "from Authentication u where u.login=:login or user.userInfo.name=:name ";
            Query query = s.createQuery(Hql);
            query.setString("login", mobile);
            query.setString("name", mobile);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

}
