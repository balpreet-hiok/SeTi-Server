package org.bstechnologies.SeTiServer.Permission;

public class Permission {
    private String channelId;
    private boolean hasWrite;
    private boolean hasRead;
    private String roleName;
    public Permission(){}
    public void setChannelId(String channelId){this.channelId=channelId;}
    public void setHasWrite(boolean hasWrite){this.hasWrite=hasWrite;}
    public void setHasRead(boolean hasRead){this.hasRead=hasRead;}
    public boolean isHasWrite(){return this.hasWrite;}
    public boolean isHasRead(){return this.hasRead;}
    public void setRoleName(String roleName){this.roleName=roleName;}
    public String getRoleName(){return roleName;}
    public String getChannelId(){return channelId;}

}
