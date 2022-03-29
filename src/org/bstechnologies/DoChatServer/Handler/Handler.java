package org.bstechnologies.DoChatServer.Handler;

import org.bstechnologies.DoChatServer.Core.Core;
import org.bstechnologies.DoChatServer.TokenData.TokenManager;
import org.bstechnologies.NetRequestManager.NetRequestManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Handler {
    private String accToken="";
    public Handler(Socket socket,TokenManager tokenManager){
        String id = new StringBuilder().append("[").append(Thread.currentThread().getId()).append("]").toString();
        System.out.println("Handling "+socket+" with thread "+Thread.currentThread().getId());
        Runnable reader = new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner scan = new Scanner(socket.getInputStream());
                    PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
                    while(true)
                    {
                        String line = "";
                        line = scan.nextLine();
                        if(line.equals("over")){
                            Thread.currentThread().stop();
                        }
                        if(line.equals("print"))
                        {
                            tokenManager.printTokens();
                            continue;
                        }
                        Core core = new Core(tokenManager);
                        String reply = core.parse(line);
                        NetRequestManager nrm = new NetRequestManager();
                        nrm.parse(line);
                        if(nrm.getMain().equals("login"))
                        {
                            nrm.parse(reply);
                            if(nrm.get("status").equals("true"))
                            {
                                accToken=nrm.get("token");
                            }
                        }
                        pw.println(reply);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread read = new Thread(reader);
        read.start();
        while(true){
            if(!read.isAlive())break;
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tokenManager.removeToken(accToken);
    }

}
