package com.jeffrey.learn.filecopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author jeffrey
 * @date 1/13/20 1:53 PM
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "/home/jefrrey/Videos/jetbrains-agent-latest.zip";
        // 得到一个文件channel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        // 准备发送
        long startTime = System.currentTimeMillis();
        // 在linux下一个transferTo方法就可以完成传输
        // 在windows下一次调用transferTo只能发送8m，就需要分段传输文件
        // transferTo底层使用到零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送的总字节数：" + transferCount + ", 耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
