package com.example.test.aspect;

/**
 * <p>
 *      切面日志
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class LogAspect {

    public void before(){
        System.out.println("前置方法执行.");
    }

    public void after(){
        System.out.println("后置方法执行.");
    }

    public void afterThrowing(){
        System.out.println("异常方法执行.");
    }

}
