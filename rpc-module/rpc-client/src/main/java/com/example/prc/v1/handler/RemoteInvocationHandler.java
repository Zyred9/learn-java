package com.example.prc.v1.handler;

import com.exampl.rpc.v1.request.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 *      Java 动态代理，针对 invoke 方法重写即可
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RemoteInvocationHandler implements InvocationHandler {

    private final String host;

    private final int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 该方法是代理类实现的 InvocationHandler 后重写的方法，该方法内主要完成了远程过程调用的一个操作
     * @param proxy     被代理的对象
     * @param method    被代理的方法
     * @param args      方法执行的参数
     * @return          方法执行后返回的结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        // 构建一个远程通讯的请求对象
        RpcRequest request = new RpcRequest()
                .setArgs(args)
                .setClassName(method.getDeclaringClass().getName())
                .setMethodName(method.getName())
                .setTypes(method.getParameterTypes());

        // 通过传输对象对封装好的 rpc 请求协议体进行发送
        return new RpcTransport(this.host, this.port).send(request);
    }
}
