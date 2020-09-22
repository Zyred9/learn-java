package com.example.ibatis.v2.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 包装类，对被代理对象进行包装
 *
 * @author zyred
 */
public class Invocation {
    /** 被代理的插件对象 **/
    private Object target;
    /** 执行拦截的方法 **/
    private Method method;
    /** 方法参数 **/
    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
