package com.example.rpc.v1.handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *      代理处理器，主要是为了将服务发布到外界，并提供RPC处理方式
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcProxyServer {

    /** 使用线程池提交 **/
    private final static ExecutorService es = Executors.newCachedThreadPool();

    /**
     * 服务发布
     * @param service       被代理的服务类
     * @param port          被发布的端口号
     */
    public void publish(Object service, int port){

        ServerSocket ss = null;

        try {
            ss = new ServerSocket(port);
            while (true){
                Socket accept = ss.accept();
                es.execute(new RpcProcessHandler(accept, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
