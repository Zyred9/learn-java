package com.com.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);//4或1024

        while (true){
            byteBuffer.clear();//如果把该行代码注释掉会怎样--36集

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