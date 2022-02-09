package com.yjm.hospital.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    private Socket socket;

    //构造方法,初始化客户端
    public Client1() {
        try {
            System.out.println("正在启动客户端");
            socket = new Socket("localhost", 8088);
            System.out.println("已经连接服务端");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("没有服务端呀======");
        }
    }

    //程序开始的方法
    public void start() {
        try {
            ServerHandler serverHandler = new ServerHandler();
            Thread t = new Thread(serverHandler);
            t.start();

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw, true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                pw.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    //用于读取 服务端的返回的数据
    private class ServerHandler implements Runnable {
        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);

                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
