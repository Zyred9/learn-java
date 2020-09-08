package com.example.learn.design.patterns.decorator.simple;

/**
 * <p>
 *  蛋糕
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/4 11:09
 * @company 中再云图技术有限公司
 **/
public abstract class AbsCake {


    /**
     * 获取蛋糕中添加的内容
     * @return
     */
    public abstract String getAssembly();

    /**
     * 获取蛋糕的价格
     * @return
     */
    public abstract int getPrice ();

}
