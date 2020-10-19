package com.com.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args){
        IntBuffer buffer = IntBuffer.allocate(10);//分配一个大小为10的整数缓存区

        for (int i = 0; i < buffer.capacity(); ++i){
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();//操作翻转，在读和写操作之间必须要调用

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
