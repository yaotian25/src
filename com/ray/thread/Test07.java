package com.ray.thread;

import java.util.Random;

public class Test07 {
    public static void main(String[] args) {
        SellTickOp op =new SellTickOp(30);
        Thread t01=new Thread(op,"张三");
        t01.start();
        Thread t02=new Thread(op,"李四");
        t02.start();
        Thread t03=new Thread(op,"王五");
        t03.start();
    }
}
class SellTickOp implements Runnable{

    boolean flag=true;
    int ticks;
    Random rd=new Random();

    public  SellTickOp(int ticks){
        this.ticks=ticks;
    }
    @Override
    public void run() {
        while (ticks>0){
              sale();
        }
    }

    public  synchronized void sale(){
        if(ticks >0){
            try {
                Thread.sleep(rd.nextInt(800));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"在sell第"+(ticks--)+"票");
        }else{
            return;
        }
    }
}