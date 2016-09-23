package com.myhome.entity;

import java.sql.Timestamp;



/**
 *
 * 比赛
 *
 */
public class Game extends AbstractEntity {

    private String title;
    
    /**
     *
     * 比赛开始时间
     *
     */
    private Timestamp beginDatetime;
    
    /**
     *
     * 比赛结束时间
     *
     */
    private Timestamp endDatetime;
    
    

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Timestamp getBeginDatetime() {
        return this.beginDatetime;
    }
    
    public void setBeginDatetime(Timestamp beginDatetime) {
        this.beginDatetime = beginDatetime;
    }
    
    public Timestamp getEndDatetime() {
        return this.endDatetime;
    }
    
    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }
    
    



    
}
