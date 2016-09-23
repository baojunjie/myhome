package com.myhome.service;

import java.util.List;

import com.myhome.entity.Parameter;

public interface IParameterService extends IService {

    public List<Parameter> get() throws Exception;
    
    public void add(Parameter parameter);

    public Parameter get(Long id);
    
    public int update(Parameter parameter) throws Exception;

}
