package com.example.prc.v2.handler;

import com.exampl.rpc.v1.request.RpcRequest;
import com.example.prc.v1.handler.RpcTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 *      远程调用处理器
 * </p>
 *
 * @author zyred
 * @since v 0.2
 **/
@Component
public class RemoteInvocationHandlerV2 implements InvocationHandler {

    @Value("${mall.rpc.host}")
    private String host;

    @Value("${mall.rpc.port}")
    private int port;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 构造一个远程调用传输协议体
        RpcRequest request = new RpcRequest()
                .setTypes(method.getParameterTypes())
                .setMethodName(method.getName())
                .setClassName(method.getDeclaringClass().getName())
                .setArgs(args);

        return new RpcTransport(this.host, this.port).send(request);
    }
}
