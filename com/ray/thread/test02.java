package com.ray.thread;

import java.util.Date;

/**
 * 实现线程的第一种方式 继承Thread
 */
public class test02 {

    public static void main(String[] args) {
        System.out.println("main开始。。。。。。   ");
         showTimeThread t=new showTimeThread();
         t.start();
        System.out.println("main结束。。。。");
    }
}
//继承Thread类 重写run();
class showTimeThread extends Thread{
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