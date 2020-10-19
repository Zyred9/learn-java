package com.example.learn.design.patterns.delegate.simple;

/**
 * <p>
 *      业务场景：
 *          老板给经理下发任务，经理根据不同情况给员工A和员工B分配任务
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 13:46
 **/
public class SimpleDelegateClient {

    public static void main(String[] args) {
        Boos boos = new Boos();
        boos.command("吹牛逼", new Leader());
    }

}
