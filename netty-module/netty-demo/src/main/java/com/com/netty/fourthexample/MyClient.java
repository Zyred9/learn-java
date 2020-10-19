package com.com.netty.fourthexample;

import com.com.netty.thirdexample.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyClient {
    public static void main(String args[])throws Exception{

        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).
                    handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8888).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                channel.writeAndFlush(br.readLine() +"\r\n");
            }
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}