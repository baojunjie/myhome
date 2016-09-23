package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Comment;



public interface ICommentDAO extends IDAO {

    public Comment get(Long id);

    public List<Comment> getByWorksid(long worksid);
    /**
     * 查询做评论
     * getCommentListMobile
     * @author gwb
     * @param worksId
     * @return
     * 2015年9月17日 上午10:39:46
     */
	public List<Comment> getCommentListMobile(Long worksId,int pageNo,int pageSize) throws Exception;




}

