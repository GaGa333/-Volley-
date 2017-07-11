package com.gcc.taotaopiao.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
    public static String getStrFromStream(InputStream is){
        String jsonStr="";
        BufferedReader reader=null;
        StringBuilder builder=null;
        try{
            reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
            String line="";
            while((line=reader.readLine())!=null){
                builder.append(line);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        jsonStr=builder.toString();
        return jsonStr;
    }
}

