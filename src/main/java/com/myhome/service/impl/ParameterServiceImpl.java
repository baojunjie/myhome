package com.myhome.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Parameter;
import com.myhome.service.IParameterService;

@Component("parameterServiceImpl")
public class ParameterServiceImpl extends AbstractServiceImpl implements IParameterService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<Parameter> get()  throws Exception{

		return super.getParameterDAO().get();
	}

    @Transactional
    public void add(Parameter parameter) {
        getParameterDAO().add(parameter);
    }
    
    @Transactional
    public int update(Parameter parameter) throws Exception{
        return getParameterDAO().update(parameter);
    }

    @Transactional
    public Parameter get(Long id){
        return getParameterDAO().get(id);
    }
}

