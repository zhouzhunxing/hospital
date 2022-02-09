package com.yjm.hospital.thread;

/**
 * 互斥锁
 */
public class SyncDemo4 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                Foo.doSome();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                Foo.doSome();
            }
        };
        t1.start();
        t2.start();
    }
}

class Boo {
    public synchronized void methodA() {
        System.out.println("方法A准备执行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法A执行完毕");
    }

    public synchronized void methodB() {
        System.out.println("方法B准备执行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法B执行完毕");
    }
}