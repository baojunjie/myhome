package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IUserInfoDAO;
import com.myhome.entity.UserInfo;

@Component("userInfoDAOHibernateImpl")
public class UserInfoDAOHibernateImpl extends AbstractDAOHibernateImpl implements IUserInfoDAO {

	@Override
	public UserInfo get(Long id) {
		return (UserInfo) super.get(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return UserInfo.class;
	}

	@Transactional
	@Override
	public List<UserInfo> findByMobile(String mobile) {
		Session s = hibernateTemplate.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<UserInfo> list = s.createCriteria(UserInfo.class).add(Restrictions.eq("mobile", mobile)).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}
	}

	@Override
	public void save(UserInfo userinfo) {
		getHibernateTemplate().save(userinfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findByMobileAndPassword(String mobile, String password) {
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {
			// String Hql =
			// "from UserInfo where mobile=:mobile and password=:password ";
			// List<UserInfo> list = (List<UserInfo>)
			// getHibernateTemplate().findByNamedParam(Hql, new
			// String[]{"mobile","password"}, new String[]{mobile,password});
			// return list;
			String Hql = "from UserInfo where mobile=:mobile and password=:password";
			Query query = s.createQuery(Hql);
			query.setString("mobile", mobile);
			query.setString("password", password);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}
	}

	@Override
	public List<UserInfo> findByName(String usename) {
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {
			String Hql = "from UserInfo where name=:usename";
			Query query = s.createQuery(Hql);
			query.setString("usename", usename);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}
	}

	@Override
	public void updateUserMobile(UserInfo userInfo) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer();
		List<String> ls = new ArrayList<String>();
		hql.append(" update UserInfo set status=status");

		if (userInfo.getProfession() != null) {
			hql.append(" ,profession=?");
			ls.add(userInfo.getProfession());
		}
		if (userInfo.getBirthday() != null) {
			hql.append(" ,birthday=?");
			ls.add(userInfo.getBirthday() + "");
		}
		if (userInfo.getName() != null) {
			hql.append(" ,name=?");
			ls.add(userInfo.getName());
		}
		if (userInfo.getNickName() != null) {
			hql.append(" ,nickName=?");
			ls.add(userInfo.getNickName());
		}
		if (userInfo.getImg() != null) {
			hql.append(" ,img=?");
			ls.add(userInfo.getImg());
		}
		if (userInfo.getOriginImg() != null) {
			hql.append(" ,originImg=?");
			ls.add(userInfo.getOriginImg());
		}
		if (userInfo.getType() != null) {
			hql.append(" ,type=?");
			ls.add(userInfo.getType());
		}
		if (userInfo.getNation() != null) {
			hql.append(" ,nation=?");
			ls.add(userInfo.getNation());
		}
		if (userInfo.getMobile() != null) {
			hql.append(" ,mobile=?");
			ls.add(userInfo.getMobile());
		}
		if (userInfo.getRegion() != null && userInfo.getRegion().getId() != null) {
			hql.append(" ,region.id=?");
			ls.add(userInfo.getRegion().getId() + "");
		}
		if (userInfo.getConstellation() != null) {
			hql.append(" , constellation=?");
			ls.add(userInfo.getConstellation());
		}
		if (userInfo.getZodiac() != null) {
			hql.append(" , zodiac=?");
			ls.add(userInfo.getZodiac());
		}
		if (userInfo.getMale() != null) {

			if (userInfo.getMale() == true) {
				hql.append(" , male=?");
				ls.add("1");
			} else if (userInfo.getMale() == false) {
				hql.append(" , male=?");
				ls.add("0");
			}

		}

		hql.append(" where id=?");
		ls.add(userInfo.getId() + "");
		Query query = session.createQuery(hql.toString());
		for (int i = 0; i < ls.size(); i++) {
			query.setString(i, ls.get(i) + "");
		}
		query.executeUpdate();

		// UserInfo us=(UserInfo) session.get(UserInfo.class, userInfo.getId());
		// Tools.copyProperties(userInfo, us);
		// session.update(us);

	}

	@Override
	public void updateMobileUserScore(UserInfo userInfo) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = " update UserInfo set score=score+:scroe where id=:id";
		int score = 0;
		String hql1 = " from  UserInfo where invitationCode=:invitationCode";

		// 自己填写调查问卷，获取十分，没有填写邀请码
		if (userInfo.getInvitationCode() == null || "".equals(userInfo.getInvitationCode())) {
			score = 10;
			Query query1 = session.createQuery(hql);
			query1.setInteger("scroe", score);
			query1.setLong("id", userInfo.getId());
			query1.executeUpdate();
		} else {
			// 填写邀请码
			Query query = session.createQuery(hql1);
			query.setString("invitationCode", userInfo.getInvitationCode());
			UserInfo info = (UserInfo) query.uniqueResult();
			// 邀请码不存在
			if (info == null) {
				score = 10;
				Query query1 = session.createQuery(hql);
				query1.setInteger("scroe", score);
				query1.setLong("id", userInfo.getId());
				query1.executeUpdate();
			} else if (userInfo.getInvitationCode() != info.getInvitationCode()) {
				// 邀请码存在 ，自己获取十分
				score = 10;
				Query query1 = session.createQuery(hql);
				query1.setInteger("scroe", score);
				query1.setLong("id", userInfo.getId());
				query1.executeUpdate();
				// 邀请码存在，邀请人获取十分
				Query query2 = session.createQuery(hql);
				query2.setInteger("scroe", 5);
				query2.setLong("id", info.getId());
				query2.executeUpdate();
			} else {
				// 邀请码存在 ，自己获取十分
				score = 10;
				Query query1 = session.createQuery(hql);
				query1.setInteger("scroe", score);
				query1.setLong("id", userInfo.getId());
				query1.executeUpdate();

			}

		}

	}

	@Override
	public void updateUserMobileInvitationCode(Long id, String serialCode) throws Exception {
		String sql = "  update t_user_info set invitation_code=:invitationCode where id=:id ";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		query.setLong("id", id);
		query.setString("invitationCode", serialCode);
		query.executeUpdate();
	}

}
