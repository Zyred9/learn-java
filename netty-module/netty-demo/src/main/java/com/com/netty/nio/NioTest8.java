package com.com.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest8 {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("input2.txt");
        FileOutputStream outputStream = new FileOutputStream("output2.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        //allocate()和allocateDirect()之间的区别，allocateDirect()是直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);

        while (true){
            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);

            System.out.println("read: " + read);

            if (-1 == read){
                break;
            }

            byteBuffer.flip();

            outputChannel.write(byteBuffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
