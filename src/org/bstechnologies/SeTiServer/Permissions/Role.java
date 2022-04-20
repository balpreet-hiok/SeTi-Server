package org.bstechnologies.SeTiServer.Permissions;

import java.util.Arrays;

public class Role {
    private String roleName;
    private String [] permissions = new String[1];
    public Role(){}
    public void setRoleName(String roleName){this.roleName=roleName;}
    public String getRoleName(){return this.roleName;}
    public void addPermission(String name){
        permissions[permissions.length-1] = name;
        permissions = Arrays.copyOf(permissions,permissions.length+1);
    }
    public boolean hasPermission(String name){
        for(int i=0;i<permissions.length;i++){
            if(name.equals(permissions[i]))return true;
        }
        return false;
    }
    public String [] getPermissions(){return this.permissions;}
}
