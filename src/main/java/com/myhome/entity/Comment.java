package com.myhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * 对作品的评论
 *
 */
public class Comment extends AbstractEntity {

	/**
     *
     * 针对的作品
     *
     */
	@JsonIgnore
    private Works works ;
    
    /**
     *
     * 评论人
     *
     */
    private User user ;
    
    /**
     *
     * 评论内容
     *
     */
    private String words;
    
    

    public Works getWorks() {
        return this.works;
    }
    
    public void setWorks(Works works) {
        this.works = works;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getWords() {
        return this.words;
    }
    
    public void setWords(String words) {
        this.words = words;
    }
    
    



    
}
