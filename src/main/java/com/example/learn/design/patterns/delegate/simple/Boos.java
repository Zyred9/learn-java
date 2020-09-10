package com.example.learn.design.patterns.delegate.simple;

/**
 * <p>
 *      1.创建老板角色
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 13:45
 **/
public class Boos {

    public void command(String task, Leader leader){
        leader.doing(task);
    }

}
