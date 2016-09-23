package com.myhome.entity;




/**
 *
 * 这么设计纯粹是为了以后的扩展，因为现在搞不清楚用户和艺术家之间的关系
 *
 */
public class UserArtistItem extends AbstractEntity {

    /**
     *
     * 评论人
     *
     */
    private User user;
    
    private Artist artist;
    
    

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Artist getArtist() {
        return this.artist;
    }
    
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    



    
}
