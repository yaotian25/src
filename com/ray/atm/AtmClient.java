package com.ray.atm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AtmClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9000);
        System.out.println("atm客服端启动，并连接了："+socket.getRemoteSocketAddress());
        Scanner sc = new Scanner(socket.getInputStream());
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        String protocol1 = "BALANCE 1\n";//查询余额的协议
        pw.println(protocol1);
        pw.flush();
        //获取服务器端响应数据
        String response = sc.nextLine();
        System.out.println("服务器响应："+response);

        //存款协议1表示账号100存款数
        protocol1 = "DEPOSIT 1 100\n";
        pw.println(protocol1);
        pw.flush();

        //获取服务器响应数据
        response = sc.nextLine();
        System.out.println("服务器响应："+response);

        //取款协议 1表示账号10取款数
        protocol1 = "WITHDRAW 1 10\n";
        pw.println(protocol1);
        pw.flush();
        //获取服务器响应数据
        response = sc.nextLine();
        System.out.println("服务器响应："+response);

        //退出
        protocol1 = "QUIT\n";
        pw.println(protocol1);
        pw.flush();
        System.out.println("安全退出：");
    }
}
