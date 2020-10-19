package com.example.learn.design.patterns.decorator.simple;

/**
 * <p>
 *      蛋糕中加水果的装饰器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 11:15
 * @company 中再云图技术有限公司
 **/
public class FruitDecorator extends CakeDecorator{
    public FruitDecorator(AbsCake cake) {
        super(cake);
    }

    @Override
    public String getAssembly() {
        return super.getAssembly() + " + 一盘水果";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 30;
    }
}
