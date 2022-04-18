package org.bstechnologies.SeTiServer.ChatManager;

import org.bstechnologies.NetRequestManager.NetRequestManager;

import java.io.File;
import java.util.Scanner;


public class ChannelManager {
    private Channel [] channels;
    public ChannelManager() throws Exception {
        File file = new File("data/channels");
        if(file.isFile()){file.delete();file.mkdirs();}
        if(!file.exists()){file.mkdirs();}
        String [] channels = file.list();
        for(int i=0;i<channels.length;i++)
        {
            Channel channel = new Channel();
            NetRequestManager nrm = new NetRequestManager();
            file = new File("data/channels/"+channels[i]);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                nrm.parse(line);
                String main = nrm.getMain();
                if(main.equals("properties")){

                }
            }
        }
    }

}
