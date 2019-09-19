package com.ray.thread;

public class Test06 {
    public static void main(String[] args) {
        YieldOne y=new YieldOne();
        YieldOne y2=new YieldOne();
        Thread t=new Thread(y,"a");//创建线程并指定名字
        Thread t2=new Thread(y2,"b");
        t.setPriority(1);
        t.start();
        t2.setPriority(10);//设置线程优先级，更大的概率执行
        t2.start();
    }

}
class YieldOne implements Runnable{

    @Override
    public void run() {
        if("a".endsWith(Thread.currentThread().getName())){
            Thread.yield();//将执行权交给优先级高的线程
        }
        for (int i =0;i<10;i++){
           System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}