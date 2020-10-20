package com.example.spring.framework.context;

import java.util.List;
import java.util.Properties;
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

    Object getBean(String beanName);

    /**
     * 根据类名获取bean
     * @return
     */
    Set<String> getAllBean();

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    Properties getConfig();

}
