package com.yjm.hospital.socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    /**
     * 运行在服务端的ServerSocket
     * 有两个作用
     * 1：像系统申请端口
     * 客户端通过此接口，连接服务端
     * 2：监听端口
     */
    private ServerSocket server;

    private PrintWriter[] allOut = {};

    public Server() {
        try {
            System.out.println("正在启动服务端");
            //申请端口
            server = new ServerSocket(8088);
            System.out.println("启动服务端完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                System.out.println("等待客户端连接");
                Socket socket = server.accept();
                System.out.println("一个客户端连接了");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread t = new Thread(clientHandler);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            PrintWriter pw = null;
            System.out.println("启动了一个线程处理客户端信息");
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);

                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
                BufferedWriter bw = new BufferedWriter(osw);
                pw = new PrintWriter(bw, true);
                synchronized (allOut) {
                    //allOut 数据扩容
                    allOut = Arrays.copyOf(allOut, allOut.length + 1);
                    //将pw 存放进数组
                    allOut[allOut.length - 1] = pw;
                }
                String str = null;
                while ((str = br.readLine()) != null) {
                    System.out.println("客户端说:" + str);
                    //pw.println("客户端说:" + str);
                    //遍历数组，转发消息
                    for (PrintWriter printWriter : allOut) {
                        printWriter.println(str);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                synchronized (allOut) {
                    //删除allOut中这一条 数据
                    for (int i = 0; i < allOut.length; i++) {
                        if (allOut[i] == pw) {
                            allOut[i] = allOut[allOut.length - 1];
                            //缩容
                            Arrays.copyOf(allOut, allOut.length - 1);
                            break;
                        }
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
