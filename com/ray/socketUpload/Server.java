package com.ray.socketUpload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(18888);
        System.out.println("服务器启动了，开始监听端口为" + ss.getLocalPort());
        while (true) {
            Socket s = ss.accept();
            //获取客服端的图片数据
            InputStream in = s.getInputStream();
            OutputStream out = new FileOutputStream(new File("E:\\yc69.png"));
            byte[] bt = new byte[1024 * 10];
            int length=-1;
            while ((length = in.read(bt)) != -1) {
                //写入文件中
                out.write(bt, 0, length);

            }
            //发送信息给客户端
            DataOutputStream data = new DataOutputStream(s.getOutputStream());
            data.writeUTF("文件上传成功！！！");
            data.flush();
            data.close();
            out.close();
            in.close();
        }
    }
}
