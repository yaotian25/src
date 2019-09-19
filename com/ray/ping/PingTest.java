package com.ray.ping;

import java.io.IOException;
import java.io.InputStream;

public class PingTest {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("ping 106.14.153.252");
        InputStream in=p.getInputStream();
        byte[] bt = new byte[1024];
        int length=-1;
        StringBuffer sb = new StringBuffer();
        while ((length=in.read(bt))!=-1){
            String str = new String(bt,0,length);
            sb.append(str);
        }
        in.close();
        System.out.println(sb.toString());
    }
}
