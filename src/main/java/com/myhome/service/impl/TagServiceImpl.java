package com.myhome.service.impl;

import java.util.List;

import com.myhome.service.ITagService;
import com.myhome.entity.Region;
import com.myhome.entity.Tag;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("tagServiceImpl")
public class TagServiceImpl extends AbstractServiceImpl 
        implements ITagService {

    

    



    @Override
    @Transactional(readOnly=true)
    public Tag get(Long id) {
        return getTagDAO().get(id);
    }
    
    
    @Transactional
    public void add(Tag tag) {
        // TODO 待完成
    }
    
    
    @Transactional
    public int update(Tag tag) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int remove(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int delete(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }


    /**
     * 作品类别列表
     * TODO 简单描述该方法的实现功能（可选）.
     * gwb
     */
	@Override
	@Transactional(readOnly=true)
    public List<Tag> getTagList() throws Exception{
	    return super.getTagDAO().getTagList();
    }


	@Override
	public List<Region> getListByLevelAndCode(Integer level, String code) {
		return getTagDAO().getListByLevelAndCode(level,code);
	}


	@Override
	public List<Tag> getTagListByName(String worksType) {
		 List<Tag> tagList = getTagDAO().getTagListByName(worksType);
		return tagList;
	}
    
    
    

}
