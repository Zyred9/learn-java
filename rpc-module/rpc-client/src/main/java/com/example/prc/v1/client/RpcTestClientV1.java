package com.example.prc.v1.client;

import com.exampl.rpc.v1.service.IUserService;
import com.example.prc.v1.handler.RpcProxyClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 *      测试客户端
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcTestClientV1 {


    public static void main(String[] args) throws IOException {
        /*RpcProxyClient client = new RpcProxyClient();

        IUserService userService = client.clientProxy(IUserService.class, "localhost", 8080);

        System.out.println("USER SERVICE getUserById => " + userService.getUserById(1));
        System.out.println("USER SERVICE getUserList => " + userService.getUserList());*/

        ServerSocket serverSocket = new ServerSocket(9999);
        Socket accept = serverSocket.accept();

        while (accept != null) {

        }
    }

}
