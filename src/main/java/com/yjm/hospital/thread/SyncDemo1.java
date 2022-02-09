package com.yjm.hospital.thread;

public class SyncDemo1 {
    public static void main(String[] args) {
        Table table = new Table();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int beans = table.getBeans();
                    Thread.yield();
                    System.out.println(getName() + ":" + beans);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int beans = table.getBeans();
                    Thread.yield();
                    System.out.println(getName() + ":" + beans);
                }

            }
        };
        t1.start();
        t2.start();
    }
}
 class Table {
    private int beans = 200;

    public synchronized int getBeans() {
        if (beans == 0) {
            throw new RuntimeException("没有豆子了！");
        }
        //模拟线程执行到这里没有时间了
        Thread.yield();
        return beans--;
    }
}
