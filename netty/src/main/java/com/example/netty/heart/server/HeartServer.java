package com.example.netty.heart.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 心跳监测服务端
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class HeartServer {

    private final int BOOS_SIZE = 1;
    private final int WORK_SIZE = 2;
    private final int SERVER_PORT = 8888;

    private void initServer() {
        EventLoopGroup boos = new NioEventLoopGroup(BOOS_SIZE);
        EventLoopGroup work = new NioEventLoopGroup(WORK_SIZE);

        try {
            ServerBootstrap bs = new ServerBootstrap()
                    .group(boos, work)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .localAddress(new InetSocketAddress(SERVER_PORT))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(new HeartServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            ChannelFuture future = bs.bind(SERVER_PORT).sync();
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
            boos.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new HeartServer().initServer();
    }

}
