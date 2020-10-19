package com.example.learn.design.patterns.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 婚介所，动态代理zhanglaosan的工作
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/1 9:01
 **/
public class CglibMatchmaker implements MethodInterceptor {

    public Object getInstance (Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Zhangsan.class);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();

        return result;
    }

    private void before() {
        System.out.println("双方牵手成功");
    }

    private void after() {
        System.out.println("婚介所开始无色。");
    }
}
