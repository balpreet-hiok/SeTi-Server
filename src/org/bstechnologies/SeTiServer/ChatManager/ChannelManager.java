package org.bstechnologies.SeTiServer.ChatManager;

import org.bstechnologies.NetRequestManager.NetRequestManager;
import org.bstechnologies.SeTiServer.TokenData.TokenGen;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class ChannelManager {
    private Channel [] channels = new Channel[1];
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
                    String channelName = nrm.get("channelName");
                    String channelId  = nrm.get("channelId");
                    channel.setName(channelName);
                    channel.setId(channelId);
                }
                if(main.equals("message")) {
                    String message = nrm.get("msg");
                    String author = nrm.get("author");
                    String msgId = nrm.get("msgId");
                    channel.addMessage(author, message, msgId);
                }
            }
            this.channels[this.channels.length-1] = channel;
            this.channels = Arrays.copyOf(this.channels,this.channels.length+1);
            saveChannels();
        }
    }
    public void addMessage(String channelId,String message, String author) throws Exception {
        Channel channel;
        for(int i=0;i<channels.length;i++)
        {
            if(channels[i] == null) continue;
            System.out.println(i+"; i");
            if(channels[i].getId().equals(channelId)){
                System.out.println("hey wassup");
                channel = channels[i];
                TokenGen tokenGen = new TokenGen();
                String msgId = tokenGen.genToken(10,false);
                System.out.println(msgId);
                channel.addMessage(author,message,msgId);
                channels[i]=channel;
                saveChannels();
                break;
            }
        }
    }
    public void saveChannels() throws Exception {
       for(int i=0;i<channels.length;i++){
           if(channels[i]==null)continue;
           HashMap<String,String> []chat = channels[i].getChat();
           for(int j=0;j<chat.length;j++){
               if(chat[j]==null)continue;
               System.out.println(chat[j].get("message")+" message\n");
           }
       }
    }
    public void newChannel(String name) throws Exception {
        Channel channel = new Channel();
        channel.setName(name);
        TokenGen tokenGen = new TokenGen();
        String id = tokenGen.genToken(5,false);
        channel.setId(id);
        channels = Arrays.copyOf(channels,channels.length+1);
        channels[channels.length-1]=channel;
        saveChannels();
    }

}
