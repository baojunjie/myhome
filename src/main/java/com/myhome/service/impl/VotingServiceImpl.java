package com.myhome.service.impl;

import java.util.List;

import com.myhome.service.IVotingService;
import com.myhome.entity.Voting;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("votingServiceImpl")
public class VotingServiceImpl extends AbstractServiceImpl implements IVotingService {

	@Transactional(readOnly = true)
	public Voting get(Long id) {
		return getVotingDAO().get(id);
	}

	@Transactional
	public void add(Voting voting) {
		getVotingDAO().add(voting);
	}

	@Transactional
	public int update(Voting voting) {
		return getVotingDAO().update(voting);
	}

	@Transactional
	public int remove(Long id) {
		return getVotingDAO().remove(id);
	}

	@Transactional
	public int delete(Long id) {
		// TODO 待完成
		throw new RuntimeException();
	}

	/**
	 * 判断该用户一天给该作品投了多少票 getVotingNum     判断一个用户一天一共投了多少组票
	 * getVotingNum
	 * @author gwb
	 * @param works_id
	 * @param user_id
	 * @return
	 * 2015年9月17日 下午3:26:39
	 */
	@Override
    public int getVotingNum(Long works_id, Long user_id) {
	    
	  
		   return super.getVotingDAO().getVotingNum(works_id,user_id);  
	   
    }
////////////////////////////////////////////////////////////////////////

    @Override
    @Transactional
    public int getByUserAndWorks(long works_id, long user_id) {
        return getVotingDAO().getByUserAndWorks(works_id,user_id);
    }

    @Override
    @Transactional
    public int getByUser(long user_id) {
        return getVotingDAO().getByUser(user_id);
    }

	@Override
    public int getVotingNumByUserId(Long works_id, Long user_id) {
	return super.getVotingDAO().getVotingNumByUserId(user_id);  
	 
    }


}

