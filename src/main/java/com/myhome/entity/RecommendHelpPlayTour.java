package com.myhome.entity;

/**
 * 
 * 我推荐的
 * 
 * @author gwb
 */
public class RecommendHelpPlayTour extends AbstractEntity {

    private User    userFrom; // 推荐/打赏/帮助人

    private User    userTo;  // 被推荐/打赏/帮助人

    private Double  money;   // 打赏的钱

    private Integer type;    // 角色类型 1普通用（默认）,2画家，3受捐者，4老师，5赞助商,6同时是画家和受捐者

    private String  message; // 寄语

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
