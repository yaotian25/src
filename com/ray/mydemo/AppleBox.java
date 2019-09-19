package com.ray.mydemo;

public class AppleBox {
    int index=0;
    Apple [] apples=new Apple[5];//只能存5个苹果

    //生产苹果

    public  synchronized  void  produceApple(Apple apple){
        while (index>=apples.length){
            //容器已满，无法继续产生苹果
            try {
                this.wait();//不能使用Thread.sleep()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        //产生苹果
        apples[index]=apple;
        System.out.println(Thread.currentThread().getName()+"生产了编号为："+apple.id+"的苹果");
        index++;
    }
    //取苹果
    public  synchronized Apple eatApple(){
        while(index<=0){
            //苹果全部消费，等待产生
            try {
                this.wait();//不能使用Thread.sleep()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        this.notifyAll();
        //消费苹果
        index--;
        System.out.println(Thread.currentThread().getName()+"生产了编号为："+apples[index].id+"的苹果");
        return apples[index];
    }

}
