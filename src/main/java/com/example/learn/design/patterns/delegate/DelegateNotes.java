package com.example.learn.design.patterns.delegate;

/**
 * <p>
 *
 *      委派模式：
 *          委派模式主要作用是完成任务的调度和分配，是一种特殊的静态代理模式
 *      静态代理模式和委派模式的区别：
 *          委派模式更加注重的是结果，客户端调用
 *          代理模式更加注重的是过程
 *
 *      委派模式三个角色：
 *          抽象任务，委派者角色，具体任务角色
 *
 *      优点：
 *          委派模式能够将大型的逻辑细化，然后管理子任务，跟进子任务，能够加快子任务处理效率
 *      缺点：
 *          根据任务的复杂层度不同，在相对复杂的任务下，可能会用到多重委派，增加了程序的复杂性
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 13:36
 **/
public class DelegateNotes {
}
