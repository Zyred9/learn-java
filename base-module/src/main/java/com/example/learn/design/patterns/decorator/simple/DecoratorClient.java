package com.example.learn.design.patterns.decorator.simple;

/**
 * <p>
 *      装饰器模式客户端：  结构型模式
 *      定义：在不改变原有对象的基础上，将功能附加到对象上，提供了比继承更加有弹性的替代方案（扩展原有对象的功能）  动态扩展
 *      * 透明的，动态的扩展一个类的功能。
 *
 *      使用场景：
 *          用于扩展一个类的功能或给一个类添加附加的职责。
 *          动态的给对象添加功能，这些功能可以添加和撤回。
 *
 *
 * 装饰器模式盒代理模式对比：
 *  1. 装饰器模式是一种特殊的代理模式
 *  2，装饰器模式强调的是本身的功能扩展，透明扩展，可定制化
 *  3. 代理模式强调代理过程的控制
 *
 *
 *  装饰器模式的优点：
 *      1.遵守开闭原则
 *      2.装饰器是继承的有力补充，比继承更加灵活，能够在不改变原有对象的情况下对对象功能的扩展
 *      3.通过对不同装饰类不通的排列组合，可实现不同的效果
 *
 *   装饰器模式的缺点：
 *      1.代码增多，类增多，显得臃肿，增加程序的复杂性
 *      2.动态装饰时，多层装饰会更加复杂
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 10:25
 **/
public class DecoratorClient {

    public static void main(String[] args) {
        AbsCake cake;
        cake = new BaseCake();
        cake = new FruitDecorator(cake);
        cake = new BreadDecorator(cake);

        System.out.println(cake.getAssembly() + "，价格：" + cake.getPrice() + "元");
    }

}
