package com.example.ibatis.v2.plugin;

import com.example.ibatis.v2.annotation.Interceptors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 14:57
 **/
public class Plugin implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object warp(Object object, Interceptor interceptor) {
        Class clazz = object.getClass();
        return Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                new Plugin(object, interceptor)
        );
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果拦截器包含 @Interceptors 注解，那么说明这是一个需要被代理的对象
        if (interceptor.getClass().isAnnotationPresent(Interceptors.class)){
            // 如果执行的方法名称和拦截器所对应的名称一样，那么直接执行interceptor 拦截逻辑
            if (method.getName().equals(interceptor.getClass().getAnnotation(Interceptors.class).value())){
                return interceptor.intercept(new Invocation(target, method, args));
            }
        }
        // 没有被拦截，则执行原有的逻辑
        return method.invoke(target, method, args);
    }
}