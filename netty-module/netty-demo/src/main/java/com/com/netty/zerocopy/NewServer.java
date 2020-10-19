package com.com.netty.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewServer {
    public static void main(String args[]) throws Exception{
        InetSocketAddress address = new InetSocketAddress(8888);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        /*
        一般socket连接结束后端口是不能马上释放的，这时候如果有新的socket想使用该端口号
        时会报错端口号已被占用。
        设置为true后则即使没被释放也可以使用该端口号
         */
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int readCount = 0;

            while (-1 != readCount){
                try {
                    readCount = socketChannel.read(byteBuffer);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                byteBuffer.rewind();
            }
        }
    }
}
