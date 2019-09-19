package com.ray.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test03HttpGet {
    public static void main(String[] args) {
        Socket socket=null;
        DataOutputStream dos=null;
        try {
            InetAddress address = InetAddress.getByName("localhost");
            String url="";
            socket = new Socket(address, 8080);
            dos = new DataOutputStream(socket.getOutputStream());
            String message = "GET" + url + "HTTP/1.1\r\n"+"Host:localhost\r\n\r\n";//报文头以两个\n结束
            byte[] bt = message.getBytes();
            dos.write(bt);
            dos.flush();
            System.out.println("-------------------");
            //获取服务器的响应信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line=null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != dos) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("运行结束。。。。");
    }
}
