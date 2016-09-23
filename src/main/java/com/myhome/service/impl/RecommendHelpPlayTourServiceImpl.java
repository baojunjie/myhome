package com.myhome.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.RecommendHelpPlayTour;
import com.myhome.entity.vo.RecommendHelpPlayTourVo;
import com.myhome.service.IRecommendHelpPlayTourService;

@Component("recommendChildrenServiceImpl")
public class RecommendHelpPlayTourServiceImpl extends AbstractServiceImpl implements IRecommendHelpPlayTourService {

	@Override
	@Transactional
    public void add(RecommendHelpPlayTour recommendChildren) throws Exception {
		
		super.getRecommendChildrenDAO().add(recommendChildren);
    }
    @Override
    @Transactional
    public List<Map<String, Object>> searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo,int t,int pageSize,int pageNo) throws Exception {
        
        return getRecommendChildrenDAO().searchRecommendChildren(recommendChildrenVo,t, pageSize, pageNo);
    }
    @Override
    @Transactional
    public int searchRecommendChildren(RecommendHelpPlayTourVo recommendChildrenVo,int t) throws Exception {
        
        return getRecommendChildrenDAO().searchRecommendChildren(recommendChildrenVo,t);
    }
}

