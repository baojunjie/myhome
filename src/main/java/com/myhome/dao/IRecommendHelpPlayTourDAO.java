package com.myhome.dao;

import java.util.List;
import java.util.Map;

import com.myhome.entity.vo.RecommendHelpPlayTourVo;

public interface IRecommendHelpPlayTourDAO extends IDAO{

    public List<Map<String, Object>> searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo,int t,int pageSize,int pageNo) throws Exception;
    public int searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo,int t) throws Exception;
}
