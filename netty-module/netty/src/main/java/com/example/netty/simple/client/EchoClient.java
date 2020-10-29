package com.example.netty.simple.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * <p>
 * 客户端
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        EchoClient client = new EchoClient("localhost", 9998);
        //client.start();
    }

    /*public void start() throws Exception {
        com.example.netty.simple.client.EchoClientHandler handler = new com.example.netty.simple.client.EchoClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    // 指定远程的链接地址和端口
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 添加自定义的处理器到 pipeline 中
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(handler);
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }*/
}
