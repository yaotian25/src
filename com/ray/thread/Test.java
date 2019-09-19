package com.ray.thread;

public class Test {
    public static void main(String[] args) {
        //线程管理对象 线程池
        ThreadPoolManager manager = new ThreadPoolManager(10);
       manager.process(new CountTask(new MyNotify() {
           @Override
           public void notifyResult(Object obj) {
               System.out.println(obj);
           }
       }));
    }
}

class CountTask implements Runnable {

    private  MyNotify myNotify;

    public CountTask(MyNotify myNotify) {
      this.myNotify=myNotify;

    }
    @Override
    public void run() {

        int i=0;
        while (true) {
            if (null != myNotify) {
                myNotify.notifyResult(i);//没有输出
            }
            i--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i <= 0) {
                break;
            }
        }
    }
}