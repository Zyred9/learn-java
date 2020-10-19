package com.example.spring.framework.beans.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *      BeanDefinition: 用于描述任意一个类的类
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Getter
@Setter
@Accessors(chain = true)
public class BeanDefinition {

    private String beanClassName;

    private String factoryName;

}
