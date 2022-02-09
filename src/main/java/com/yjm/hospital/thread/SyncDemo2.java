package com.yjm.hospital.thread;

public class SyncDemo2 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                shop.buy();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                shop.buy();
            }
        };
        t1.start();
        t2.start();
    }
}

class Shop {
    //    public synchronized void buy() {
    public void buy() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + "正在挑衣服.....");
            Thread.sleep(2000);

            synchronized (this) {
                System.out.println(t.getName() + "正在试衣服.....");
                Thread.sleep(2000);
            }

            System.out.println(t.getName() + "结账离开.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
