package com.example.learn.design.patterns.proxy.jdk.dynamic;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/1 8:52
 * @company 中再云图技术有限公司
 **/
public class Zhangsan implements IPerson {

    @Override
    public void findLove() {
        System.out.println("张三要求：肤白貌美大长腿！！");
    }

    @Override
    public void buyInsure() {
        System.out.println("购买100万保险");
    }
}
