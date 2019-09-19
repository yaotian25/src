package com.ray.atm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankService implements Runnable {

    private Socket socket;
    private Bank bank;
    private Scanner sc;
    private PrintWriter pw;

    public BankService(Socket socket, Bank bank) {
        this.bank=bank;
        this.socket=socket;
    }


    @Override
    public void run() {

        try {
            //处理协议，并给出响应信息
            sc = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream());
            parseProtocal();
            //解析
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseProtocal() {
        while (true) {
            if (!sc.hasNext()) {
                System.out.println("客户端" + this.socket.getRemoteSocketAddress() + "已掉线");
                return;
            }
            String command = sc.next();
            if ("QUIT".equals(command)) {
                System.out.println("客户端" + this.socket.getRemoteSocketAddress() + "已经掉线...");
                return;
            }
            //获取账号信息
            int accountId=sc.nextInt();
            if ("DEPOSIT".equals(command)) {
                double money = sc.nextDouble();
                this.bank.deposit("" + accountId, money);
            }else if ("WITHDRAW".equals(command)){
                double money=sc.nextDouble();
                this.bank.withdraw("" + accountId, money);
            } else if (!"BALANCE".equals(command)) {
                pw.println("命令错误");
                pw.flush();
            }
            //显示余额
            pw.println(accountId + "" + this.bank.getBalance("" + accountId));
            pw.flush();
        }
    }
}
