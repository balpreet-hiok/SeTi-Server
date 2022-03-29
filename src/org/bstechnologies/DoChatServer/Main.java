package org.bstechnologies.DoChatServer;

import org.bstechnologies.DoChatServer.Handler.Handler;
import org.bstechnologies.DoChatServer.TokenData.Token;
import org.bstechnologies.DoChatServer.TokenData.TokenManager;

import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("[Server] Starting Server");
        System.out.println("[Server] Loading Properties");
        HashMap<String,String> map = getProperties();
        System.out.println("[Server] Starting Files Checkup");
        fileCheckup();
        int port = Integer.parseInt(map.get("port"));
        ServerSocket server = new ServerSocket(port);
        System.out.println("[Server] Listening to port "+port);
        TokenManager tokenManager = new TokenManager();
        while (true)
        {
            Socket socket = server.accept();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(socket,tokenManager);
                }
            };
            Thread th = new Thread(run);
            th.start();
        }
    }
    public static HashMap<String,String> getProperties() throws Exception {
        System.out.println("[Properties] Starting Properties Checkup");
        File path = new File("dochat_server.properties");

        if(!path.exists()){
            path.createNewFile();
            FileWriter fw = new FileWriter(path);
            fw.write("name=DoChat Server\n");
            fw.write("description=A DoChat Server\n");
            fw.write("port=1304\n");
            fw.close();
            System.out.println("[Properties] Made new Properties File");
        }

        HashMap<String,String> properties = new HashMap<>();
        Scanner scan = new Scanner(path);

        int jj=0;
        while(scan.hasNextLine())
        {
            scan.nextLine();
            jj++;

        }
        String [] prop = new String[jj];
        int i=0;
        scan = new Scanner(path);
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            prop[i] = line;
            i++;

        }

        for(int j=0;j<prop.length;j++)
        {
            String ln = prop[j];
            int k = ln.indexOf("=");
            String key = ln.substring(0,k);
            String value = ln.substring(k+1);
            properties.put(key,value);
        }
        System.out.println("[Properties] Loaded Properties: done");
        return properties;
    }
    public static void fileCheckup(){
        File file = new File("data");
        if(!file.exists()){file.mkdirs();}
        if(file.isFile()){file.delete();file.mkdirs();}
        file = new File("data/users");
        if(!file.exists()){file.mkdirs();}
        if(file.isFile()){file.delete();file.mkdirs();}
    }
}
