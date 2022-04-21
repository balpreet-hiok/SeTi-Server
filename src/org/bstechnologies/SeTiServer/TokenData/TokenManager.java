package org.bstechnologies.SeTiServer.TokenData;

import java.util.Arrays;
import java.util.HashMap;

public class TokenManager {
    private Token [] tokens = new Token[1];
    public TokenManager(){}
    public void addToken(String tokenStr, String type,HashMap<String,String> data)
    {
        Token token$ = new Token(tokenStr,type);
        token$.add(data);
        int i = emptySpace();
        tokens[i] = token$;

    }
    public void addToken(String token, String type)
    {
        Token token$ = new Token(token,type);
        int i = emptySpace();
        tokens = Arrays.copyOf(tokens,tokens.length+1);
        tokens[i] = token$;


    }
    private int emptySpace()
    {
        int out=0;
        for(int i=0;i<tokens.length;i++)
        {
            if(tokens[i]==null)
            {
                out = i;
                break;
            }
        }
        return out;
    }
    public boolean checkToken(String token)
    {
        boolean out=false;
        for(int i =0;i<tokens.length;i++)
        {
            if(tokens[i]==null)continue;
            String token$ = tokens[i].getToken();
            if(token.equals(token$)){
                out = true;
                break;
            }
        }
        return out;
    }

    public void removeToken(String token)
    {
        int in = 0;
        for(int i=0;i< tokens.length;i++)
        {
            if(tokens[i]==null)continue;
            String tokenStr = tokens[i].getToken();
            if(tokenStr.equals(token))
            {
                in=i;
                break;
            }
        }
        Token [] temp = new Token[tokens.length-1];
        if(temp.length==0){temp=new Token[1];}
        for(int i=0;i< temp.length;i++)
        {
            if(i==in)continue;
            temp[i]=tokens[i];
        }
        tokens=temp;
    }
    public Token getToken(String tokenStr){
        for(int i=0;i<tokens.length;i++){
            System.out.println(tokens[i].getToken());
        }
        for(int i=0;i<tokens.length;i++){
            if(tokens[i].getToken().equals(tokenStr)){return tokens[i];}
        }
        return null;
    }
}
