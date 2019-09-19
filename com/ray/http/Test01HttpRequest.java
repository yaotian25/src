package com.ray.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test01HttpRequest {
    public static void main(String[] args) throws IOException {
        String urlStr = "https://www.jd.com";
        //创建URL对象
        URL url = new URL(urlStr);
        //创建连接对象

        /**
         * 通过在URL
         */
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        int code = conn.getResponseCode();
        System.out.println(code);
        if (200!=code){
            return;
        }

        System.out.println("文件大小:"+conn.getContentLength());
        if ("GET".equalsIgnoreCase(conn.getRequestMethod()) ||
                "POST".equalsIgnoreCase(conn.getRequestMethod())){
            InputStream in = conn.getInputStream();
            File file = new File("G:\\demofile\\index.html");
            OutputStream out = new FileOutputStream(file);
            int length = -1;
            StringBuffer sb = new StringBuffer();
            byte[] bt = new byte[1024*10];
            while ((length=in.read(bt))!=-1){
                out.write(bt,0,length);
                sb.append(new String(bt,0,length));
                out.flush();
            }
            out.close();
            in.close();
            System.out.println(sb.toString());
        }

    }
}
