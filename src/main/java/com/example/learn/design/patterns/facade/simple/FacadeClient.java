package com.example.learn.design.patterns.facade.simple;

/**
 * <p>
 * 门面模式：  结构型模式
 * <p>
 * 使用场景：
 * 系统复杂的时候，增加门面模式提供简单访问
 * 构建多层系统，使用门面对象提供们个层的接口(例如：mvc结构)
 * <p>
 * 一个功能访问多个子系统的时候，通过一个对象完成对多个子系统的访问，被称为门面模式
 *
 * 门面模式和静态代理的区别：
 *  门面模式：重点在于对对象功能的封装
 *  静态代理：重点在对类功能的增强
 *
 *  门面模式和单例模式的区别：
 *
 *
 *   门面模式的优点：
 *   简化了调用过程， 无需深入了解子系统，以防给子系统带来风险
 *   减少系统依赖，松耦合
 *   更好的划分访问的层次，提高了安全性
 *   遵循迪米特法则，最少知道原则
 *
 *   门面模式的缺点：
 *   子系统扩展行为或修改参数，门面也需要同步修改。不符合开闭原则
 *   某些情况下违背单一职责原则，非面向抽象编程，无法进行职责单一
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/3 17:02
 * @company 中再云图技术有限公司
 **/
public class FacadeClient {


    public static void main(String[] args) {
        Goods goods = new Goods("《JVM 从入门到放弃》", 300);
        Facade facade = new Facade();
        facade.facade(goods);
    }


}
