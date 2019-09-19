package com.ray.thread;

public class SimpleThread extends Thread {
    private int threadNumber;//线程编号
    private  boolean runningFlag;//当前线程运行状态 true-->wait
    private Runnable task;//要执行的任务
    private boolean flag=false;//用于控制线程消亡

    public SimpleThread(int threadNumber) {
        this.threadNumber=threadNumber;
        this.runningFlag=false;
        System.out.println("线程：" + this.threadNumber + "创建....");
    }

    public boolean isRunningFlag() {
        return runningFlag;

    }

    //关键方法，通过这个方法唤醒线程(wait)
    public synchronized void setRunningFlag(boolean runningFlag) {
        this.runningFlag=runningFlag;
        //资源锁 当前线程running ，要通知其他线程 可能进入阻塞的状态--》 notify
        if (this.runningFlag) {
            this.notifyAll();
        }
    }

    @Override
    public synchronized void run() {
        while (!flag) {
            try {
                if (!runningFlag) {
                    this.wait();//线程进入等待状态 无需占用cpu资源
                } else {
                    //当前线程运行起来
                    System.out.println("线程：" + this.threadNumber + "重新进入等待状态...等待下一次的调度");
                    this.runningFlag=false;
                }
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }

    //获取编程号
    public int getThreadNumber() {
        return this.threadNumber;
    }

    //设置任务
    public void setTask(Runnable task) {
        this.task=task;
    }

    //获得任务
    public Runnable getTask() {
        return this.task;
    }

    //关闭线程
    public synchronized void close() {
        this.flag=true;
    }
}
