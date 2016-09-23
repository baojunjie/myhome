package com.myhome.entity;




/**
 *
 * 参赛作品
 *
 */
public class GameWorksItem extends AbstractEntity {

    private Game game ;
    
    private Works works ;
    
    /**
     *
     * 报名者
     *
     */
    private User applicant ;
    
    

    public Game getGame() {
        return this.game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public Works getWorks() {
        return this.works;
    }
    
    public void setWorks(Works works) {
        this.works = works;
    }
    
    public User getApplicant() {
        return this.applicant;
    }
    
    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
    
    



    
}
