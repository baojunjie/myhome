package com.myhome.service;

import java.util.List;
import java.util.Map;

import com.myhome.entity.RecommendHelpPlayTour;
import com.myhome.entity.vo.RecommendHelpPlayTourVo;

public interface IRecommendHelpPlayTourService extends IService {

    public void add(RecommendHelpPlayTour recommendChildren) throws Exception;

    public List<Map<String, Object>> searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo, int t,int pageSize,int pageNo) throws Exception;

    public int searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo, int t) throws Exception;

}

