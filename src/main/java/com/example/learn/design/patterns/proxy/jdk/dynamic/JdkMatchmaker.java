package com.example.learn.design.patterns.proxy.jdk.dynamic;

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
public class JdkMatchmaker implements InvocationHandler {

    private IPerson target;

    public IPerson getInstance(IPerson target) {
        this.target = target;
        Class<? extends IPerson> clazz = target.getClass();

        return (IPerson) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        after();
        Object invoke = method.invoke(this.target, args);
        before();
        return invoke;
    }

    private void before() {
        System.out.println("双方牵手成功");
    }

    private void after() {
        System.out.println("婚介所开始无色。");
    }
}
