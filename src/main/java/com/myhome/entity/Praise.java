package com.myhome.entity;



/**
 *
 * 点赞记录
 *
 */
abstract public class Praise extends AbstractEntity {

    /**
     *
     * 投票人
     *
     */
    private User user ;

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
    
    



    
}
