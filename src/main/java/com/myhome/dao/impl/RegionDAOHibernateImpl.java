package com.myhome.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.myhome.dao.IRegionDAO;
import com.myhome.entity.Region;

@Component("regionDAOHibernateImpl")
public class RegionDAOHibernateImpl extends AbstractDAOHibernateImpl implements IRegionDAO {

	@Override
	public Region get(Long id) {
		return (Region) super.get(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Region.class;
	}

	@Override
	public List<Map> getRegionListMobile(int level) throws Exception {

		String sql = "select name,region_id,region_code from t_Region where level=:level  ";
		Session session = super.hibernateTemplate.getSessionFactory().openSession();
		Query qu = session.createSQLQuery(sql);
		qu.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		qu.setInteger("level", level);
		List<Map> map = qu.list();
		session.close();
		return map;
	}

	@Override
	public List<Map> getRegionParentByCodeMobile(Integer regionCode) throws Exception {

		String sql = "select name,region_id,region_code from t_Region where region_id=:regionCode";
		Session session = super.hibernateTemplate.getSessionFactory().openSession();
		Query qu = session.createSQLQuery(sql);
		qu.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		qu.setInteger("regionCode", regionCode);
		List<Map> list = qu.list();
		session.close();
		return list;
	}

	@Override
	public Region getRegionByName(String searchregion) {
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {
			String hql = " from Region  where name like :searchregion and  level !=1";
			Query query = s.createQuery(hql);
			query.setString("searchregion", "%" + searchregion + "%");
			return (query.list() == null || query.list().size() == 0) ? null : (Region) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}
	}

	@Override
	public List<Map<String, String>> getRegion(Long code,Integer  level) throws Exception {
		
		    Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		    
			String codeStr=code+"";
			List<Map<String, String>> ls=new ArrayList<Map<String,String>>();
			StringBuffer str=new StringBuffer();
			Map<String,String> map3=new HashMap<String,String>();
			for(int i=1;i<4;i++){
				String hql = " select name,region_code as regionCode,level from  t_region  where region_code=?";
				Query query = s.createSQLQuery(hql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if(i==1){
					query.setString(0, codeStr.substring(0,2));
					Map<String,String> map=new HashMap<String,String>();
					map =(Map<String, String>) query.uniqueResult();
					//str.append(map.get("name"));
					ls.add(map);
				}else if(i==2){
					query.setString(0, codeStr.substring(0,4));
					Map<String,String> map2=new HashMap<String,String>();
					map2 =(Map<String, String>) query.uniqueResult();
					//str.append(map2.get("name"));
					ls.add(map2);
				}else if(i==3){
					query.setLong(0, code);
					Map<String,String> map1=new HashMap<String,String>();
					query.list();
					map1 =(Map<String, String>) query.uniqueResult();
					//str.append(map1.get("name"));
					ls.add(map1);
				}
			}
			//map3.put("region", str.toString());
			//ls.add(map3);
		return ls;
			
		 
	}

}
