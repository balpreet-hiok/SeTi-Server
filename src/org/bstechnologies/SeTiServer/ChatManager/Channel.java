package org.bstechnologies.SeTiServer.ChatManager;

import java.util.Arrays;
import java.util.HashMap;
public class Channel {
    private String name;
    private String channelId;
    private HashMap<String,String>[] chatHistory = new HashMap[1];
    public void setName(String newName){
        this.name=newName;
    }
    public void setId(String id){this.channelId=id;}
    public String getName(){return this.name;}
    public String getId(){return this.channelId;}
    public void addMessage(String author,String message, String msgId){
        HashMap<String, String> data = new HashMap<>();
        data.put("message",message);
        data.put("author",author);
        data.put("messageId",msgId);
        chatHistory[chatHistory.length-1]=data;
        chatHistory = Arrays.copyOf(chatHistory,chatHistory.length+1);

    }
    private boolean checkId(String id) throws Exception
    {
        for(int i=0;i<chatHistory.length;i++)
        {

            String idd = chatHistory[i].get("id");
            if(idd.equals(id))return true;
        }
        return false;
    }
    public void removeMessage(String id){
        HashMap<String,String> [] temp = new HashMap[chatHistory.length-1];
        for(int i=0;i<chatHistory.length;i++)
        {

            String idStr = chatHistory[i].get("id");
            if(idStr.equals(id))continue;
            temp[i]=chatHistory[i];
        }
    }
    public HashMap<String,String> [] getChat(){return chatHistory;}
}
