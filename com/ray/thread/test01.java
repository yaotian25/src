package com.ray.thread;

public class test01 {
    public static void main(String[] args) {
        System.out.println("main开始。。。。。。   ");
        show();
        System.out.println("main结束。。。。");
    }
    public static  void show(){
        boolean flag=true;
        while (flag){
            //系统假死。
        }
    }
}
