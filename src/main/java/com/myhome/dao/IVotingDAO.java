package com.myhome.dao;

import java.util.List;

import com.myhome.entity.Voting;



public interface IVotingDAO extends IDAO {

    public Voting get(Long id);

	public int getByUserAndWorks(long works_id, long user_id);
	/**
	 * 判断该用户一天给该作品投了多少票
	 * getVotingNum
	 * @author gwb
	 * @param works_id
	 * @param user_id
	 * @return
	 * 2015年9月17日 下午3:26:39
	 */
    public int getVotingNum(Long works_id, Long user_id);
    /**
     * 10个作品
     * @param user_id
     * @return
     */
    public int getByUser(long user_id);

    public int getVotingNumByUserId(Long user_id);



}
