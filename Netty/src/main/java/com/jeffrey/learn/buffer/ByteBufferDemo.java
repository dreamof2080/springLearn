package com.jeffrey.learn.buffer;

import java.nio.ByteBuffer;

/**
 * @author: jeffrey
 * @date: 1/11/20 5:51 PM
 *
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        // 创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);
        // 类型化方式放入数据
        buffer.putInt(88);
        buffer.putLong(6L);
        buffer.putChar('朱');
        buffer.putShort((short) 11);
        // 取出
        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
