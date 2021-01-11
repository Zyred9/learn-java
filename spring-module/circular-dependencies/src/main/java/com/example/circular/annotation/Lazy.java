package com.example.circular.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *          懒加载注解
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lazy {
}
