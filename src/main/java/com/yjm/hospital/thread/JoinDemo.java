package com.yjm.hospital.thread;

public class JoinDemo {
    private static boolean flag = false;
    public static void main(String[] args) {
        Thread download = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(10);
                        System.out.println("下载进度" + i + "%");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                flag = true;
            }
        };

        Thread show = new Thread() {
            @Override
            public void run() {
                try {
                    download.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!flag) {
                    throw new RuntimeException("加载图片失败!");
                }
                System.out.println("show:图片显示完毕!");
            }
        };
        download.start();
        show.start();
    }
}
