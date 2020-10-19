package com.com.netty.nio;

import java.nio.ByteBuffer;

public class NioTest6 {
    public static void main(String[] args) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i){
            buffer.put((byte)i);
        }

        buffer.position(2);//调整buffer中position位置
        buffer.limit(6);//同上

        //该方法能生成一个新buffer，该新的buffer从老的buffer的position开始，limit结束，
        // 含前不含后，但是改变任意一个buffer的相同位置另一个也会改变
        ByteBuffer sliceBuffer = buffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); ++i){
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}