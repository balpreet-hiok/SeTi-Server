package org.bstechnologies.SeTiServer.Handler;

import org.bstechnologies.SeTiServer.Core.Core;
import org.bstechnologies.SeTiServer.Manager;
import org.bstechnologies.NetRequestManager.NetRequestManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Handler {
    private String accToken="";
    public Handler(Socket socket, Manager manager){
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
                        NetRequestManager nrm = new NetRequestManager();
                        try{
                            nrm.parse(line);
                        }catch (Exception e)
                        {
                            pw.println(e);
                            break;
                        }
                        Core core = new Core(manager);
                        String reply = core.parse(line);
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
        manager.tokenManager.removeToken(accToken);
    }

}
