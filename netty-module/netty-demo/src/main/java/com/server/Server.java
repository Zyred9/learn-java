package com.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
/**
 * 1. 双线程组
 * 2. Bootstrap配置启动信息
 * 3. 注册业务处理Handler
 * 4. 绑定服务监听端口并启动服务
 */
@Component
@Slf4j
public class Server {

	private EventLoopGroup acceptorGroup = null;
	private EventLoopGroup clientGroup = null;
	// 服务启动相关配置信息
	private ServerBootstrap bootstrap = null;

	@PostConstruct
	private void init(){
		// 初始化线程组,构建线程组的时候，如果不传递参数，则默认构建的线程组线程数是CPU核心数量。
		acceptorGroup = new NioEventLoopGroup(2);

		clientGroup = new NioEventLoopGroup(14);
		// 初始化服务的配置
		bootstrap = new ServerBootstrap();
		// 绑定线程组
		bootstrap.group(acceptorGroup, clientGroup);
		// 设定通讯模式为NIO， 同步非阻塞
		bootstrap.channel(NioServerSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true);
		//使用内存池缓冲区提高性能
		bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		// 设定缓冲区大小， 缓存区的单位是字节。
		bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		// SO_SNDBUF发送缓冲区，SO_RCVBUF接收缓冲区，SO_KEEPALIVE开启心跳监测（保证连接有效）
		bootstrap.option(ChannelOption.SO_SNDBUF, 16*1024)
			.option(ChannelOption.SO_RCVBUF, 16*1024)
			.option(ChannelOption.SO_KEEPALIVE, true);
	}
	/**
	 * 监听处理逻辑。
	 * @param port 监听端口。
	 * @param acceptorHandlers 处理器， 如何处理客户端请求。
	 * @return
	 * @throws InterruptedException
	 */
	public ChannelFuture doAccept(int port, final ChannelHandler... acceptorHandlers) throws InterruptedException{

		System.out.println(port);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {

				// 定义一个定时断线处理器
				// 自定义间隔时长单位。 new ReadTimeoutHandler(long times, TimeUnit unit);
				ch.pipeline().addLast(new IdleStateHandler(1000, 0, 0));
				ch.pipeline().addLast(acceptorHandlers);
			}
		});

		log.info("*******************server-start*******************");
		return bootstrap.bind(port).sync();
	}
	
	/**
	 * shutdownGracefully - 方法是一个安全关闭的方法。可以保证不放弃任何一个已接收的客户端请求。
	 */
	public void release(){
		this.acceptorGroup.shutdownGracefully();
		this.clientGroup.shutdownGracefully();
	}

	@Async
	public void start(int port, final ChannelHandler... acceptorHandlers) {
		ChannelFuture future = null;
		Server server = null;

		try {
			future = doAccept(port, acceptorHandlers);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (null != future) {
				try {
					future.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(null != server){
				server.release();
			}
		}
	}
}