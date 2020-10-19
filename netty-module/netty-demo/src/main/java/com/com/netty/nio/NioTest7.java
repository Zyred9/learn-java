package com.com.netty.nio;

import java.nio.ByteBuffer;

public class NioTest7 {
    public static void main(String[] args) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i){
            buffer.put((byte)i);
        }

//        转换为只读buffer，只读不能写，该buffer不能在转成可读写的buffer了
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

//        class java.nio.HeapByteBufferR R代表只读
        System.out.println(readOnlyBuffer.getClass());
    }
}
