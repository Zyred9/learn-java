package com.example.learn.design.patterns.adepter.objctadapter;

/**
 * <p>
 *      对象适配器：
 *      将原对象转换为目标对象，对象适配器可以满足最少知道原则，
 *      其最只管的就是将一种功能转换成另一种功能。
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 9:28
 * @company 中再云图技术有限公司
 **/
public class ObjectAdapter implements DC5{

    private AC220 ac220;

    public ObjectAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int output5v() {
        return ac220.output220v() / 44;
    }
}
