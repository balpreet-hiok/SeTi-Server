package org.bstechnologies.DoChatServer.TokenData;

import java.util.HashMap;
import java.util.Set;

public class Token {
    private String tokenStr="";
    private String type="";
    private HashMap<String,String> data = new HashMap<String, String>();
    public Token(String tokenStr,String type){
        this.data.put("type",type);
        this.data.put("token",tokenStr);

    }
    public void setType(String type)
    {
        this.type=type;

    }
    public void setToken(String tokenStr)
    {
        this.tokenStr=tokenStr;
    }

    public String getToken(){
        return this.data.get("token");
    }
    public String getType()
    {
        return this.data.get("type");
    }
    public void add(HashMap<String,String> data)
    {
        Set keySet = data.keySet();
        String [] keys = new String[keySet.size()];
        keySet.toArray(keys);
        for(int i=0;i<keys.length;i++)
        {
            this.data.put(keys[i],data.get(keys[i]));
        }
    }

}
