package com.jeffrey.learn.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws Exception{
        // 创建SeverSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定一个端口6666,在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 得到一个Selector对象
        Selector selector = Selector.open();
        // 把serverSocketChannel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册后的selectionKey 数量：" + selector.keys().size());
        //循环等待客户端连接
        while (true) {
            // 这里我们等待1秒，如果没有事件发生，返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            // 如果返回>0，就获取到相关的selectionKey集合
            // 1. 如果返回>0,表示已经获取到关注的事件
            // 2. selector.selectedKeys()返回关注事件的集合
            // 通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys 数量：" + selectionKeys.size());
            // 遍历Set<SelectionKey>，使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                // 获取到SelectionKey
                SelectionKey key = keyIterator.next();
                // 根据key对应的通道发生的事件做相应处理
                if (key.isAcceptable()) {
                    // 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个socketChannel:" + socketChannel.hashCode());
                    // 将SocketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 将socketChannel注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后，组册的selectionKey数量：" + selector.keys().size());
                }
                if (key.isReadable()) {
                    // 通过key反向获取到对应channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    channel.read(byteBuffer);
                    System.out.println("from 客户端：" + new String(byteBuffer.array()));
                }
                // 手动从集合中移动当前的selectionKey,防止重复操作
                keyIterator.remove();
            }
        }
    }
}
