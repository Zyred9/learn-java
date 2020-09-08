package com.example.learn.design.patterns.decorator.simple;

/**
 * <p>
 *      蛋糕中加豆腐的装饰器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 11:15
 * @company 中再云图技术有限公司
 **/
public class TofuDecorator extends CakeDecorator{
    public TofuDecorator(AbsCake cake) {
        super(cake);
    }

    @Override
    public String getAssembly() {
        return super.getAssembly() + " + 一瓶臭豆腐";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 10;
    }
}
