package com.ray.mypratise;

/**
 * 模拟龟兔赛跑
 */
public class Racer implements Runnable {

    private String winner;


    @Override
    public void run() {

        for (int steps = 1; steps <= 100; steps++) {

            //模拟休息
            if(Thread.currentThread().getName().equals("兔子")&& steps%10==0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-->" + steps);
            //比赛是否结实
           boolean flag = gameOver(steps);
            if (flag) {
                break;
            }
        }
    }

    private boolean gameOver(int steps) {

        if (winner!=null) {
            return  true;
        }else{
            if(steps==100){
                winner=Thread.currentThread().getName();
                System.out.println("winner is"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Racer racer=new Racer();
        new Thread(racer,"乌龟").start();
        new Thread(racer,"兔子").start();
    }
}