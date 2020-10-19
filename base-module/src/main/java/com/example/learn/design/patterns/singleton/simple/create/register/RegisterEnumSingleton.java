package com.example.learn.design.patterns.singleton.simple.create.register;

/**
 * <p>
 *      枚举创建单例对象
 *      优点：JDK底层实现了枚举无法被反射破坏
 *      缺点：内存浪费
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 13:52
 * @company 中再云图技术有限公司
 **/
public enum RegisterEnumSingleton {

    /** 实例 **/
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public static RegisterEnumSingleton getInstance() {
        return INSTANCE;
    }

}
