package com.example.learn.design.patterns.decorator.simple;

/**
 * <p>
 *      蛋糕的装饰器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 11:11
 * @company 中再云图技术有限公司
 **/
public class CakeDecorator extends AbsCake{

    private AbsCake cake;

    /**
     * 能通过对类功能增强的主要地方在于此，需要对顶层抽象作为装饰器的成员变量并提供构造入参
     * @param cake
     */
    public CakeDecorator(AbsCake cake) {
        this.cake = cake;
    }

    @Override
    public String getAssembly() {
        return cake.getAssembly();
    }

    @Override
    public int getPrice() {
        return cake.getPrice();
    }
}
