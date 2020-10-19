package com.com.netty.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

//文件锁
public class NioTest10 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

//        true代表共享锁，false代表排他锁.排他锁是写只能有一个在写，读可以有多个一起读
        FileLock fileLock = fileChannel.lock(3, 6, true);

//        判断有效性
        System.out.println("valid: " +  fileLock.isValid());

        System.out.println("lock type: " + fileLock.isShared());

        randomAccessFile.close();
    }
}
