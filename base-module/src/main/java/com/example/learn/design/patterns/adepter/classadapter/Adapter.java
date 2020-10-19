package com.example.learn.design.patterns.adepter.classadapter;

/**
 * <p>
 *      适配器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/9 17:58
 **/
public class Adapter extends AC220 implements DC5{
    @Override
    public int output5v() {
        int ac220output = super.output220v();
        return ac220output / 44;
    }
}
