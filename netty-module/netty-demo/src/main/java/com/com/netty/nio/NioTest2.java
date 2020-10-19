package com.com.netty.nio;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("E:\\idea\\work\\netty-demo\\src\\niotest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();//mark = -1;也就是让mark失效的意思

        while (byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.println("Character " + (char)b);
        }

        fileInputStream.close();
    }
}
