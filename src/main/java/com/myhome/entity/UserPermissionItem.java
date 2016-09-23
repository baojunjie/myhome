package com.myhome.entity;




/**
 *
 * 用户拥有的权限，这个往往是独立于角色之外的
 *
 */
public class UserPermissionItem extends AbstractEntity {

    private User user;
    
    private Permission permission;
    
    

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Permission getPermission() {
        return this.permission;
    }
    
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    
    



    
}
