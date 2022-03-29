package org.bstechnologies.DoChatServer.TokenData;

import java.util.Random;

public class TokenGen {
    public String genToken(int legnth)
    {
        String [] list = {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
        };
        String out = "";
        for(int i=0;i<legnth;i++)
        {
            Random rand = new Random();
            int a = rand.nextInt(2);
            if(a==0)
            {
                int b = rand.nextInt(10);
                out = out + new StringBuilder().append(b).toString();
            }
            if(a==1)
            {
                int c = rand.nextInt(2);
                if(c==0){
                    int d = rand.nextInt(26);
                    String e = list[d];
                    out = out + new StringBuilder().append(e).toString();
                }
                if(c==1)
                {
                    int d = rand.nextInt(26);
                    String e = list[d];e=e.toUpperCase();
                    out = out + new StringBuilder().append(e).toString();
                }
            }
        }
        return out;
    }
    public String genToken(int legnth, boolean alphaNumerical)
    {
        if(alphaNumerical)
        {
            String [] list = {
                    "a",
                    "b",
                    "c",
                    "d",
                    "e",
                    "f",
                    "g",
                    "h",
                    "i",
                    "j",
                    "k",
                    "l",
                    "m",
                    "n",
                    "o",
                    "p",
                    "q",
                    "r",
                    "s",
                    "t",
                    "u",
                    "v",
                    "w",
                    "x",
                    "y",
                    "z"
            };
            String out = "";
            for(int i=0;i<legnth;i++)
            {
                Random rand = new Random();
                int a = rand.nextInt(2);
                if(a==0)
                {
                    int b = rand.nextInt(10);
                    out = out + new StringBuilder().append(b).toString();
                }
                if(a==1)
                {
                    int c = rand.nextInt(2);
                    if(c==0){
                        int d = rand.nextInt(26);
                        String e = list[d];
                        out = out + new StringBuilder().append(e).toString();
                    }
                    if(c==1)
                    {
                        int d = rand.nextInt(26);
                        String e = list[d];e=e.toUpperCase();
                        out = out + new StringBuilder().append(e).toString();
                    }
                }
            }
            return out;
        }
        if(!alphaNumerical)
        {
            String out = "";
            for(int i=0;i<legnth;i++)
            {
                Random rand = new Random();
                int k = rand.nextInt(10);
                out = out+new StringBuilder().append(k).toString();
            }
            return out;
        }
        return null;
    }
}
