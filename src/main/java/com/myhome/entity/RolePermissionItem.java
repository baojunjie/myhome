package com.myhome.entity;




/**
 *
 * 角色拥有的权限
 *
 */
public class RolePermissionItem extends AbstractEntity {

    private Role role = new Role();
    
    private Permission permission;
    
    

    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public Permission getPermission() {
        return this.permission;
    }
    
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    
    



    
}
