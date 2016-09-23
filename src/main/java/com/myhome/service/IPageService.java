package com.myhome.service;

import java.util.List;

import com.myhome.entity.Page;
import com.myhome.entity.User;

public interface IPageService extends IService {

    public List<Page> get(int i) throws Exception;

    public List<Page> addTest(Page page) throws Exception;

    public void addTest2(Page page);

    public boolean updateTest(Page page) throws Exception;

    public boolean updateUser(User page) throws Exception;

	public List<Page> getByType(int i);

}
