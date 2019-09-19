package com.ray.thread;

import java.util.Vector;

public class ThreadPoolManager {
    private Vector<SimpleThread> vector;//存储线程的集合
    private int initSize=10;

    public void setInitSize(int initSize) {
        this.initSize=initSize;
    }

    public ThreadPoolManager(int initSize) {
        if (initSize > this.initSize) {
            this.initSize=initSize;
        }
        vector =new Vector<SimpleThread>();
        //循环初始化长度，创建线程存储到集合中
        for (int i = 0; i < initSize; i++) {
            SimpleThread sm = new SimpleThread(i + 1);
            vector.add(sm);//线程对象添加集合中
            sm.start();
        }
    }

    //调度线程池中线程的方法
    public void process(Runnable task) {
        int i;
        //循环 vector所有线程资源找到其中runningFlag=false
        for (i=0;i<vector.size();i++) {
            SimpleThread sm = vector.get(i);//获取线程对象
            if (sm.isRunningFlag() == false) {
                System.out.println("线程："+sm.getThreadNumber()+"开始执行新的任务");
                //设置任务
                sm.setTask(task);
                sm.setRunningFlag(true);
                return;
            }
        }
        System.out.println("线程池的资源已全部占用...请先扩容...");
        //线程池扩容
        if (i == vector.size()) {
            int temp=vector.size()+10;
            this.initSize=temp;
            for (; i < temp; i++) {
                SimpleThread sm = new SimpleThread(i + 1);
                vector.add(sm);
                sm.start();
            }
            //再次调用process
            this.process(task);
        }
    }

    public void destory() {
        for (int i = 0; i < vector.size(); i++) {
            vector.get(i).close();
        }
    }
}
