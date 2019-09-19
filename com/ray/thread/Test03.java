package com.ray.thread;

import java.util.Date;

public class Test03 {
    public static void main(String[] args) {
        System.out.println("main开始.....");
     /*   //使用内部类
        ShowTimeTask task =new Test03().new  ShowTimeTask();
        //创建线程对象
        Thread t=new Thread(task);
        t.start();//启动线程*/
     /*//使用匿名类
        Runnable r=new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    System.out.println("当前系统时间" + date);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t=new Thread(r);
        t.start();
    }*/

        //使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    System.out.println("当前系统时间" + date);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    //创建一个内部类
    class  ShowTimeTask implements Runnable{
        boolean flag=true;
        @Override
        public void run(){
            while (flag){
                Date date =new Date();
                System.out.println("当前系统时间"+date);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

