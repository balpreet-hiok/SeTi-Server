package org.bstechnologies.DoChatServer.ChatManager;

import org.bstechnologies.DoChatServer.TokenData.TokenGen;
import org.bstechnologies.NetRequestManager.NetRequestManager;

import java.util.Arrays;

public class Channel {
    private String name;
    private String [] chatHistory = new String[1];
    public void setName(String newName){
        this.name=newName;
    }
    public void addMessage(String author,String message) throws Exception {
        String messageId;
        String data = "";
        String msgId = "";
        while (true)
        {
             msgId = new TokenGen().genToken(10,false);
            boolean check = checkId(msgId);
            if(!check)break;
        }
        data="message?author="+author+"&message="+message+"&id="+msgId;
        chatHistory[chatHistory.length-1]=data;
        chatHistory = Arrays.copyOf(chatHistory,chatHistory.length+1);
        return;
    }
    private boolean checkId(String id) throws Exception
    {
        for(int i=0;i<chatHistory.length;i++)
        {
            NetRequestManager nrm = new NetRequestManager();
            nrm.parse(chatHistory[i]);
            String idd = nrm.get("id");
            if(idd.equals(id))return true;
        }
        return false;
    }
    public void removeMessage(String id) throws Exception {
        String [] temp = new String[chatHistory.length-1];
        NetRequestManager nrm = new NetRequestManager();
        for(int i=0;i<chatHistory.length;i++)
        {
            nrm.parse(chatHistory[i]);
            String idStr = nrm.get("id");
            if(idStr.equals(id))continue;
            temp[i]=chatHistory[i];
        }
    }

}
