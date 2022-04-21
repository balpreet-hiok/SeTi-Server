package org.bstechnologies.SeTiServer;

import org.bstechnologies.SeTiServer.ChatManager.ChannelManager;
import org.bstechnologies.SeTiServer.Permission.PermissionManager;
import org.bstechnologies.SeTiServer.TokenData.TokenManager;
import org.bstechnologies.SeTiServer.UserData.UserManager;

public class Manager {
    public TokenManager tokenManager;
    public UserManager userManager;
    public ChannelManager channelManager;
    public PermissionManager permissionManager;
    public Manager(TokenManager tokenManager, UserManager userManager, ChannelManager channelManager, PermissionManager permissionManager){
        this.channelManager=channelManager;
        this.userManager=userManager;
        this.tokenManager=tokenManager;
        this.permissionManager=permissionManager;
    }
}
