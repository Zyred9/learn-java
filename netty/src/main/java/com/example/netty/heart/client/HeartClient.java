package com.example.netty.heart.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class HeartClient {

    private static final int GROUP_SIZE = 1;
    private static final int SERVER_PORT = 8888;
    private static final String SERVER_HOST = "localhost";

    private void initClient () {
        EventLoopGroup group = new NioEventLoopGroup(GROUP_SIZE);

        try {
            Bootstrap bs = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .remoteAddress(new InetSocketAddress(SERVER_HOST, SERVER_PORT))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("ping", new IdleStateHandler(0, 9, 0, TimeUnit.SECONDS));
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(new HeartClientHandler());
                        }
                    });
//                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE);

            ChannelFuture future = bs.connect().sync();
            future.channel().closeFuture().sync();
        }catch (Exception ex ){
            ex.printStackTrace();
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        new HeartClient().initClient();
    }

}
