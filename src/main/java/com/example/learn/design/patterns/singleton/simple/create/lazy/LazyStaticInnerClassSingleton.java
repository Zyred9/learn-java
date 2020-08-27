package com.example.learn.design.patterns.singleton.simple.create.lazy;

/**
 * <p>
 *      静态内部类单例
 *      优点：写法优雅，高性能，避免内存浪费
 *      缺点: 能够被反射破坏
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 13:40
 **/
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {
    }

    public static LazyStaticInnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * LazyStaticInnerClassSingleton.class 被加载的时候，
     * LazyStaticInnerClassSingleton$LazyHolder.class 不会被加载
     *
     * 利用了java本身的语法特点，静态内部类只有被加载的时候才会被初始化
     */
    private static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE
                = new LazyStaticInnerClassSingleton();
    }
}
