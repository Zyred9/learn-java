package com.example.netty.heart.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class HeartServerHandler extends ChannelInboundHandlerAdapter {

    /** 发送到远程节点的心跳消息 **/
    private static final ByteBuf HEART_MSG = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("滴答滴答", Charset.defaultCharset()));

    private static AtomicInteger ERROR_COUNTER = new AtomicInteger(0);

    private static int ERROR_MAX = 5;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到客户端发送的消息：" + msg);
    }


    /**
     * 当空闲时间太长时，会处罚一个 IdleSateEvent 事件， 重写本方法，可以对该事件进行处理
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            ctx.writeAndFlush(HEART_MSG.duplicate());
            IdleStateEvent id = (IdleStateEvent) evt;
            if (id.state() == IdleState.READER_IDLE) {
                ERROR_COUNTER.incrementAndGet();
                System.out.println("10s 读取超时");
                if (ERROR_COUNTER.get() > ERROR_MAX) {
                    // 关闭和客户端连接
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
