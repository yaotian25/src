package com.ray.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test02HttpCookie {
    public static void main(String[] args) throws IOException {
        String urlStr = "http://localhost:8080/tiantiansy/user.jsp";
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie","JSESSIONID=1EEF52E4739C45018D91AEA811D00C75");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");

        conn.connect();
        int code = conn.getResponseCode();
        System.out.println(code);
        if (200 != code) {
            return;
        }
        System.out.println("文件大小：" + conn.getContentLength());
        if ("GET".equalsIgnoreCase(conn.getRequestMethod()) || "POST".equalsIgnoreCase(conn.getRequestMethod())) {
            InputStream in = conn.getInputStream();
            File file = new File("G:\\demofile\\index1.html");
            OutputStream out = new FileOutputStream(file);
            int length = -1;
            StringBuffer sb = new StringBuffer();
            byte[] bt = new byte[1024 * 10];
            while ((length = in.read(bt)) != -1) {
                out.write(bt, 0, length);
                sb.append(new String(bt, 0, length));
                out.flush();
            }
            out.close();
            in.close();
            System.out.println(sb.toString());

        }
    }
}
