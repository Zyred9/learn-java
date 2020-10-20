package com.example.spring.framework.aop;

/**
 * <p>
 *      顶层接口
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);


}
