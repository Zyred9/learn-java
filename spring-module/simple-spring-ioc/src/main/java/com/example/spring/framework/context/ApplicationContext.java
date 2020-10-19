package com.example.spring.framework.context;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public interface ApplicationContext {

    /**
     * 根据类名获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> clazz);

    /**
     * 根据类名获取bean
     * @return
     */
    Set<String> getAllBean();
}
