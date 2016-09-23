package com.myhome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Page;
import com.myhome.entity.User;
import com.myhome.service.IPageService;

@Component("pageServiceImpl")
public class PageServiceImpl extends AbstractServiceImpl implements IPageService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<Page> get(int i)  throws Exception{

		return getPageDAO().get(i);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public List<Page> addTest(Page page)  {

	
		List<Page> pagelist=new ArrayList<Page>();
		Page page1 =new Page();
		page1.setId(8l);
		page1.setType(12);
		page1.setName("name2");
		pagelist.add(page1);
		
		Page page12 =new Page();
		page12.setId(9l);
		page12.setName("name2");
		page12.setType(2);
		pagelist.add(page12);
		try {
			
			for(Page page123:pagelist){
				getPageDAO().addTest2(page123);
			}
			
			page.setName(page.getName() + "test");
	       // getPageDAO().addTest(page);
        } catch (Exception e) {
	        e.printStackTrace();
	       throw new RuntimeException();
          
        }

		/*
		 * Session session=sessionFactory.openSession(); Transaction
		 * t=session.beginTransaction(); //t.begin(); try {
		 * getPageDAO().addTest2(page,session);
		 * page.setName(page.getName()+"test");
		 * getPageDAO().addTest(page,session); t.commit(); } catch (Exception e)
		 * { t.rollback(); e.printStackTrace(); }finally{ session.close(); }
		 */

		return null;
	}

	@Override
	public void addTest2(Page page) {
		// getPageDAO().addTest2(page);

	}

	@Override
	@Transactional
    public boolean updateTest(Page page) throws Exception {
	    super.getPageDAO().updateTest(page);
	    return false;
    }

	@Override
	@Transactional
    public boolean updateUser(User page) throws Exception {
	    
		 super.getPageDAO().updateUser(page);
	    return false;
    }

	@Override
	@Transactional
	public List<Page> getByType(int i) {
		return getPageDAO().getByType(i);
	}

}

