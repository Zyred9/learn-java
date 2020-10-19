package com.example.ibatis.v2.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 15:14
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Interceptors {

    String value();

}
