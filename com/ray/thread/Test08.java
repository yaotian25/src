package com.ray.thread;

public class Test08 implements Runnable {
    boolean flag=true;
    static Zhangsan zs=new Zhangsan();
    static  Lisi ls=new Lisi();

    public static void main(String[] args) {
        Test08 t08=new Test08();
        t08.flag=true;
        new Thread(t08).start();
        Test08 tt08=new Test08();
        tt08.flag=false;
        new Thread(tt08).start();
    }

    @Override
    public void run() {
    if(flag){
        synchronized (zs){
            zs.say();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //同时把Lisi锁定
            synchronized (ls){
                zs.get();
            }
        }
    }else {
        synchronized (ls){
            ls.say();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (zs){
                ls.get();
            }
        }
    }
    }
}
class  Zhangsan{
    public void say(){
        System.out.println("张三对李四说：你给我书，我给你画");
    }
    public void get(){
        System.out.println("张三得到了画");
    }
}
class  Lisi{
    public void say(){
        System.out.println("李四对张三说：你给我画，我给你书");
    }
    public void get(){
        System.out.println("李四得到了书");
    }
}