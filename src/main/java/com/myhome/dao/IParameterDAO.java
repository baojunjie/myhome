package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Parameter;


public interface IParameterDAO extends IDAO {


	public List<Parameter> get()  throws Exception;

    public Parameter get(Long id);



}
