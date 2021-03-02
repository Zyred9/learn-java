package com.example.rpc.v1;

import com.exampl.rpc.v1.service.IUserService;
import com.example.rpc.v1.handler.RpcProxyServer;
import com.example.rpc.impl.UserServiceImpl;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcTestServiceV1 {

    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publish(userService, 8080);
    }
}
