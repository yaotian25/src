package com.ray.atm;

import com.ray.thread.ThreadPoolManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
    public static void main(String[] args) {
        Bank bank = new Bank(10);
        ThreadPoolManager manager = new ThreadPoolManager(10);
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("银行服务器启动。。。");
            System.out.println("正在监听端口：" + serverSocket.getLocalPort());
            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("客户端：" + s.getRemoteSocketAddress() + "连接上了。。。。");
                BankService service = new BankService(s, bank);
                manager.process(service);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
