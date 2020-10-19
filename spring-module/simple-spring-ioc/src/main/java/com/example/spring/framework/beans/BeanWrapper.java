package com.example.spring.framework.beans;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Getter
public class BeanWrapper {

    private Object wrapperInstance;

    private Class<?> wrapperClazz;

    public BeanWrapper(Object wrapperInstance) {
        this.wrapperClazz = wrapperInstance.getClass();
        this.wrapperInstance = wrapperInstance;
    }

}
