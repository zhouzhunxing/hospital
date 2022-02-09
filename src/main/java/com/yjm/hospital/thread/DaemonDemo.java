package com.yjm.hospital.thread;

public class DaemonDemo {
    public static void main(String[] args) {
        Thread rose = new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 5; i++) {
                    System.out.println("rose：let me go!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("rose：AAAAAA啊啊啊啊aaaaa........");
                System.out.println("噗通!!!");
            }
        };

        Thread jack = new Thread(){
            @Override
            public void run(){
                while (true) {
                    System.out.println("you jump! i jump!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        rose.start();
        jack.setDaemon(true);
        jack.start();
    }
}
