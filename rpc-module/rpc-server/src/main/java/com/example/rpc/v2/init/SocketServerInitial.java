package com.example.rpc.v2.init;

import com.example.rpc.v2.handler.ProcessorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *      当容器启动完毕后会发布一个 ContextRefreshedEvent 的事件
 *      可以通过实现 ApplicationListener 类对该事件进行监听
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {

    private final static ExecutorService es = Executors.newCachedThreadPool();

    /**
     * 当 spring 容器启动完毕后，说明该启动一个远程调用的服务
     * @param contextRefreshedEvent     容器上下文启动完毕事件
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket = null;

        try {

            int rpcServerPort = 8888;
            serverSocket = new ServerSocket(rpcServerPort);
            while (true){
                // 通过线程池处理提交的任务
                es.execute(new ProcessorHandler(serverSocket.accept()));
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
