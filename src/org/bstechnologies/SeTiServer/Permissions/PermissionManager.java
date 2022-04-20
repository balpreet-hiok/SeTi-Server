package org.bstechnologies.SeTiServer.Permissions;

import org.bstechnologies.NetRequestManager.NetRequestManager;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class PermissionManager {
    private Role [] roles = new Role[1];
    public PermissionManager() throws Exception{
        File file = new File("data/permissions.txt");
        if(!file.exists()){file.createNewFile();}
        if(file.isDirectory()){file.delete();file.createNewFile();}
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            NetRequestManager nrm = new NetRequestManager(line);
            String roleName = nrm.getMain();
            String writeStr = nrm.get("write");
            String readStr = nrm.get("read");
            Role role = new Role();
            role.setRoleName(roleName);
            if(writeStr.equals("1")){
                role.addPermission("write");
            }
            if(readStr.equals("1")){
                role.addPermission("role");
            }
            roles[roles.length-1] = role;
            roles = Arrays.copyOf(roles,roles.length+1);

        }
    }
    public void addRole(String roleName,String [] permissions) throws Exception {
        Role role = new Role();
        role.setRoleName(roleName);
        for(int i=0;i<permissions.length;i++){
            role.addPermission(permissions[i]);
        }
        roles[roles.length-1] = role;
        roles = Arrays.copyOf(roles,roles.length+1);
        saveRoles();
    }
    public void saveRoles() throws Exception{
        File file = new File("data/permissions.txt");
        FileWriter fw = new FileWriter(file);
        for(int i=0;i<roles.length;i++){
            if(roles[i] == null)continue;
            String out = "";
            String roleName = roles[i].getRoleName();
            out = roleName+"?";
            if(roles[i].hasPermission("write")){out=out+"write=1";}else{out=out+"write=0";}
            if(roles[i].hasPermission("read")){out=out+"&read=1";}else {out=out+"read=0";}
            fw.write(out);
        }
        fw.close();
    }
}
