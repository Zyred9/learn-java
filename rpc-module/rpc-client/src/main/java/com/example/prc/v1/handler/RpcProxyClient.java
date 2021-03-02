package com.example.prc.v1.handler;

import java.lang.reflect.Proxy;

/**
 * <p>
 *          客户端代理
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcProxyClient {

    /**
     * 针对接口生成代理类，代理类中主要是对网络请求做处理
     * @param interfaceCls      被代理的接口
     * @param host              可以通过socket访问的地址
     * @param port              可以通过socket访问地址的端口号
     * @param <T>               实现接口的类型
     * @return                  返回一个被代理的对象
     */
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port){
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }

}
