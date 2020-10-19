package com.com.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

//    netty提供的用于保存channel的,连接的时候需要主动加入，断掉的时候netty会自动将其剔除
    private static ChannelGroup channelGroup = new DefaultChannelGroup((GlobalEventExecutor.INSTANCE));

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.forEach(ch ->{
            if(channel != ch){
                ch.writeAndFlush((channel.remoteAddress() + "发送的消息：" + msg + "\n"));
            }else {
                ch.writeAndFlush("自己" + msg + "\n");
            }
        });
    }

    @Override//服务器端与客户端建立连接时调用
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将指定的消息写入channelGroup内包含的所有的消息
        channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
    }

    @Override//连接断掉会调用该方法
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[服务器]-" +channel.remoteAddress() + "离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel() + "上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel() + "下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
