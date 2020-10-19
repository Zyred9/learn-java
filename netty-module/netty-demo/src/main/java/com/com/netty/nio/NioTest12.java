package com.com.netty.nio;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector选择器，
 * https://blog.csdn.net/qq_32331073/article/details/81132937
 */
public class NioTest12 {
    public static void main(String[] args) throws Exception{
        int[] ports = new int[5];

        ports[0] = 5001;
        ports[1] = 5002;
        ports[2] = 5003;
        ports[3] = 5004;
        ports[4] = 5005;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; ++i){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//调整为非阻塞模式
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

//            第一个参数为selector，第二个为SelectionKey中的接收事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口： " + ports[i]);
        }

        while (true){
            int numbers = selector.select();//返回的是一个key的数量，有可能是零，这是一个阻塞的方法，当有事件产生时才会继续往下执行
            System.out.println("numbers：" + numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();//返回selector的key set，因为监听多个通道所以是一个集合

            System.out.println("selectedKeys: " + selectionKeys);

            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()){
                SelectionKey selectionKey = iter.next();

                if(selectionKey.isAcceptable()){//如果是监听事件--即有新的连接
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);//连接注册到selector后我们要监听读事件

                    iter.remove();//必须移除，不然我们还会监听到这个事件

                    System.out.println("获得客户端连接：" + socketChannel);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    int bytesRead = 0;

                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);

                        if (read <= 0){
                            break;
                        }

                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);

                        bytesRead += read;
                    }

                    System.out.println("读取: " + bytesRead + "，来自于 " + socketChannel);

                    iter.remove();
                }
            }
        }
    }
}
