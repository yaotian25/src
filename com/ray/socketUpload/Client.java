package com.ray.socketUpload;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        /**
         * 1.创建服务器连接，获取端口号
         * 2.客户端读取图片数据
         * 3.通过客户端套接字将数据发送给服务器
         * 4.读取服务器响应信息
         * 5.关闭服务器
         */
        Socket socket = new Socket("localhost", 18888);
        //读取图片数据
        InputStream in = new FileInputStream(new File("E:\\1.png"));
        byte[] bt = new byte[1024 * 10];//存储数据
        OutputStream out = socket.getOutputStream();
        int length=-1;
        while ((length = in.read(bt)) != -1) {
            //发送给服务器
            out.write(bt, 0, length);
        }
        socket.shutdownOutput();//高速服务器图片上传完成
        //等待服务器响应
        DataInputStream data = new DataInputStream(socket.getInputStream());
        String str=null;
        System.out.print("服务器说：");
        if ((str = data.readUTF()) != null) {
            System.out.println(str);
        }
        //关闭所有资源
        data.close();
        in.close();
        out.close();
        socket.close();
    }
}
