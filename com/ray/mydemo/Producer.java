package com.ray.mydemo;

import java.util.Random;

public class Producer implements Runnable {
    private AppleBox box;
    public Producer(AppleBox box){
        this.box=box;
    }
    @Override
    public void run() {
        Random rd = new Random();
        //产生20个苹果
        for (int i= 0 ;i<20;i++) {
          Apple apple =new Apple(i+1);
          //存苹果
            box.produceApple(apple);
            System.out.println("产生了："+apple.id+"苹果");
            //模拟产生时间
            try {
                Thread.sleep(rd.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }
}
