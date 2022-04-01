package org.bstechnologies.DoChatServer.UserData;

import org.bstechnologies.DoChatServer.TokenData.TokenGen;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class UserManager{
    private User [] users;
    private User [] online;
    public UserManager() throws Exception {
        File file = new File("data/users");
        String []ids = file.list();
        users = new User[ids.length];
        online = new User[ids.length];
        for(int i=0;i<users.length;i++)
        {
            User user = new User();
            user.setId(ids[i]);
            HashMap<String,String> data = loadData("data/users/"+user.getId());
            String name = data.get("name");
            user.setName(name);
            String []keys = new String[data.keySet().size()];data.keySet().toArray(keys);
            HashMap<String,String> usrData = new HashMap<>();
            for(int j=0;j<keys.length;j++)
            {
                if(keys[j].equals("name"))continue;
                String value = data.get(keys[j]);

                usrData.put(keys[j],value);
            }
            user.add(usrData);
            users[i]=user;
        }

    }
    public void isOnline(String id) throws Exception {
        User user = new User();
        HashMap<String,String> data = loadData("data/users/"+id);
        String name = data.get("name");
        user.setName(name);
        user.setId(id);
        String []keys = new String[data.keySet().size()];data.keySet().toArray(keys);
        HashMap<String,String> usrData = new HashMap<>();
        for(int i=0;i<keys.length;i++)
        {
            if(keys[i].equals("name"))continue;

            usrData.put(keys[i],data.get(keys[i]));
        }
        user.add(usrData);
        int in = getEmpty(online);
        online[in]=user;
    }
    public void isOffline(String id)
    {
        int removeIndex=0;
        for(int i=0;i< online.length;i++)
        {
            String checkId = online[i].getId();
            if(checkId.equals(id)) {
                removeIndex=i;
            }
        }
        if(removeIndex==0){return;}
        User [] temp = new User[online.length-1];
        for(int i=0;i< online.length;i++)
        {
            if(i==removeIndex)continue;
            temp[i]=online[i];
        }
        online = temp;
    }
    public User getUserData(String id)
    {
        User out;
        for(int i=0;i< users.length;i++)
        {
            if(users[i].getId().equals(id))return users[i];
        }
        return null;
    }
    public String makeUser(User user) throws IOException {
        try{
            String id = "";
            while (true) {
                TokenGen token = new TokenGen();
                id = token.genToken(4, false);
                File file = new File("data/users/" + id);
                if (!file.exists()) break;
            }
            String authToken = new TokenGen().genToken(50);
            JSONObject json = new JSONObject();
            json.put("name", user.getName());
            json.put("passwd", user.get("passwd"));
            json.put("authToken", authToken);
            FileWriter fw = new FileWriter("data/users/" + id);
            fw.write(json.toJSONString());
            fw.close();
            return id;
        }catch (Exception e)
        {
            return null;
        }
    }
    private int getEmpty(User[] users) throws Exception {
        for(int i=0;i< users.length;i++)
        {
            if(users[i]==null)return i;
        }
        throw new Exception("No Null Found");
    }
    private HashMap<String,String> loadData(String path) throws Exception
    {
        HashMap<String,String> output = new HashMap<>();
        File file = new File(path);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
        JSONObject json = (JSONObject) obj;
        Set keySet = json.keySet();
        String [] keys = new String[keySet.size()];
        keySet.toArray(keys);
        for(int i=0;i<keys.length;i++)
        {
            output.put(keys[i],json.get(keys[i]).toString());
        }
        return output;
    }
    public void reloadUsers() throws Exception {
        File file = new File("data/users");
        String []ids = file.list();
        users = new User[ids.length];
        for(int i=0;i<users.length;i++)
        {
            User user = new User();
            user.setId(ids[i]);
            HashMap<String,String> data = loadData("data/users/"+user.getId());
            String name = data.get("name");
            user.setName(name);
            String []keys = new String[data.keySet().size()];data.keySet().toArray(keys);
            HashMap<String,String> usrData = new HashMap<>();
            for(int j=0;j<keys.length;j++)
            {
                if(keys[j].equals("name"))continue;
                String value = data.get(keys[j]);

                usrData.put(keys[j],value);
            }
            user.add(usrData);
            users[i]=user;
        }

    }

}