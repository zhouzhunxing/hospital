package com.yjm.hospital.thread;

/**
 * @author zzx
 */
public class VolatileTest {
    private static volatile int count = 0;

    public static synchronized void add() {
        count++;
    }

    public static void main(String[] args) {
        String str = " abc aa";
        System.out.println(str.trim());
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                for (int j = 0; j < 10000; j++) {
//                    add();
//                }
//            }).start();
//        }
//
//        // 所有线程累加完成后输出
//        while (Thread.activeCount() > 2) {
//            Thread.yield();
//        }
//        System.out.println(count);
    }
}
