package com.myhome.entity;




/**
 *
 * 用户拥有的角色
 *
 */
public class UserRoleItem extends AbstractEntity {

    private User user;
    
    private Role role;
    
    

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    



    
}
