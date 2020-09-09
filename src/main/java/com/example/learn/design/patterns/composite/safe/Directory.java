package com.example.learn.design.patterns.composite.safe;

/**
 * <p>
 *      Linux 系统目录，顶层抽象
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/9 14:03
 **/
public abstract class Directory {

    protected String name;

    public Directory(String name) {
        this.name = name;
    }

    public abstract void show();
}
