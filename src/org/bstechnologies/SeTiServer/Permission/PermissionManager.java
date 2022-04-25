package org.bstechnologies.SeTiServer.Permission;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class PermissionManager {
    private Permission []roles = new Permission[1];
    public PermissionManager() throws Exception {
        File file = new File("data/permissions.txt");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
        JSONObject json = (JSONObject) obj;
        JSONArray roleNamesJSON = (JSONArray) json.get("roles"); // List of role names
        int rolesLegnth = roleNamesJSON.size();
        String [] roleNames = new String[rolesLegnth];
        for(int i=0;i<roleNames.length;i++){
            roleNames[i] = roleNamesJSON.get(i).toString();
            System.out.println("roles :"+ roleNames[i]);
        }
        for(int i=0;i<roleNames.length;i++){
            JSONObject roleJSON = (JSONObject) json.get(roleNames[i]); // JSONObject of role name
            Set keySet = roleJSON.keySet();
            String []channelData = new String[keySet.size()];
            keySet.toArray(channelData);
            for(int j=0;j<channelData.length;j++){
               JSONObject channelJSON = (JSONObject) roleJSON.get(channelData[i]); // JSONObject of channel in role
               String hasWriteStr = channelJSON.get("hasWrite").toString();
               String hasReadStr = channelJSON.get("hasRead").toString();
               boolean hasRead = hasReadStr.equals("1");
               boolean hasWrite = hasWriteStr.equals("1");
               Permission permission = new Permission();
               permission.setChannelId(channelData[i]);
               permission.setRoleName(roleNames[i]);
               permission.setHasWrite(hasWrite);
               permission.setHasRead(hasRead);
               roles[roles.length-1] = permission;
               roles = Arrays.copyOf(roles,roles.length+1);
            }
        }
    }


    public Permission getPermission(String roleName,String channelId){
        for(int i=0;i<roles.length;i++){
            System.out.println(i);
            if(roles[i]==null){
                System.out.println("null");continue;
            }
            System.out.println(roles[i].getRoleName()+" "+roles[i].getChannelId()+" :role");
            if(roles[i].getRoleName().equals(roleName) && roles[i].getChannelId().equals(channelId))return roles[i];
        }
        return null;
    }
    public void reloadFiles() throws Exception{
        File file = new File("data/permissions.txt");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
        JSONObject json = (JSONObject) obj;
        JSONArray array = (JSONArray) json.get("roles");
        String [] roles = new String[1];
        for(int i=0;i<array.size();i++){
            roles[i] = array.get(i).toString();
            roles = Arrays.copyOf(roles,roles.length+1);
        }
        for(int i=0;i<roles.length;i++){
            JSONArray arr = (JSONArray) json.get(roles[i]);
            if(arr==null)continue;
            JSONObject json$ = (JSONObject) arr.get(0);
            String [] channels = new String[0];
            json$.keySet().toArray(channels);
            for(int j=0;j<channels.length;j++){
                String channelId = channels[j];
                arr = (JSONArray) json$.get(channelId);
                json$ =(JSONObject) arr.get(0);
                String hasWriteStr = json$.get("hasWrite").toString();
                String hasReadStr = json$.get("hasRead").toString();
                boolean hasRead = false;
                boolean hasWrite = false;
                if(hasWriteStr.equals("1"))hasWrite=true;
                if(hasReadStr.equals("1"))hasRead=true;
                Permission permission = new Permission();
                permission.setChannelId(channelId);
                permission.setHasWrite(hasWrite);
                permission.setHasRead(hasRead);
                permission.setRoleName(roles[i]);
                this.roles[this.roles.length-1] = permission;
                this.roles = Arrays.copyOf(this.roles,this.roles.length+1);
            }

        }
    }
}
