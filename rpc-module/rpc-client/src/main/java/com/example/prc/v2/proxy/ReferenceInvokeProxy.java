package com.example.prc.v2.proxy;

import com.example.prc.v2.annotation.Reference;
import com.example.prc.v2.handler.RemoteInvocationHandlerV2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 处理 @Reference 注解类，对应的代理类注入
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    private RemoteInvocationHandlerV2 remoteInvocationHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 获取spring中加载完毕后的bean
        Field[] fields = bean.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {

                // 如果bean包含 @Reference 注解，那么需要进行代理
                if (field.isAnnotationPresent(Reference.class)) {
                    field.setAccessible(true);

                    // 为有 @Reference 注解的成员变量生成一个代理类
                    Object proxyInstance = Proxy.newProxyInstance(field.getType().getClassLoader(),
                            new Class<?>[]{field.getType()}, remoteInvocationHandler);
                    // 给成员变量设置代理后的对象
                    field.set(bean, proxyInstance);

                    field.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
