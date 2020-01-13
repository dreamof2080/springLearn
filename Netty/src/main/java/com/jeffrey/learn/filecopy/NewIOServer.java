package com.jeffrey.learn.filecopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author jeffrey
 * @date 1/13/20 1:48 PM
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception{
        InetSocketAddress address = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);

        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while (readCount != -1) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                } catch (Exception e) {
                    break;
                }
                // 倒带position=0 mark作废
                byteBuffer.rewind();
            }
        }
    }
}
