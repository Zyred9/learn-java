package com.example.spring.framework.context;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public interface ApplicationContext {

    <T> T getBean(Class<T> clazz);
}
