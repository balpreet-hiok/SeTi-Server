package org.bstechnologies.DoChatServer.UserData;

import java.util.HashMap;
import java.util.Set;

public class User {
    private String name;
    private String id;
    private HashMap<String, String> data = new HashMap<>();
    public User(){}
    public User(String name){this.name=name;}
    public User(String name,String id){this.name=name;this.id=id;}
    public void setName(String name){this.name=name;}
    public void setId(String id){this.id=id;}
    public void add(HashMap<String,String> data)
    {
        Set keySet = data.keySet();
        String [] keys = new String[keySet.size()];
        keySet.toArray(keys);
        for(int i=0;i< keys.length;i++)
        {
            this.data.put(keys[i],data.get(keys[i]));

        }
    }
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public String get(String key)
    {
        return this.data.get(key);
    }


}
