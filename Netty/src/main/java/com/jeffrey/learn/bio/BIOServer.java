package com.jeffrey.learn.bio;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: jeffrey
 * @date: 1/11/20 4:04 PM
 * BIO应用示例，运行main方法，使用telnet localhost 8888进行通信，同时telnet多个
 * 不同的telnet窗口会和handler建立不同的线程
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        // 线程池机制
        // 思路
        // 1.创建一个线程池
        // 2.如果有客户端连接，就创建一个线程，与之通信(单独写一个方法)
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(5);
        // 最大线程数
        taskExecutor.setMaxPoolSize(20);
        // 队列大小
        taskExecutor.setQueueCapacity(1000);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        // 线程名的前缀
        taskExecutor.setThreadNamePrefix("async-thread-");

        taskExecutor.initialize();
        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("BIO服务器启动了");
        while (true) {
            System.out.println("线程ID：" + Thread.currentThread().getId());
            System.out.println("线程名字：" + Thread.currentThread().getName());
            // 监听，等待客户端连接
            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            // 使用线程池技术优化
            taskExecutor.execute(() -> handler(socket));
        }
    }

    // 编写一个handler方法，可客户端通讯
    public static void handler(Socket socket){
        try {
            System.out.println("handler线程ID：" + Thread.currentThread().getId());
            System.out.println("handler线程名字：" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            // 循环的读取客户端发送的数据
            int read;
            while ((read = inputStream.read(bytes))!=-1) {
                System.out.println("线程ID：" + Thread.currentThread().getId());
                System.out.println("线程名字：" + Thread.currentThread().getName());
                System.out.println(new String(bytes, 0, read));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
