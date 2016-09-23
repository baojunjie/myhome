package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Activity;


public interface IActivityDAO extends IDAO {


	public List<Activity> getIActivity()  throws Exception;
	public List<Activity> get()  throws Exception;




}
