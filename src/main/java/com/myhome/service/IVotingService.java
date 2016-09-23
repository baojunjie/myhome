package com.myhome.service;

import java.util.List;

import com.myhome.entity.Voting;



public interface IVotingService extends IService {

    public Voting get(Long id);

    public void add(Voting voting) ;
    
    
    public int update(Voting voting) ;

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
     * 你每天只能给10组作品投票哦，明天再来吧！
     * @param user_id
     * @return
     */
    public int getByUser(long user_id);

	public int getVotingNumByUserId(Long works_id, Long user_id);
    



}
