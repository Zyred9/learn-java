package com.com.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<String, SocketChannel>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
//        将serverSocketChannel注册到selector,并关注连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(selectionKey -> {

                final SocketChannel client;

                try {
                    if (selectionKey.isAcceptable()){
//                        获取连接事件的ServerSocketChannel
                        ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
//                        获取SocketChannel，之后ServerSocketChannel便无用了
                        client = server.accept();
                        client.configureBlocking(false);
//                      将SocketChannel注册到selector,并关注读事件
                        client.register(selector, SelectionKey.OP_READ);

                        String key = "[" + UUID.randomUUID().toString() + "]";
                        clientMap.put(key, client);
                    }else if (selectionKey.isReadable()){
                        client = (SocketChannel)selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                        int count = client.read(readBuffer);

                        if (count > 0) {
                            readBuffer.flip();

                            Charset charset = Charset.forName("utf-8");
                            String receivedMessage = String.valueOf(charset.decode(readBuffer).array());

                            System.out.println(client + ": " + receivedMessage);

                            String senderKey = null;

                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()){
                                if (client == entry.getValue()){
                                    senderKey = entry.getKey();
                                    break;
                                }
                            }

                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()){
                                SocketChannel value = entry.getValue();

                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                writeBuffer.put((senderKey + ": " + receivedMessage).getBytes());
                                writeBuffer.flip();

                                value.write(writeBuffer);
                            }
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            });
            selectionKeys.clear();//一定要将处理完的事件清空
        }
    }
}
