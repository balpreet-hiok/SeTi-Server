package org.bstechnologies.DoChatServer.UserData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class UserManager {
    public User [] users;
    public User [] online;
    public UserManager() throws Exception {
        File file = new File("data/users");
        String [] ids = file.list();
        users = new User[ids.length];
        User user = new User();
        for(int i=0;i<users.length;i++)
        {
            File usr = new File("data/users/"+ids[i]);
            String id = usr.getName();
            System.out.println(id+" blhaa");
            JSONObject json = loadData("data/users/"+id);
            String name = json.get("name").toString();
            System.out.println(name+" haha");
            user.setName(name);
            user.setId(id);
            HashMap<String,String> data = new HashMap<>();
            String [] keys = new String[json.keySet().size()];
            json.keySet().toArray(keys);
            for(int j=0;j< keys.length;j++)
            {
                if(keys[i].equals("name"))continue;
                data.put(keys[i],json.get(keys[i]).toString());
            }
            int in = emptySpace();
            this.users[in] = user;
            System.out.println(users[i].getName()+" lmao");

        }

    }
    public JSONObject loadData(String path) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject json = (JSONObject) obj;
        return json;
    }
    private int emptySpace()
    {
        int out=0;
        for(int i=0;i<users.length;i++)
        {
            if(users[i]==null)
            {
                out = i;
                break;
            }
        }
        return out;
    }

}
