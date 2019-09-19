package com.tomacat;

public class Exec {
    public static void main(String[] args) {
    Thread t=new Thread(){
        public void run() {
              pong();
        }
    };

    t.run();
        System.out.println("招商银行");

    }


    static  void pong(){
        System.out.println("信用卡");
    }
}
