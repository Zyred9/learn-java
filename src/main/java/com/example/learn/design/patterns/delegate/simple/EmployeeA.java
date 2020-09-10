package com.example.learn.design.patterns.delegate.simple;

/**
 * <p>
 *      员工A
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 13:49
 **/
public class EmployeeA implements IEmployee {

    public static String goodAt = "吹牛逼";

    @Override
    public void doing(String task) {
        System.out.println("我是员工A，我擅长" + goodAt + ", 经理吩咐我" + task);
    }
}
