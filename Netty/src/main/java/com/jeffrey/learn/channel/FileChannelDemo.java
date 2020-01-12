package com.jeffrey.learn.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  FileChannel示例
 */
public class FileChannelDemo {

    private static final String PATH = "/home/jeffrey/Videos/1.txt";
    private static final String COPY_PATH = "/home/jeffrey/Videos/2.txt";


    public static void main(String[] args) throws Exception {
//        writeToFile();
//        readFromFile();
//        copyFileText();
        copyFile();
    }


    // 输出str到文件中
    public static void writeToFile() throws Exception {
        String str = "Hello, Channel";
        // 1 创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 2 将 str 放入 byteBuffer
        byteBuffer.put(str.getBytes());
        // 3 创建一个输出流 channel
        FileOutputStream fileOutputStream = new FileOutputStream(PATH);
        // 4 通过FileOutputStream获取对应的FileChannel,这个FileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 5 对byteBuffer 进行flip
        byteBuffer.flip();
        // 6 通过write方法将byteBuffer 数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    // 从文件中读取
    public static void readFromFile() throws Exception{
        // 创建文件的输入流
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        // 通过fileInputStream 获取对应的FileChannel
        FileChannel fileChannel = fileInputStream.getChannel();
        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将通道的数据读入Buffer
        fileChannel.read(byteBuffer);
        // 将byteBuffer的字节数据转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    // 把文件1的数据复制到文件2中,文件2中的数据被覆盖
    public static void copyFileText() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(PATH);
        FileChannel channel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(COPY_PATH);
        FileChannel channel2 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            // 读取到buffer里
            int read = channel1.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            channel2.write(byteBuffer);
            byteBuffer.clear();
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    // 拷贝文件、拷贝文件内容、追加文件内容
    public static void copyFile() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile(PATH, "rw");
        RandomAccessFile toFile = new RandomAccessFile(COPY_PATH, "rw");

        // 获取各个流对应的fileChannel
        FileChannel sourceChannel = fromFile.getChannel();
        FileChannel destChannel = toFile.getChannel();

        // 使用transferForm完成拷贝
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        // 使用transferForm在源文件追加内容
//        destChannel.transferFrom(sourceChannel, destChannel.size(), 4);
        // 使用transferTo完成拷贝
//        sourceChannel.transferTo(0, sourceChannel.size(), destChannel);
        // 关闭相关通道和流
        sourceChannel.close();
        destChannel.close();
        fromFile.close();
        toFile.close();
    }
}
