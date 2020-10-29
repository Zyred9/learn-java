package com.example.spring.framework.aop;

import com.example.spring.framework.aop.aspect.Advice;
import com.example.spring.framework.aop.support.AdvisedSupport;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class JdkDynamicProxy implements InvocationHandler {

    private AdvisedSupport support;

    public JdkDynamicProxy(AdvisedSupport support) {
        this.support = support;
    }

//    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.support.getTargetClass().getInterfaces(),this);
    }

//    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.support.getTargetClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Map<String, Advice> advices = this.support.getAdvices(method);

        Object result = null;
        try {
            // 執行前置方法
            this.invokeAdvice(advices, "after");

            result = method.invoke(this.support.getTarget(), args);

            // 执行后置方法
            this.invokeAdvice(advices, "before");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }


    /**
     * 执行aop方法
     */
    @SneakyThrows
    private void invokeAdvice (Map<String, Advice> advices, String key) {
        Advice advice = advices.get(key);
        advice.getAdviceMethod().invoke(advice.getAspect());
    }
}
