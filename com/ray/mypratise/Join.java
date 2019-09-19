package com.ray.mypratise;

public class Join {
    public static void main(String[] args) {
        System.out.println("老爸让儿子买烟的故事");
        new Thread(new Father()).start();
    }
}
class Father extends Thread{
    public void run() {
        System.out.println("老爸突然发现没烟了");
        System.out.println("让儿子去买和天下");
        Thread t=new Thread(new Son());
        t.start();
        try {
            t.join();
            System.out.println("老爸接过烟,把零钱给了儿子");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("儿子走丢了，老爸出去找儿子");
        }

    }
}
class Son extends Thread{
    public void run() {
        System.out.println("儿子接过老爸的钱出门了");
        System.out.println("路边有个网吧，进去玩了10秒");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "秒过去了。。。");

        }
        System.out.println("突然想起来忘记买烟了，赶紧去买烟");
        System.out.println("买了烟之后立马飞奔回家，把烟递给了老爸");
    }
}