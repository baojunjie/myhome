package com.myhome.entity;

/**
 * 认证
 */


public class Authentication extends AbstractEntity {

    private User user;
    
    /**
     *
     * 可能是 账号、email、手机号码，
     * 也可能是 QQ、微信、微博 这样的第三方的 token，
     * 如果是 token，此时 password 就为 null
     *
     */
    private String login;
    
    private String password;
    
    private String salt;

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return this.salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
}
