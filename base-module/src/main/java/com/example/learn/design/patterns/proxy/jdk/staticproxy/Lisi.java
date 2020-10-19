package com.example.learn.design.patterns.proxy.jdk.staticproxy;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/1 8:53
 * @company 中再云图技术有限公司
 **/
public class Lisi implements IPerson{

    @Override
    public void findLove() {
        System.out.println("李四要求：有车有房..");
    }
}
