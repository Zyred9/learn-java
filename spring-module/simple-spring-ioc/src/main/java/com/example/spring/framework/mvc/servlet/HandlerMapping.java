package com.example.spring.framework.mvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * <p>
 *          处理器映射类
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class HandlerMapping {

    private Pattern pattern;

    private Object controller;

    private Method method;

}
