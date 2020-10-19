package com.example.learn.design.patterns.decorator.simple;

/**
 * <p>
 *  最基础的蛋糕
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 11:09
 * @company 中再云图技术有限公司
 **/
public  class BaseCake extends AbsCake{

    @Override
    public String getAssembly() {
        return "蛋糕";
    }

    @Override
    public int getPrice() {
        return 200;
    }
}
