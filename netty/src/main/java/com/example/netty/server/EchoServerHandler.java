package com.example.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * <p>
 *          服务端 channel处理器
 *          @ChannelHandler.Sharable : 标志 EchoServerHandler 可以被多个 channel 共享
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 对于每个传入的消息都要调用
     * @param ctx       上下文
     * @param msg       接收到的消息，是 ByteBuf 类型的
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取msg
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(Charset.defaultCharset()));
        // 将接收到的消息写给发送者，而不冲刷出站
        buf.writeBytes(("服务端接收到消息："+ buf.toString(Charset.defaultCharset())).getBytes());
        ctx.write(buf);
    }

    /**
     * 通知ChannelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点，并且关闭该 Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 在读取操作期间，有异常抛出时会调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 跟踪堆栈异常
        cause.printStackTrace();
        // 关闭channel
        ctx.close();
    }
}
