package com.example.learn.design.patterns.proxy.jdk.staticproxy;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/1 8:54
 * @company 中再云图技术有限公司
 **/
public class Zhanglaosan implements IPerson{

    private Zhangsan zhangsan;


    /**
     * 静态代理，无法为李四提供帮助，因为 zhanglaosan 不认识 lisi， 成千上万的人，不可能zhanglaosan一个人去完成
     * @param zhangsan
     */
    public Zhanglaosan(Zhangsan zhangsan) {
        this.zhangsan = zhangsan;
    }

    @Override
    public void findLove() {
        zhangsan.findLove();
        System.out.println("开始为儿子张三物色对象。");
        System.out.println("开始交往");
    }
}
