package com.myhome.dao;

import com.myhome.entity.AbstractEntity;



public interface IDAO {

    public void add(AbstractEntity abstractEntity);


    public int update(AbstractEntity abstractEntity);


    public int remove(Long id);


    public int delete(Long id);




}
