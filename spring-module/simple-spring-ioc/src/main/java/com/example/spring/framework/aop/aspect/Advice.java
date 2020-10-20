package com.example.spring.framework.aop.aspect;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Setter
@Getter
public class Advice {

    /** 被代理的类 **/
    private Object aspect;

    /** 执行的方法 **/
    private Method adviceMethod;

    /** 异常的名称 **/
    private String throwName;

    public Advice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }
}
