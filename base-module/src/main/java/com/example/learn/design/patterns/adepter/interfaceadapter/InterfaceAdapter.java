package com.example.learn.design.patterns.adepter.interfaceadapter;

/**
 * <p>
 *      接口适配器：
 *      关注点：解决接口方法过多的问题，如果某个接口的方法过多，实现全部方法后，类显得臃肿。
 *      使用一个适配器去实现某一个接口的所有方法。
 *
 *      将220v转换为 5v 12v  24v  36v
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 9:35
 **/
public class InterfaceAdapter implements DC {

    private AC220 ac220;

    public InterfaceAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int output5v() {
        return ac220.output220v() / 44;
    }

    @Override
    public int output12v() {
        return ac220.output220v() / 18;
    }

    @Override
    public int output24v() {
        return ac220.output220v() / 9;
    }

    @Override
    public int output36v() {
        return ac220.output220v() / 6;
    }
}
