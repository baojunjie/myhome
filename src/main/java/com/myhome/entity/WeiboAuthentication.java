package com.myhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * 微博登录：此时 login 为微博的token
 *
 */
public class WeiboAuthentication extends Authentication {

    private String accessToken;

    private String name;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
