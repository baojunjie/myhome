package com.myhome.entity;




/**
 *
 * 投票(打分)记录
 *
 */
public class Voting extends AbstractEntity {

    /**
     *
     * 投票人
     *
     */
    private User user;
    /**
    *
    * 作品
    *
    */
   private Works works;
    
    /**
     *
     * 给出的评分
     *
     */
    private Integer point = 1;
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Integer getPoint() {
        return this.point;
    }
    
    public void setPoint(Integer point) {
        this.point = point;
    }

	public Works getWorks() {
		return works;
	}

	public void setWorks(Works works) {
		this.works = works;
	}
    
    



    
}
