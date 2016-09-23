package com.myhome.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Activity;
import com.myhome.service.IActivityService;

@Component("activityServiceImpl")
public class ActivityServiceImpl extends AbstractServiceImpl implements IActivityService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<Activity> get()  throws Exception{

		return super.getActivityDAO().get();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Activity> getIActivity(
			) throws Exception {
		return super.getActivityDAO().getIActivity();
	}

}

