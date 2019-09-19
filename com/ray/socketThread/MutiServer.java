package com.ray.socketThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MutiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(20000);
        System.out.println("服务器启动了，开始监听端口号为：" + ss.getLocalPort());

        while (true) {
            Socket s = ss.accept();
            Thread t = new Thread(new TalkTask(s));
            t.start();
        }
    }
}

class TalkTask implements Runnable {
    private Socket socket;

    public TalkTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("客户端：" + socket.getInetAddress() + "连接上服务器....");
            Scanner clientReader = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            do {
                String response = clientReader.nextLine();
                System.out.println("客户端：" + socket.getInetAddress() + "向服务器说：" + response);
                if ("bye".equals(response)) {
                    System.out.println("客户端：" + socket.getInetAddress() + "主动与我服务器断开连接");
                    break;

                }
                System.out.println("请输入你要对" + socket.getInetAddress() +"客户端说的话：");
                String line = sc.nextLine();
                pw.println(line);
                pw.flush();
                if ("bye".equals(line)) {
                    System.out.println("我主动与客户端：" + socket.getInetAddress() + "断开连接");
                    break;
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}