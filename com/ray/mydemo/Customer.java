package com.ray.mydemo;

import java.util.Random;

public class Customer implements Runnable{

    private  AppleBox box;
    public  Customer(AppleBox box){
        this.box=box;
    }
    @Override
    public void run() {
        Random rd = new Random();
        for (int i= 0 ;i<20;i++) {

            //取苹果
            Apple apple=box.eatApple();
            System.out.println("消费了：" + apple.id + "苹果");
            //模拟产生时间
            try {
                Thread.sleep(rd.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
