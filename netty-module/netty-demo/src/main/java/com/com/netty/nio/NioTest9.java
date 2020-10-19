package com.com.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest9 {

//    内存映射文件MappedByteBuffer，他是可以把内存和磁盘文件映射起来，这样子就可以通过操作缓存来进行对文件内容的修改，
//    操作的是堆外内存
    public static void main(String[] args) throws Exception{
//       RandomAccessFile https://blog.csdn.net/dimudan2015/article/details/81910690
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

//        position位置 size长度
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte)'a');
        mappedByteBuffer.put(3, (byte)'b');

        randomAccessFile.close();
//        注意，这里如果修改以后发现txt没有修改，直接去查看源文件，idea同步需要一段时间
    }
}
