package com.myhome.service;

import java.util.List;

import com.myhome.entity.Activity;

public interface IActivityService extends IService {

    public List<Activity> get() throws Exception;
    
    public List<Activity> getIActivity() throws Exception;

}
