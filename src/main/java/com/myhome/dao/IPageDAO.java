package com.myhome.dao;

import java.util.List;

import org.hibernate.Session;

import com.myhome.entity.Page;
import com.myhome.entity.User;


public interface IPageDAO extends IDAO {


	public List<Page> get(int i)  throws Exception;

	public List<Page> addTest(Page page) throws Exception;

	public Object addTest2(Page page) throws Exception;

	public void updateTest(Page page) throws Exception;

	public void updateUser(User page) throws Exception;

	public List<Page> getByType(int i);




}
