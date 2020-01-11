package com.jeffrey.learn.buffer;

import java.nio.IntBuffer;

/**
 * @author: jeffrey
 * @date: 1/11/20 5:48 PM
 *
 * Buffer操作示例
 */
public class BufferDemo {
    public static void main(String[] args) {
        // 创建一个IntBuffer, 大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
