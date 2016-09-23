package com.myhome.service;

import java.util.List;

import com.myhome.entity.Comment;



public interface ICommentService extends IService {

    public Comment get(Long id);

	public void add(Comment comment)  throws Exception;

	/**
	 * 查询作品评论
	 * getCommentListMobile
	 * @author gwb
	 * @param worksId
	 * @return
	 * @throws Exception
	 * 2015年9月17日 上午10:39:02
	 */
	public List<Comment> getCommentListMobile(Long worksId,int pageNo,int pageSize)  throws Exception;

////////////////////////////////////////////////////////////////

	    public void addweb(Comment comment);

	    public List<Comment> getByWorksid(long worksid);



}

