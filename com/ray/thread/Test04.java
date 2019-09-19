package com.ray.thread;

public class Test04 {
    public static void main(String[] args) {
        System.out.println("main开始。。。");
        ShowTimeTask2 task2=new ShowTimeTask2();
        Thread t=new Thread(task2);
        //设置精灵线程 又叫守护线程 当主线程结束，子线程也随之结束
        t.setDaemon(true);//必须在线程启动前调用
        t.start();
        System.out.println("main结束....");

    }
}
class ShowTimeTask2 implements Runnable{
    boolean flag=true;
    @Override
    public void run(){
        while (flag){
            System.out.println("子线程："+Thread.currentThread().getId()+"线程名："+Thread.currentThread().getName()+"\t优先级："+Thread.currentThread().getPriority());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
