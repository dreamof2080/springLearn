package com.jeffrey.learn.filecopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author jeffrey
 * @date 1/13/20 12:01 PM
 */
public class OldIOClient {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 7001);
        String fileName = "/home/jefrrey/Videos/jetbrains-agent-latest.zip";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
