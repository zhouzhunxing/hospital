package com.yjm.hospital.thread;

/**
 * 静态方法使用synchronized修饰,那么该方法一定具有同步效果;
 *
 */
public class SyncDemo3 {
    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                Foo.doSome();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                Foo.doSome();
            }
        };
        t1.start();
        t2.start();
    }
}
class Foo{
    public static synchronized void doSome(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"正在执行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName()+"执行完毕");

    }
}
