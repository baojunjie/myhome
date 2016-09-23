package com.myhome.service.impl;

import java.util.List;

import com.myhome.service.ICommentService;
import com.myhome.entity.Comment;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Component("commentServiceImpl")
public class CommentServiceImpl extends AbstractServiceImpl 
        implements ICommentService {

    
    
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
    public void add(Comment comment) throws Exception{
    	super.getWorksDAO().addCommentNum(comment.getWorks().getId());
    	getCommentDAO().add(comment);
    }
    
   

    /**
     * 查询作品评论
     */
	@Override
	@Transactional(readOnly=true)
    public List<Comment> getCommentListMobile(Long worksId,int pageNo,int pageSize) throws Exception {
	    
	    return super.getCommentDAO().getCommentListMobile(worksId, pageNo, pageSize);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    @Override
    @Transactional(readOnly=true)
    public Comment get(Long id) {
        return getCommentDAO().get(id);
    }
    
    
    @Override
    @Transactional
    public void addweb(Comment comment) {
        getCommentDAO().add(comment);
    }
    
    
    @Transactional
    public int update(Comment comment) {
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


    @Override
    public List<Comment> getByWorksid(long worksid) {
        return getCommentDAO().getByWorksid(worksid);
    }
   
}

