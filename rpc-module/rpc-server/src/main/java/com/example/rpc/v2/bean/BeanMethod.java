package com.example.rpc.v2.bean;

import java.lang.reflect.Method;

/**
 * <p>
 *      对 spring bean 和对应方法进行映射
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class BeanMethod {

    private Object bean;

    private Method method;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
