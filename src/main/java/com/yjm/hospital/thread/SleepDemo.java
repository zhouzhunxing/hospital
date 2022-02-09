package com.yjm.hospital.thread;

public class SleepDemo {
    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("林：睡觉了");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("林：干嘛呢，干嘛呢");
                }
                System.out.println("林：醒了");
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("黄：开始砸墙了");
                for (int i = 0; i < 5; i++) {
                    System.out.println("80一锤子");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("黄：进来了");
                    }
                }
                System.out.println("黄:搞定");
                t1.interrupt();
            }
        };
        t1.start();
        t2.start();
    }
}
