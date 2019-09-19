package com.ray.thread;

public class Test05 {
    public static void main(String[] args) throws InterruptedException {

        LifeCricle lc=new LifeCricle();
        Thread t=new Thread(lc);
        t.start();
        System.out.println("主程序。。。");
        t.join();//让t线程执行完成
        System.out.println("主线程....");
        System.out.println(t.isAlive()+"---");
    }
}

class LifeCricle implements Runnable{
    @Override
    public void run() {
        int i=0;
        while (i<=10){
            System.out.println(Thread.currentThread().getId()+":"+i);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 主线程向下运行，碰到t.join(),t要申请及加入到运行中来，就是要CPU执行权
     * 此时执行权在主线程中，主线程把CPU的执行权交给t,自己冻结状态
     * t执行完成后，主线程才能恢复运行中来
     */


}