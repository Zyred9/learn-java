package com.com.netty.nio;

import java.nio.ByteBuffer;

public class NioTest5 {

//    ByteBuffer类型化的put与get
    public static void main(String[] args) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(500000000L);
        buffer.putDouble(14.123564);
        buffer.putChar('你');
        buffer.putShort((short)2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());

        /***放入和取出的顺序一定得一至，因为这里不同数据类型所占据的字节是不一致的，如果顺序不一致则会报错***/


    }
}
